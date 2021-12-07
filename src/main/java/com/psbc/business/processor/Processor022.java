package com.psbc.business.processor;

import com.nlf.calendar.util.HolidayUtil;
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
import com.psbc.utils.DateAndTimeUtil;
import com.psbc.utils.helper.XMLParser;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.Exception;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.psbc.business.service.ObjectProcessor.copyFields;

import static com.psbc.utils.DateAndTimeUtil.*;
import static com.psbc.utils.DateAndTimeUtil.getNowDate;
/**
 * @author Dealyz
 * @date 2021年11月30日
 */
@Data
@Component
public class Processor022 extends BiDirectionProcessor {
    private static final Logger logger = Logger.getLogger(Processor022.class);

    public RepositoryFactory FactoryDao() {
        RepositoryFactory repositoryFactory = SpringContextUtil.getBean(RepositoryFactory.class);
        return repositoryFactory;
    }


    public void getProcessingException(ProcessingException exception, String code) {

        String description = ReturnCodeDescription(code);
        exception.setReturncode(code);
        exception.setErrortype("1");
        exception.setSpeification(description);
        logger.error(description);
    }
    public String ReturnCodeDescription(String code) {
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

//    用于判断是否为周末和节假日
    public String getNextTransactionDate(String transactionDate) {
        String  transactionCfdate=transactionDate;
        while (isWeekend(transactionCfdate)){
            try {
                transactionCfdate = DateAndTimeUtil.addDay(transactionCfdate, 1);
            } catch (ParseException e) {
//                getProcessingException(applyException,"0000");
                logger.error(e);
            }
        }
        //        获取交易日期配置表，获取延迟确认天数
        //        获取工作日日历表
        if (HolidayUtil.getHoliday(transactionCfdate) != null) {
            transactionCfdate=getNextTransactionDayFromDB(transactionDate);
        }

        return transactionCfdate;
    }
//    public void validateApplyFromXML(ApplicationModel apply) throws ApplyException {
//
//        TransactionApplication transactionApplication = (TransactionApplication) apply;
//        ApplyException applyException =  new ApplyException();
////        ApplyException applyException = (ApplyException) new ProcessingException();
//        copyFields(transactionApplication, applyException);
//        String businesscode = transactionApplication.getBusinesscode();
//
//        XMLNode business_configs = XMLParser.parseXml(".\\src\\main\\resources\\xml\\business_configs\\BUSINESSCODE.xml".replace("BUSINESSCODE", businesscode));
//        List<XMLNode> childrenNodes = business_configs.getChildrenNodes();
//        for (XMLNode c : childrenNodes
//        ) {
//            Map<String, String> attributes = c.getAttributes();
//            Set<String> keySet = attributes.keySet();
//            for (String key : keySet
//            ) {
//                if (key.equals("fieldName")) {
//                    try {
//                        String fieldName = attributes.get(key).toLowerCase();
//                        String required = attributes.get("required");
//                        Field field = transactionApplication.getClass().getDeclaredField(fieldName);
//                        field.setAccessible(true);
//
//                        Object o = invokeGetMethod(transactionApplication, field.getName(), null);
//                        if ((required.equals(true) && o== null)||(required.equals(true) &&"".equals(o.toString()))) {
//                            logger.error(fieldName + "字段缺失");
//                            applyException.setReturncode("9999");
//                            applyException.setErrortype("1");
//                            applyException.setSpeification(fieldName + "字段缺失");
//                            logger.error(fieldName + "字段缺失");
//                            throw applyException;
//                        }
//                    } catch (NoSuchFieldException e) {
//                        logger.error(e);
//                    }
//                }
//            }
//        }
//
//
//    }


    public List<TransactionApplication> getApplyList() {
        TransactionApplicationDao applicationDao = FactoryDao().getTransactionApplicationDao();
        List<TransactionApplication> transactionApplications = applicationDao.selectAll();
        return transactionApplications;
    }


    // 判断申请记录的业务合法性，不同业务需具体实现;
    // 若合法，不做任何返回；若不合法，抛对应异常

    @Override
    void validateApply(ApplicationModel apply) throws ApplyException {
        //  检查必要字段不为空,根据对应业务的XML解析是否required
//        validateApplyFromXML(apply);


        TransactionApplication transactionApplication = (TransactionApplication) apply;
        ApplyException applyException=new ApplyException();
        copyFields(apply,applyException);

        ApplyFormatValidator applyFormatValidator = SpringContextUtil.getBean(ApplyFormatValidator.class);

        try {
            applyFormatValidator.validateFieldRequirement(transactionApplication);
            applyFormatValidator.validateFieldFormat(transactionApplication);
        }catch (ApplyException e){
//            交易请求报文格式错误
            CommonProcessUtils.getProcessingException(e,"3105",logger);
            logger.error("申请记录字段格式不正确");
            throw e;
        }

        String transactiondate = transactionApplication.getTransactiondate();
        Double transactiontime = Double.valueOf(transactionApplication.getTransactiontime());
        BigDecimal.valueOf(Long.valueOf(transactionApplication.getTransactiondate()));
        BigDecimal applicationamount = transactionApplication.getApplicationamount();
        //判断是否下午三点切日，如果切日日期加一天
        String CHANGEDTE = "150000";
        String transactionCfdate=transactiondate;
        if (Double.valueOf(CHANGEDTE) < transactiontime) {
            String  transactiondateHead= transactiondate.substring(0,7);
            String day = String.valueOf(Integer.parseInt(transactiondate.substring(7)) + 1);
            transactionCfdate=transactiondateHead+day;
        }


        AcctShareKey acctShareKey = new AcctShareKey();
        copyFields(transactionApplication, acctShareKey);
        AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);


        FundParaConfig fundParaConfig = new FundParaConfig();
        copyFields(transactionApplication, fundParaConfig);
        fundParaConfig = FactoryDao().getFundParaConfigDao().selectByUnionCode(fundParaConfig);
//        申购日期不在申请期限内
//        if (Double.valueOf(transactiondate) > Double.valueOf(fundParaConfig.getFundestablishdate())) {
//            logger.error("申请日期不在申购日期期限中");
//            getProcessingException(applyException,"1864");
//            throw applyException;
//        }


//        判断申购是否为首次购买 AppSheetSerialNo 通过申请单编号
//        @TODO
        TransactionConfirmation transactionConfirmation = new TransactionConfirmation();
        copyFields(transactionApplication,transactionConfirmation);
        String confirmationAppsheetserialno = transactionConfirmation.getAppsheetserialno();
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
                getProcessingException(applyException,"1393");
                //            再判断是否超过单日可申购最高额度buyupperamount
            } else if (applicationamount.compareTo(fundParaConfig.getBuyupperamount()) == 1) {
                logger.error("超过单日可申购最高额度");
                getProcessingException(applyException,"1393");
//             个人当日累计购买最大金额 indidaymaxsumbuy
            } else if (applicationamount.compareTo(fundParaConfig.getIndidaymaxsumbuy()) == 1) {
                logger.error("超过个人当日累计购买最大金额");
                getProcessingException(applyException,"1393");
//            再判断自己加上之前的金额是否超过个人最大申购

            } else if ((applicationamount.add(acctShare.getTotalamountofdistributorinta())).compareTo(fundParaConfig.getIndidaymaxsumbuy()) == 1) {
                logger.error("再判断自己加上之前的金额是否超过个人最大申购");
                getProcessingException(applyException,"1393");
            } else {
//                增加的金额
                BigDecimal add = acctShare.getTotalamountofdistributorinta().add(applicationamount);
                acctShare.setTotalamountofdistributorinta(add);
//                增加的份额
                acctShare.setTotalfrozenvol(acctShare.getTotalfrozenvol().add(applicationamount.divide(fundParaConfig.getNav())));
            }
        }

//            当前净值类型非申购类型下   个人购买额度超过总额度
        if (fundParaConfig.getNetvaluetype().equals("1")) {
            BigDecimal nav = fundParaConfig.getNav();
//           2为有效位数
            BigDecimal share = applicationamount.divide(nav, 2, BigDecimal.ROUND_HALF_UP);
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


//        while (isHoliday) {
//            holiday.setDay(transactionDate);
//            List<Holiday> holidays = FactoryDao().getHolidayDao().selectByCondition(holiday);
//            if (holidays != null) {
//                try {
//                    transactionDate = addDay(transactionDate, 1);
//                } catch (ParseException e) {
//                    getProcessingException(applyException,"0000");
//                    logger.error(e);
//                }
//            } else {
//                isHoliday = false;
//            }
//        }
        transactionCfdate = DateAndTimeUtil.getNextTransactionDate(transactionCfdate);


    }


    @Override
    void validateConfirmExpectation(ExpectationModel expect) throws ConfirmExpectationException {
        TransactionExpectation transactionExpectation = (TransactionExpectation) expect;

        ConfirmExpectationException confirmExpectationException=new ConfirmExpectationException();
        copyFields(expect,confirmExpectationException);
        BigDecimal Confirmedamount = transactionExpectation.getConfirmedamount();
        String transactiondate = transactionExpectation.getTransactiondate();
        String transactiontime = transactionExpectation.getTransactiontime();

        String CHANGEDTE = "150000";
        String transactionCfdate=transactiondate;
        if (Double.valueOf(CHANGEDTE) < Double.valueOf(transactiontime)) {
            String  transactiondateHead= transactiondate.substring(0,7);
            String day = String.valueOf(Integer.parseInt(transactiondate.substring(7)) + 1);
            transactionCfdate=transactiondateHead+day;
        }
        transactionExpectation.setTransactioncfmdate(transactionCfdate);

        FundDateKey fundDateKey = new FundDateKey();
        copyFields(transactionExpectation, fundDateKey);
        FundDate fundDate = FactoryDao().getFundDateDao().selectByPrimaryKey(fundDateKey);

        //      查找当前申购期望 的持仓表记录
        AcctShareKey acctShareKey = new AcctShareKey();
//        copyFields(transactionExpectation,acctShareKey);
        TransactionApplication transactionApplication = new TransactionApplication();
        copyFields(transactionExpectation,transactionApplication);
        transactionApplication = FactoryDao().getTransactionApplicationDao().selectByPrimaryKey(transactionApplication);
        copyFields(transactionApplication,acctShareKey);


        AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);

        //        理财产品总金额（含冻结）
        BigDecimal totalamountofdistributorinta = acctShare.getTotalamountofdistributorinta();
        FundParaConfigDao fundParaConfigDao = SpringContextUtil.getBean(FundParaConfigDao.class);
        FundParaConfig fundParaConfig1 = new FundParaConfig();
        copyFields(transactionApplication,fundParaConfig1);
        FundParaConfig fundParaConfig = fundParaConfigDao.selectByUnionCode(fundParaConfig1);


        totalamountofdistributorinta.add(transactionExpectation.getConfirmedamount());

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

    // 根据业务逻辑更新对应库表
    @Override
    @Transactional
    void updateRepository(ApplicationModel apply, List<ConfirmationModel> confirm, ApplyException applyException) {
        FactoryDao().getTransactionApplicationDao().updateByPrimaryKey((TransactionApplication) apply);
        for (ConfirmationModel transactionConfirmation : confirm) {
            TransactionConfirmation confirmation = (TransactionConfirmation) transactionConfirmation;
            FactoryDao().getTransactionConfirmationDao().updateByPrimaryKey(confirmation);
        }


        TransactionApplication transactionApplication = (TransactionApplication) apply;
        TransactionConfirmationDao transactionConfirmationDao = FactoryDao().getTransactionConfirmationDao();
        AcctShareDao acctShareDao = FactoryDao().getAcctShareDao();
        FundParaConfigDao fundParaConfigDao = FactoryDao().getFundParaConfigDao();


        FundParaConfig fundParaConfig = new FundParaConfig();
        copyFields(transactionApplication,fundParaConfig);
        //        查询当前申购对应的产品
        FundParaConfig paraConfig = fundParaConfigDao.selectByUnionCode(fundParaConfig);
        //        产品额度更新
        BigDecimal nav = paraConfig.getNav();
//        paraConfig.setFundmaxbala(paraConfig.getFundmaxbala().add((nav.multiply(transactionApplication.getApplicationvol()))));
        AcctShareKey acctShareKey = new AcctShareKey();

        for (ConfirmationModel c : confirm
        ) {
            TransactionConfirmation transactionConfirmation = (TransactionConfirmation) c;

//            当确认记录的业务代码为122 时更新 持仓与 产品 表
            if (transactionConfirmation.getBusinesscode().equals("1" + transactionApplication.getBusinesscode().substring(1))) {
//                当返回码为"0000"成功时
                if (transactionConfirmation.getReturncode().equals("0000")) {
                    copyFields(transactionApplication, acctShareKey);
                    AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);
                    //   当前持仓金额
                    BigDecimal totalamountofdistributorinta = acctShare.getTotalamountofdistributorinta();
//                    当前持仓的总份额
                    BigDecimal totalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
                    //    当前持仓金额
//                    持仓金额更新  当前 总金额=总金额+申购金额
                    acctShare.setTotalamountofdistributorinta(totalamountofdistributorinta.add(transactionApplication.getApplicationamount()));

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


    // 生成确认记录，不同业务需具体实现
    @Override
    void generateConfirm(ApplicationModel apply, ConfirmationModel confirm, ApplyException applyException){

//      业务流程更新confirm
        TransactionApplication transactionApplication = (TransactionApplication) apply;
        TransactionConfirmation transactionConfirmation = (TransactionConfirmation) confirm;
//        get DAO
        FundParaConfigDao fundParaConfigDao = FactoryDao().getFundParaConfigDao();
        TransactionConfirmationDao transactionConfirmationDao = FactoryDao().getTransactionConfirmationDao();
        AcctShareDao acctShareDao = FactoryDao().getAcctShareDao();

        FundParaConfig fundParaConfig = new FundParaConfig();
        copyFields(transactionApplication,fundParaConfig);

        FundParaConfig paraConfig = fundParaConfigDao.selectByUnionCode(fundParaConfig);
        BigDecimal nav = paraConfig.getNav();


        AcctShareKey acctShareKey = new AcctShareKey();
        copyFields(transactionApplication, acctShareKey);
        AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);


        String transactiondate = transactionConfirmation.getTransactiondate();
        copyFields(transactionApplication, transactionConfirmation);
        //      获得交易日日期
        if (applyException != null && applyException.getReturncode() != null) {
            transactionConfirmation.setReturncode(applyException.getReturncode());

        } else {
            transactionConfirmation.setReturncode("0000");
        }
//     周末和节假日排除
        String nextTransactionDate = DateAndTimeUtil.getNextTransactionDate(transactionApplication.getTransactiondate());

        transactionConfirmation.setConfirmedvol(transactionApplication.getApplicationamount().divide(paraConfig.getNav()));
        transactionConfirmation.setTransactioncfmdate(nextTransactionDate);
        transactionConfirmation.setTransactiondate(transactionApplication.getTransactiondate());
        transactionConfirmation.setBusinesscode("1" + transactionApplication.getBusinesscode().substring(1));

        BigDecimal shareTotalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
        if (transactionApplication.getApplicationvol()==(transactionApplication.getApplicationamount().subtract(paraConfig.getNav()))){
            acctShare.setTotalvolofdistributorinta(shareTotalvolofdistributorinta.add(transactionApplication.getApplicationvol()));
        }

        transactionConfirmation.setTransactiondate(getNextTransactionDayFromDB(getNowDate()));
        transactionConfirmation.setTransactioncfmdate(getNextTransactionDayFromDB(getNowDate()));
        transactionConfirmation.setBusinesscode("1" + transactionApplication.getBusinesscode().substring(1));

        //生成交易确认成功记录（122）
//        transactionConfirmationDao.insert(transactionConfirmation);
        //更新或新增账户持仓表
        acctShareDao.updateByPrimaryKey(acctShare);
        //更新产品已售额度等
        fundParaConfigDao.updateByPrimaryKey(paraConfig);
    }






}
