package com.psbc.business.processor;

import com.nlf.calendar.util.HolidayUtil;
import com.psbc.business.service.*;
import com.psbc.exceptions.ApplyException;
import com.psbc.exceptions.ConfirmExpectationException;
import com.psbc.pojo.*;
import com.psbc.pojo.ApplicationModel;
import com.psbc.pojo.ConfirmationModel;
import com.psbc.pojo.ExpectationModel;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
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


    // 判断申请记录的业务合法性，不同业务需具体实现;
    // 若合法，不做任何返回；若不合法，抛对应异常

    @Override
    void validateApply(ApplicationModel apply) throws ApplyException {

        TransactionApplication transactionApplication = (TransactionApplication) apply;
        Double transactiondate = Double.valueOf(transactionApplication.getTransactiondate());

        Double transactiontime = Double.valueOf(transactionApplication.getTransactiontime());
        //判断是否下午三点切日，如果切日日期加一天
        String CHANGEDTE = "150000";
        if (Double.valueOf(CHANGEDTE) < transactiontime) {
            transactiontime += 1;
        }

        if (transactiondate + transactiontime > Double.valueOf(getFullNowDateTime())) {
            logger.error("申请日期不在申购日期期限中");
            throw new ApplyException();
        }

        if (transactionApplication.getAppsheetserialno() == null ||
                transactionApplication.getDistributorcode() == null || transactionApplication.getFundcode() == null) {
            logger.error("必要字段存在为空");
            throw new ApplyException();
        }


        //   个人购买额度是否可购买最高额度
        FundParaConfig fundParaConfig = FactoryDao().getFundParaConfigDao().selectByPrimaryKey(Integer.valueOf(transactionApplication.getFundcode()));
        if (!(fundParaConfig.getBuyupperamount() == null) || !(fundParaConfig.getBuyupperamount()).equals(0)) {
            BigDecimal applicationamount = transactionApplication.getApplicationamount();
            BigDecimal buyupperamount = fundParaConfig.getBuyupperamount();
            if (applicationamount.compareTo(buyupperamount) == 1) {
                logger.error("个人购买额度超过最高额度");
                throw new ApplyException();
            }
        }

        AcctShareKey acctShareKey = new AcctShareKey();
        copyFields(transactionApplication, acctShareKey);
        AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);
//            当前净值类型非申购类型下   个人购买额度超过总额度
        if (fundParaConfig.getNetvaluetype().equals(1)) {
            BigDecimal nav = fundParaConfig.getNav();
            BigDecimal applicationamount = transactionApplication.getApplicationamount();
            BigDecimal share = applicationamount.divide(nav, 20, BigDecimal.ROUND_HALF_UP);
            BigDecimal totalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
//                个人购买额度超过总额度 ==1为左边>右边超过 ==0表示左边=右边  ==-1左边<右边
            if (totalvolofdistributorinta.compareTo(share) == 1) {
                logger.error("个人购买额度超过总额度");
                throw new ApplyException();
            } else {
//                    @TODO 存储 确认的份额
//                    share
            }
        } else {
            logger.error("当前净值类型非申购类型");
            throw new ApplyException();
        }


    }

    // 判断所获期望是否合理，不同业务需具体实现
    @Override
    void validateConfirmExpectation(ExpectationModel expect) throws ConfirmExpectationException{
        TransactionExpectation transactionExpectation = (TransactionExpectation) expect;
        //        -- Table structure for acct_share（账户份额表）
//        -- Table structure for fund_date（产品日历表）
//        -- Table structure for fund_info（产品基本信息表）
//        -- Table structure for fund_para（产品参数表）

//        acct_share（账户份额表）
        AcctShareKey acctShareKey = new AcctShareKey();
        AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);


//        读取期望配置表（获取失败信息）
//        获取交易日期配置表，获取延迟确认天数  获取工作日日历表
//        读取产品表（获取个人额度，产品额度）
//        读取账户信息表（获取个人已购额度）


//          fund_date（产品日历表）
//        设定确认日期
        FundDateKey fundDateKey = new FundDateKey();
        copyFields(transactionExpectation, fundDateKey);
        FundDate fundDate = FactoryDao().getFundDateDao().selectByPrimaryKey(fundDateKey);

        //        @TODO 需要更改
        String transactioncfmdate =transactionExpectation.getTransactioncfmdate();
        String transactionDate = transactioncfmdate.substring(0,8);
        String transactionYear = transactionDate.substring(0,4);
        String transactionMonth = transactionDate.substring(4,6);
        String transactionDay = transactionDate.substring(6,8);
        String transactionDateNew=transactionDate;
        while (HolidayUtil.getHoliday(Integer.valueOf(transactionYear),Integer.valueOf(transactionMonth),Integer.valueOf(transactionDay))!=null){
            try {
                transactionDateNew = addDay(transactionDateNew, 1);
                transactionYear = transactionDateNew.substring(0,4);
                transactionMonth = transactionDateNew.substring(4,6);
                transactionDay = transactionDateNew.substring(6,8);
            }catch (ParseException e){
                logger.error(e);
            }
        }
//        判断日期是节假日 则开始转到节假日后的工作日
        if (transactionDateNew!=transactionDate){
            transactioncfmdate=transactionDateNew+transactioncfmdate.substring(9);
            fundDate.setTransactioncfmdate(transactioncfmdate);
        }
        fundDate.setTransactioncfmdate(transactioncfmdate);
        FactoryDao().getFundDateDao().updateByPrimaryKey(fundDate);


//        fund_para（产品参数表
        FundParaConfig fundParaConfig = FactoryDao().getFundParaConfigDao().selectByPrimaryKey(1);// 1需要改变 fundparaid
        String fundcode = fundParaConfig.getFundcode();

//            当前净值类型非申购类型下   个人购买额度超过总额度
        if (fundParaConfig.getNetvaluetype().equals(1)) {
            BigDecimal nav = fundParaConfig.getNav();
            BigDecimal confirmedamount = transactionExpectation.getConfirmedamount();
            BigDecimal share = confirmedamount.divide(nav, 20, BigDecimal.ROUND_HALF_UP);
            BigDecimal totalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
//                个人购买额度超过总额度 ==1为左边>右边超过 ==0表示左边=右边  ==-1左边<右边
            if (totalvolofdistributorinta.compareTo(share) == 1) {
                logger.error("个人购买额度超过总额度");
                throw new ConfirmExpectationException();
            } else {
//                    @TODO 存储 确认的份额
                transactionExpectation.setConfirmedvol(share);
            }
        } else {
            logger.error("当前净值类型非申购类型");
            throw new ConfirmExpectationException();
        }

    }

    // 根据业务逻辑更新对应库表
    @Override
    void updateRepository(ApplicationModel apply, List<ConfirmationModel> confirm, ApplyException applyException) {
        FactoryDao().getTransactionApplicationDao().updateByPrimaryKey((TransactionApplication) apply);
        List<TransactionConfirmation> transactionConfirmationList = Collections.singletonList((TransactionConfirmation) confirm);
        for (TransactionConfirmation transactionConfirmation : transactionConfirmationList) {
            FactoryDao().getTransactionConfirmationDao().updateByPrimaryKey(transactionConfirmation);
        }
    }

    // 生成确认记录，不同业务需具体实现
    @Override
    void generateConfirm(ApplicationModel apply, ConfirmationModel confirm, ApplyException applyException) {
        FactoryDao().getTransactionApplicationDao().insert((TransactionApplication) apply);
        FactoryDao().getTransactionConfirmationDao().insert((TransactionConfirmation) confirm);
    }


//      用于增加天数
    public static String addDay(String time , int addDay) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        Date date = ft.parse(time);
        String format = ft.format(new Date(date.getTime() + addDay * 24 * 60 * 60 * 1000));
        return format;
    }
}
