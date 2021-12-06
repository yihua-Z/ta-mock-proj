package com.psbc.business.processor;

import com.psbc.business.service.*;
import com.psbc.exceptions.ApplyException;
import com.psbc.exceptions.ConfirmExpectationException;
import com.psbc.exceptions.ProcessingException;
import com.psbc.mapper.*;
import com.psbc.pojo.*;
import com.psbc.pojo.ApplicationModel;
import com.psbc.pojo.ConfirmationModel;
import com.psbc.pojo.ExpectationModel;
import com.psbc.reader.xmlModel.XMLNode;
import com.psbc.utils.helper.XMLParser;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.Exception;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.psbc.service.ObjectProcessor.copyFields;
import static com.psbc.utils.DateAndTimeUtil.getFullNowDateTime;

@Data
@Component
public class Processor022 extends BiDirectionProcessor {
    private static final Logger logger = Logger.getLogger(Processor022.class);

    public RepositoryFactory FactoryDao() {
        RepositoryFactory repositoryFactory = SpringContextUtil.getBean(RepositoryFactory.class);
        return repositoryFactory;
    }

    public static ProcessingException getProcessingException(ProcessingException exception, String code) {

        String description = ReturnCodeDescription(code);
        exception.setReturncode(code);
        exception.setErrortype("1");
        exception.setSpeification(description);
        logger.error(description);
        return exception;
    }
    public static String ReturnCodeDescription(String code) {
        XMLNode return_codes = XMLParser.parseXml(".\\src\\main\\resources\\xml\\return_code\\return_codes.xml");
        String description = "未知错误";
        List<XMLNode> childrenNodes = return_codes.getChildrenNodes();
        for (XMLNode x : childrenNodes
        ) {
            Map<String, String> attributes = x.getAttributes();
            Set<String> keySet = attributes.keySet();

            for (String key : keySet
            ) {
                if (key.equals("code")) {
                    if (attributes.get(key).equals(code)) {
                        description = attributes.get("description");
                        return description;
                    }
                }

            }
        }

        return description;
    }

    public static void validateApplyFromXML(ApplicationModel apply) throws ApplyException {

        TransactionApplication transactionApplication = (TransactionApplication) apply;
        ApplyException applyException = (ApplyException) new ProcessingException();
        copyFields(transactionApplication, applyException);
        String businesscode = transactionApplication.getBusinesscode();

        XMLNode business_configs = XMLParser.parseXml(".\\src\\main\\resources\\xml\\business_configs\\BUSINESSCODE.xml".replace("BUSINESSCODE", businesscode));
        List<XMLNode> childrenNodes = business_configs.getChildrenNodes();
        for (XMLNode c : childrenNodes
        ) {
            Map<String, String> attributes = c.getAttributes();
            Set<String> keySet = attributes.keySet();
            for (String key : keySet
            ) {
                if (key.equals("fieldName")) {
                    try {
                        String fieldName = attributes.get(key).toLowerCase();
                        String required = attributes.get("required");
                        Field field = transactionApplication.getClass().getDeclaredField(fieldName);
                        field.setAccessible(true);
                        Object o = field.get(fieldName);
                        String s = o.toString();
                        if (required.equals(true) && s == null) {
                            logger.error(fieldName + "字段缺失");
                            applyException.setReturncode("9999");
                            applyException.setErrortype("1");
                            applyException.setSpeification(fieldName + "字段缺失");
                            logger.error(fieldName + "字段缺失");
                            throw applyException;
                        }
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        logger.error(e);
                    }
                }
            }
        }


    }


    // 判断申请记录的业务合法性，不同业务需具体实现;
    // 若合法，不做任何返回；若不合法，抛对应异常

    @Override
    void validateApply(ApplicationModel apply) throws ApplyException {
        //  检查必要字段不为空,根据对应业务的XML解析是否required
        validateApplyFromXML(apply);

        TransactionApplication transactionApplication = (TransactionApplication) apply;
        ApplyException applyException=new ApplyException();
        copyFields(apply,applyException);

        ApplyFormatValidator applyFormatValidator = SpringContextUtil.getBean(ApplyFormatValidator.class);
        try {
            applyFormatValidator.validateFieldFormat(transactionApplication);
        }catch (ApplyException e){
//            交易请求报文格式错误
            getProcessingException(applyException,"3105");
            logger.error("申请记录字段格式不正确");
            throw e;
        }



        Double transactiondate = Double.valueOf(transactionApplication.getTransactiondate());
        Double transactiontime = Double.valueOf(transactionApplication.getTransactiontime());

        BigDecimal applicationamount = transactionApplication.getApplicationamount();
        //判断是否下午三点切日，如果切日日期加一天
        String CHANGEDTE = "150000";
        if (Double.valueOf(CHANGEDTE) < transactiontime) {
            transactiondate += 1;
        }



        AcctShareKey acctShareKey = new AcctShareKey();
        copyFields(transactionApplication, acctShareKey);
        AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);
        FundParaConfig fundParaConfig = FactoryDao().getFundParaConfigDao().selectByPrimaryKey(1);
//        申购日期不在申请期限内
        if (transactiondate > Double.valueOf(fundParaConfig.getFundestablishdate())) {
            logger.error("申请日期不在申购日期期限中");
            ApplyException processingException =(ApplyException) getProcessingException(applyException,"1864");
            throw  processingException;
        }


//        判断申购是否为首次购买 AppSheetSerialNo 通过申请单编号
        String confirmationAppsheetserialno = new TransactionConfirmation().getAppsheetserialno();
        if (!confirmationAppsheetserialno.contains(transactionApplication.getAppsheetserialno())) {
//            首次购买额度超值和低于最低额度
            if (applicationamount.compareTo(fundParaConfig.getMinbidsamountbyindi()) == -1
                    || applicationamount.compareTo(fundParaConfig.getMaxsubsamountbyindi()) == 1) {
                logger.error("首次申购的金额国小或者过大");
                getProcessingException(applyException,"0000");
            } else {
//                设定金额
                acctShare.setTotalamountofdistributorinta(applicationamount);
//                设定份额
                acctShare.setTotalfrozenvol(applicationamount.divide(fundParaConfig.getNav()));
            }
//            非首次购买
        } else {
            //   先判断申购金额是否超过个人最大申购金额getIndimaxpurchase
            if (applicationamount.compareTo(fundParaConfig.getIndimaxpurchase()) == 1) {
                logger.error("申购金额超过个人最大申购金额");
                getProcessingException(applyException,"0000");
                //            再判断是否超过单日可申购最高额度buyupperamount
            } else if (applicationamount.compareTo(fundParaConfig.getBuyupperamount()) == 1) {
                logger.error("超过单日可申购最高额度");
                getProcessingException(applyException,"0000");
//             个人当日累计购买最大金额 indidaymaxsumbuy
            } else if (applicationamount.compareTo(fundParaConfig.getIndidaymaxsumbuy()) == 1) {
                logger.error("超过个人当日累计购买最大金额");
                getProcessingException(applyException,"0000");
//            再判断自己加上之前的金额是否超过个人最大申购
            } else if ((applicationamount.add(acctShare.getTotalamountofdistributorinta())).compareTo(fundParaConfig.getIndidaymaxsumbuy()) == 1) {
                logger.error("再判断自己加上之前的金额是否超过个人最大申购");
                getProcessingException(applyException,"0000");
            } else {
//                增加的金额
                acctShare.setTotalamountofdistributorinta(acctShare.getTotalamountofdistributorinta().add(applicationamount));
//                增加的份额
                acctShare.setTotalfrozenvol(acctShare.getTotalfrozenvol().add(applicationamount.divide(fundParaConfig.getNav())));
            }
        }


//            当前净值类型非申购类型下   个人购买额度超过总额度
        if (fundParaConfig.getNetvaluetype().equals(1)) {
            BigDecimal nav = fundParaConfig.getNav();
            BigDecimal share = applicationamount.divide(nav, 20, BigDecimal.ROUND_HALF_UP);
//            理财产品总份数（含冻结）
            BigDecimal totalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
//            理财产品冻结份数
            BigDecimal totalfrozenvol = acctShare.getTotalfrozenvol();
//             个人购买额度超过总额度 ==1为左边>右边超过 ==0表示左边=右边  ==-1左边<右边
//            总份数-冻结总份数==为可申购的金额
//                    @TODO 存储 确认的份额
            totalvolofdistributorinta = totalvolofdistributorinta.add(share);
            acctShare.setTotalvolofdistributorinta(totalvolofdistributorinta);
            FactoryDao().getAcctShareDao().updateByPrimaryKey(acctShare);

        } else {
            logger.error("当前净值类型非申购类型");
            getProcessingException(applyException,"0000");
        }

//        跳过节假日和周末，生成确认表
        Boolean isHoliday = true;
        String transactioncfmdate = transactionApplication.getTransactiondate();
        String transactionDate = transactioncfmdate.substring(0, 8);
        Holiday holiday = new Holiday();
        while (isHoliday) {
            holiday.setDay(transactionDate);
            List<Holiday> holidays = FactoryDao().getHolidayDao().selectByCondition(holiday);
            if (holidays != null) {
                try {
                    transactionDate = addDay(transactionDate, 1);
                } catch (ParseException e) {
                    getProcessingException(applyException,"0000");
                    logger.error(e);
                }
            } else {
                isHoliday = false;
            }
        }
        TransactionExpectation transactionExpectation = new TransactionExpectation();
        transactionExpectation.setTransactioncfmdate(transactioncfmdate.substring(9) + transactionDate);
        TransactionExpectationDao transactionExpectationDao = SpringContextUtil.getBean(TransactionExpectationDao.class);
        transactionExpectationDao.updateByPrimaryKey(transactionExpectation);


        if (!(fundParaConfig.getNav().compareTo(applicationamount.divide(transactionApplication.getApplicationvol()))==0)){
            logger.error("申请金额与申请份额不一致");
            getProcessingException(applyException,"0000");
        }
    }

    // 判断所获期望是否合理，不同业务需具体实现

    //        读取期望配置表（获取失败信息）
//        获取交易日期配置表，获取延迟确认天数  获取工作日日历表
//        读取产品表（获取个人额度，产品额度）
//        读取账户信息表（获取个人已购额度）

    @Override
    void validateConfirmExpectation(ExpectationModel expect) throws ConfirmExpectationException {
        TransactionConfirmation transactionConfirmation = (TransactionConfirmation) expect;

        ConfirmExpectationException confirmExpectationException=new ConfirmExpectationException();
        copyFields(expect,confirmExpectationException);
        BigDecimal Confirmedamount = transactionConfirmation.getConfirmedamount();
        String transactiondate = transactionConfirmation.getTransactiondate();
        String transactiontime = transactionConfirmation.getTransactiontime();
        if (Double.valueOf(transactiondate + transactiontime) > Double.valueOf(getFullNowDateTime())) {
            getProcessingException(confirmExpectationException,"0000");
        }

        FundDateKey fundDateKey = new FundDateKey();
        copyFields(transactionConfirmation, fundDateKey);
        FundDate fundDate = FactoryDao().getFundDateDao().selectByPrimaryKey(fundDateKey);

        //      查找当前申购期望 的持仓表记录
        AcctShareKey acctShareKey = new AcctShareKey();
        AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);

        //        理财产品总金额（含冻结）
        BigDecimal totalamountofdistributorinta = acctShare.getTotalamountofdistributorinta();
        FundParaConfigDao fundParaConfigDao = SpringContextUtil.getBean(FundParaConfigDao.class);
        FundParaConfig fundParaConfig = fundParaConfigDao.selectByPrimaryKey(1);


        totalamountofdistributorinta.add(transactionConfirmation.getConfirmedamount());

        //   先判断申购金额是否超过个人最大申购金额getIndimaxpurchase
        if (Confirmedamount.compareTo(fundParaConfig.getIndimaxpurchase()) == 1) {
            logger.error("申购金额超过个人最大申购金额");
            getProcessingException(confirmExpectationException,"0000");
//            再判断是否超过单日可申购最高额度buyupperamount
        } else if (Confirmedamount.compareTo(fundParaConfig.getBuyupperamount()) == 1) {
            logger.error("超过单日可申购最高额度");
            getProcessingException(confirmExpectationException,"0000");
//             个人当日累计购买最大金额 indidaymaxsumbuy
        } else if (Confirmedamount.compareTo(fundParaConfig.getIndidaymaxsumbuy()) == 1) {
            logger.error("超过个人当日累计购买最大金额");
            getProcessingException(confirmExpectationException,"0000");
//            再判断自己加上之前的金额是否超过个人最大申购
        } else if ((Confirmedamount.add(acctShare.getTotalamountofdistributorinta())).compareTo(fundParaConfig.getIndidaymaxsumbuy()) == 1) {
            logger.error("再判断自己加上之前的金额是否超过个人最大申购");
            getProcessingException(confirmExpectationException,"0000");
        } else {
//                增加的金额
            acctShare.setTotalamountofdistributorinta(acctShare.getTotalamountofdistributorinta().add(Confirmedamount));
//                增加的份额
            acctShare.setTotalfrozenvol(acctShare.getTotalfrozenvol().add(Confirmedamount.divide(fundParaConfig.getNav())));
        }

    }

    // 生成确认记录，不同业务需具体实现
    @Override
    void generateConfirm(ApplicationModel apply, ConfirmationModel confirm, ApplyException applyException) {

//      业务流程更新confirm
        TransactionApplication transactionApplication = (TransactionApplication) apply;
        TransactionConfirmation transactionConfirmation = (TransactionConfirmation) confirm;
        FundParaConfigDao fundParaConfigDao = FactoryDao().getFundParaConfigDao();
        TransactionConfirmationDao transactionConfirmationDao = FactoryDao().getTransactionConfirmationDao();
        AcctShareDao acctShareDao = FactoryDao().getAcctShareDao();
        FundParaConfig fundParaConfig = new FundParaConfig();


        fundParaConfig.setFundcode(transactionApplication.getFundcode());
        fundParaConfig.setTacode(transactionApplication.getTacode());
        fundParaConfig.setDistributorcode(transactionApplication.getDistributorcode());

        FundParaConfig paraConfig = fundParaConfigDao.selectByUnionCode(fundParaConfig);
        BigDecimal nav = paraConfig.getNav();


        AcctShareKey acctShareKey = new AcctShareKey();
        copyFields(transactionConfirmation, acctShareKey);
        AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);

        BigDecimal shareTotalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
        if (transactionApplication.getApplicationvol()==(transactionApplication.getApplicationamount().subtract(fundParaConfig.getNav()))){
            acctShare.setTotalvolofdistributorinta(shareTotalvolofdistributorinta.add(transactionApplication.getApplicationvol()));
        }

        copyFields(transactionApplication, transactionConfirmation);

        //生成交易确认成功记录（122）
        transactionConfirmationDao.insert(transactionConfirmation);
        //更新或新增账户持仓表
        acctShareDao.updateByPrimaryKey(acctShare);
        //更新产品已售额度等
        fundParaConfigDao.updateByPrimaryKey(paraConfig);
    }


    // 根据业务逻辑更新对应库表
    @Override
    void updateRepository(ApplicationModel apply, List<ConfirmationModel> confirm, ApplyException applyException) {
        FactoryDao().getTransactionApplicationDao().updateByPrimaryKey((TransactionApplication) apply);
        List<TransactionConfirmation> transactionConfirmationList = Collections.singletonList((TransactionConfirmation) confirm);
        for (TransactionConfirmation transactionConfirmation : transactionConfirmationList) {
            FactoryDao().getTransactionConfirmationDao().updateByPrimaryKey(transactionConfirmation);
        }


        TransactionApplication transactionApplication = (TransactionApplication) apply;
        TransactionConfirmationDao transactionConfirmationDao = FactoryDao().getTransactionConfirmationDao();
        AcctShareDao acctShareDao = FactoryDao().getAcctShareDao();
        FundParaConfigDao fundParaConfigDao = FactoryDao().getFundParaConfigDao();


        FundParaConfig fundParaConfig = new FundParaConfig();
        fundParaConfig.setFundcode(transactionApplication.getFundcode());
        fundParaConfig.setTacode(transactionApplication.getTacode());
        fundParaConfig.setDistributorcode(transactionApplication.getDistributorcode());
        //        查询当前申购对应的产品
        FundParaConfig paraConfig = fundParaConfigDao.selectByUnionCode(fundParaConfig);
        //        产品额度更新
        BigDecimal nav = paraConfig.getNav();
//        paraConfig.setFundmaxbala(paraConfig.getFundmaxbala().add((nav.multiply(transactionApplication.getApplicationvol()))));
        AcctShareKey acctShareKey = new AcctShareKey();

        for (ConfirmationModel c : confirm
        ) {
            TransactionConfirmation transactionConfirmation = (TransactionConfirmation) c;

//            当确认记录的业务代码为124 时更新 持仓与 产品 表
            if (transactionConfirmation.getBusinesscode().equals("1" + transactionApplication.getBusinesscode().substring(1))) {
//                当返回码为"0000"成功时
                if (transactionConfirmation.getReturncode().equals("0000")) {
                    copyFields(transactionConfirmation, acctShareKey);
                    AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);
                    //   当前持仓金额
                    BigDecimal totalamountofdistributorinta = acctShare.getTotalamountofdistributorinta();
//                    当前持仓的总份额
                    BigDecimal totalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
                    //    当前持仓金额
//                    持仓金额更新  当前 总金额=总金额+申购金额
                    acctShare.setTotalamountofdistributorinta(totalamountofdistributorinta.add(transactionConfirmation.getAchievementcompen()));

                    //    持仓份额更新  当前 总持仓=总持仓+申购份额
                    acctShare.setTotalvolofdistributorinta(totalvolofdistributorinta.add(transactionApplication.getApplicationvol()));
                    //    持仓金额更新  当前 总金额=总持仓+申购金额
                    acctShare.setTotalfrozenamount(totalamountofdistributorinta.subtract(transactionApplication.getApplicationvol()));

                    try {
                        //更新或新增账户持仓表
                        acctShareDao.updateByPrimaryKey(acctShare);
                        //更新产品已售额度等
                        fundParaConfigDao.updateByPrimaryKey(paraConfig);
                    } catch (Exception e) {
                        logger.error(e);
                    }

                }
            }

            try {
                //生成交易确认记录
                transactionConfirmationDao.insert(transactionConfirmation);
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }


    //      用于增加天数
    public static String addDay(String time, int addDay) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        Date date = ft.parse(time);
        String format = ft.format(new Date(date.getTime() + addDay * 24 * 60 * 60 * 1000));
        return format;
    }
}
