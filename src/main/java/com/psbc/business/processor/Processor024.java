package com.psbc.business.processor;

import com.nlf.calendar.util.HolidayUtil;
import com.psbc.business.service.RepositoryFactory;
import com.psbc.business.service.SpringContextUtil;
import com.psbc.exceptions.ApplyException;
import com.psbc.exceptions.ConfirmExpectationException;
import com.psbc.exceptions.ProcessingException;
import com.psbc.mapper.AcctShareDao;
import com.psbc.mapper.FundParaConfigDao;
import com.psbc.mapper.TransactionApplicationDao;
import com.psbc.mapper.TransactionConfirmationDao;
import com.psbc.pojo.*;
import com.psbc.reader.xmlModel.XMLNode;
import com.psbc.utils.helper.XMLParser;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.Exception;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.psbc.business.service.CommonProcessUtils.FactoryDao;
import static com.psbc.business.service.ObjectProcessor.copyFields;
import static com.psbc.business.service.RecordOperator.invokeGetMethod;
import static com.psbc.utils.DateAndTimeUtil.*;

/**
 * @author Huilin Tong
 * @date 2021年11月30日 14:23
 */
@Data
@Component
public class Processor024 extends BiDirectionProcessor {
    private static final Logger logger = Logger.getLogger(Processor024.class);

    public static void validateApplyFromXML(ApplicationModel apply, Logger logger) throws ApplyException {

        TransactionApplication transactionApplication = (TransactionApplication) apply;

        ApplyException applyException = new ApplyException();
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
                        Object o = invokeGetMethod(transactionApplication, field.getName(), null);
                        if ((required.equals("true") && o == null) || (required.equals("true") && o.toString().equals(""))) {
                            logger.error(fieldName + "字段缺失");
                            applyException.setReturncode("9999");
                            applyException.setErrortype("1");
                            applyException.setSpeification(fieldName + "字段缺失");
                            logger.error(fieldName + "字段缺失");
                            throw applyException;
                        }
                    } catch (NoSuchFieldException e) {
                        logger.error(e);
                    }
                }
            }
        }


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

    public static ProcessingException getProcessingException(ProcessingException exception, String code) {

        String description = ReturnCodeDescription(code);
        exception.setReturncode(code);
        exception.setErrortype("1");
        exception.setSpeification(description);
        logger.error(description);
        return exception;
    }

    public static List<TransactionApplication> getApplyList() {
        TransactionApplicationDao applicationDao = FactoryDao().getTransactionApplicationDao();
        List<TransactionApplication> transactionApplications = applicationDao.selectAll();
        return transactionApplications;
    }


    //  赎回必输字段校验，
    //  金额、日期等等
    @Override
    void validateApply(ApplicationModel apply) throws ApplyException {
        //  检查必要字段不为空,根据对应业务的XML解析是否required
        validateApplyFromXML(apply,logger);

        TransactionApplication transactionApplication = (TransactionApplication) apply;

        ApplyException applyException = new ApplyException();
        copyFields(transactionApplication, applyException);

        //  @TODO 个人单笔最少赎回份额 PMinRedemptionVol
        //  @TODO 个人当日累计赎回最大份额 IndiMaxRedeem

        //  @TODO 赎回期内判断
        //  @TODO 币种 产品收益币种 IncomeCurrType 本金返还币种 CostCurrType

        //  @TODO 收费类型 ShareClass
        //  @TODO 收费方式判断、是否扣除手术费

        //  @TODO 理财最高赎回份数 MaxRedemptionVol
        //  @TODO 理财最低持有份数 MinAccountBalance

        String transactiondate = transactionApplication.getTransactiondate();
        String transactiontime = transactionApplication.getTransactiontime();

//        赎回份数
        BigDecimal applicationvol = transactionApplication.getApplicationvol();

//      查找当前赎回申请 的持仓表记录
        AcctShareKey acctShareKey = new AcctShareKey();
        copyFields(transactionApplication, acctShareKey);
        AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);

//        查找当前赎回的对应产品参数
        FundParaConfig fundParaConfig = new FundParaConfig();
        copyFields(transactionApplication, fundParaConfig);
        fundParaConfig = FactoryDao().getFundParaConfigDao().selectByUnionCode(fundParaConfig);
//        当前净值
        BigDecimal nav = fundParaConfig.getNav();

//        理财产品总份数（含冻结）
        BigDecimal totalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
//        理财产品冻结总份数
        BigDecimal totalfrozenvol = acctShare.getTotalfrozenvol();

//        理财产品实际可用总份数
        BigDecimal shareVol = totalvolofdistributorinta.subtract(totalfrozenvol);


//        a = -1,表示 shareAmount 小于 applyAmount；
//        a =  0,表示 shareAmount 等于 applyAmount；
//        a =  1,表示 shareAmount 大于 applyAmount；
        if (shareVol.compareTo(applicationvol) < 0) {
//            超过当前额度，赎回异常
            ApplyException processingException = (ApplyException) getProcessingException(applyException, "1386");
            throw processingException;
        }
//
        if (Double.valueOf(transactiondate + transactiontime) > Double.valueOf(getFullNowDateTime())) {
            ApplyException processingException = (ApplyException) getProcessingException(applyException, "1864");
            throw processingException;
        }
        applyException.setReturncode("0000");


    }

    @Override
    void validateConfirmExpectation(ExpectationModel expect) throws ConfirmExpectationException {

        //        读取期望配置表（获取失败信息）
        TransactionExpectation transactionExpectation = (TransactionExpectation) expect;

        ConfirmExpectationException confirmExpectationException = new ConfirmExpectationException();
        copyFields(transactionExpectation, confirmExpectationException);

        String transactiondate = transactionExpectation.getTransactiondate();
        String transactiontime = transactionExpectation.getTransactiontime();
        //        赎回份数
        BigDecimal applicationvol = transactionExpectation.getConfirmedvol();
        //        赎回金额
        BigDecimal confirmedamount = transactionExpectation.getConfirmedamount();
        //        单位净值
        BigDecimal nav = transactionExpectation.getNav();

        //  @TODO 赎回最小份数的判断
        //  @TODO 当日最大赎回份数的判断

        if (Double.valueOf(transactiondate + transactiontime) > Double.valueOf(getFullNowDateTime())) {

            ConfirmExpectationException processingException = (ConfirmExpectationException) getProcessingException(confirmExpectationException, "1864");
            throw processingException;
        }
        //        获取交易日期配置表，获取延迟确认天数
        //        获取工作日日历表
        if (HolidayUtil.getHoliday(transactionExpectation.getTransactioncfmdate()) != null) {
            ConfirmExpectationException processingException = (ConfirmExpectationException) getProcessingException(confirmExpectationException, "1864");
            throw processingException;
        }

        //      查找当前赎期望的持仓表记录
        //      读取产品表（获取个人额度，产品额度）
        //      读取账户信息表（获取个人已购额度）
        AcctShareKey acctShareKey = new AcctShareKey();
        TransactionApplication transactionApplication = new TransactionApplication();

        copyFields(transactionExpectation, transactionApplication);
        copyFields(transactionExpectation, acctShareKey);

        transactionApplication = FactoryDao().getTransactionApplicationDao().selectByPrimaryKey(transactionApplication);
        acctShareKey.setTaaccountid(transactionApplication.getTaaccountid());
        acctShareKey.setFundcode(transactionApplication.getFundcode());

        AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);

        //        理财产品总份数（含冻结）
        BigDecimal totalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
        //        理财产品冻结总份数
        BigDecimal totalfrozenvol = acctShare.getTotalfrozenvol();

        //        理财产品实际可用总份数
        BigDecimal shareVol = totalvolofdistributorinta.subtract(totalfrozenvol);

        if (shareVol.compareTo(applicationvol) < 0) {
            ConfirmExpectationException processingException = (ConfirmExpectationException) getProcessingException(confirmExpectationException, "1386");
            throw processingException;
        }


    }

    @Override
    @Transactional
    void updateRepository(ApplicationModel apply, List<ConfirmationModel> confirm, ApplyException applyException) {

        try {
            TransactionApplication transactionApplication = (TransactionApplication) apply;
            TransactionConfirmationDao transactionConfirmationDao = FactoryDao().getTransactionConfirmationDao();
            AcctShareDao acctShareDao = FactoryDao().getAcctShareDao();
            FundParaConfigDao fundParaConfigDao = FactoryDao().getFundParaConfigDao();
            FundParaConfig fundParaConfig = new FundParaConfig();
            fundParaConfig.setFundcode(transactionApplication.getFundcode());
            fundParaConfig.setTacode(transactionApplication.getTacode());
            fundParaConfig.setDistributorcode(transactionApplication.getDistributorcode());
            //        查询当前赎回对应的产品
            FundParaConfig paraConfig = fundParaConfigDao.selectByUnionCode(fundParaConfig);

            for (ConfirmationModel c : confirm
            ) {
                //        产品额度更新
                BigDecimal nav = paraConfig.getNav();
                paraConfig.setFundmaxbala(paraConfig.getFundmaxbala().add((nav.multiply(transactionApplication.getApplicationvol()))));
                AcctShareKey acctShareKey = new AcctShareKey();

                TransactionConfirmation transactionConfirmation = (TransactionConfirmation) c;

                // 当确认记录的业务代码为124 时更新 持仓与 产品 表
                if (transactionConfirmation.getReturncode() != null && transactionConfirmation.getBusinesscode() != null) {
//                当返回码为"0000"成功时
                    if (transactionConfirmation.getBusinesscode().equals("1" + transactionApplication.getBusinesscode().substring(1))) {
                        if (transactionConfirmation.getReturncode().equals("0000")) {
                            copyFields(transactionConfirmation, acctShareKey);

                            transactionApplication = FactoryDao().getTransactionApplicationDao().selectByPrimaryKey(transactionApplication);

                            acctShareKey.setTaaccountid(transactionApplication.getTaaccountid());
                            acctShareKey.setFundcode(transactionApplication.getFundcode());

                            AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);
                            //   当前持仓份数
                            BigDecimal shareTotalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
                            //    当前持仓金额
                            BigDecimal totalamountofdistributorinta = acctShare.getTotalamountofdistributorinta();

                            //    持仓份额更新  当前 总持仓=总持仓-赎回份额
                            acctShare.setTotalvolofdistributorinta(shareTotalvolofdistributorinta.subtract(transactionConfirmation.getConfirmedvol()));
                            //    持仓金额更新  当前 总金额=总持仓-赎回份额*净值
                            acctShare.setTotalamountofdistributorinta(totalamountofdistributorinta.subtract(transactionConfirmation.getConfirmedvol().multiply(nav)));
                            transactionConfirmation.setConfirmedamount(transactionConfirmation.getConfirmedvol().multiply(nav));
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
                }

                try {
                    //生成交易确认记录
                    transactionConfirmationDao.insert(transactionConfirmation);
                    if (applyException != null) {
                        FactoryDao().getExceptionDao().insert(applyException);
                    }
                } catch (Exception e) {
                    logger.error(e);
                }
            }

        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    void generateConfirm(ApplicationModel apply, ConfirmationModel confirm, ApplyException applyException) {
        TransactionApplication transactionApplication = (TransactionApplication) apply;
        TransactionConfirmation transactionConfirmation = (TransactionConfirmation) confirm;
        if (transactionConfirmation.getReturncode() == null) {

            String transactiondate = transactionConfirmation.getTransactiondate();
            copyFields(transactionApplication, transactionConfirmation);
            //      获得交易日日期
            if (applyException != null && applyException.getReturncode() != null) {
                transactionConfirmation.setReturncode(applyException.getReturncode());

            } else {
                transactionConfirmation.setReturncode("0000");
            }

            transactionConfirmation.setConfirmedvol(transactionApplication.getApplicationvol());
            transactionConfirmation.setTransactioncfmdate(getNextTransactionDayFromDB(transactiondate));
            transactionConfirmation.setTransactiondate(getNextTransactionDayFromDB(transactiondate));
            transactionConfirmation.setBusinesscode("1" + transactionApplication.getBusinesscode().substring(1));

        }
//      TODO 改进确认日期的生成
        transactionConfirmation.setTransactiondate(getNextTransactionDayFromDB(getNowDate()));
        transactionConfirmation.setTransactioncfmdate(getNextTransactionDayFromDB(getNowDate()));
        transactionConfirmation.setBusinesscode("1" + transactionApplication.getBusinesscode().substring(1));


    }
}
