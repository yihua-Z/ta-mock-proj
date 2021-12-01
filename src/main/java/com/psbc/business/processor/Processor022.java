package com.psbc.business.processor;

import com.psbc.TaMockProjectApplication;
import com.psbc.business.service.*;
import com.psbc.exceptions.ApplyException;
import com.psbc.exceptions.ConfirmExpectationException;
import com.psbc.mapper.*;
import com.psbc.pojo.Exception;
import com.psbc.pojo.*;
import com.psbc.writer.DataFileWriterDataBase;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.psbc.business.service.CommonProcessUtils.readRecords;
import static com.psbc.service.ObjectProcessor.copyFields;
import static com.psbc.utils.DateAndTimeUtil.getFullNowDateTime;

@Data
@Component
public class Processor022 extends BiDirectionProcessor{
    private static final Logger logger = Logger.getLogger(Processor022.class);

    public RepositoryFactory FactoryDao(){
        RepositoryFactory repositoryFactory = SpringContextUtil.getBean(RepositoryFactory.class);
        return repositoryFactory;
    }



    // 判断申请记录的业务合法性，不同业务需具体实现;
    // 若合法，不做任何返回；若不合法，抛对应异常

    @Override
    void validateApply(ApplicationModel apply) throws ApplyException {

        TransactionApplication transactionApplication=(TransactionApplication) apply;
        Double transactiondate = Double.valueOf(transactionApplication.getTransactiondate());

        Double transactiontime = Double.valueOf(transactionApplication.getTransactiontime());
        //判断是否下午三点切日，如果切日日期加一天
        String CHANGEDTE="150000";
        if (Double.valueOf(CHANGEDTE)<transactiontime){
            transactiontime+=1;

        if (transactiondate + transactiontime > Double.valueOf(getFullNowDateTime())) {
            logger.error("申请日期不在申购日期期限中");
            throw new ApplyException();
        }

        if (transactionApplication.getAppsheetserialno()==null||
                transactionApplication.getDistributorcode()==null||transactionApplication.getFundcode()==null){
            logger.error("必要字段存在为空");
            throw new ApplyException();
        }


        //个人购买额度是否可购买最高额度
            FundParaConfig fundParaConfig = FactoryDao().getFundParaConfigDao().selectByPrimaryKey(Integer.valueOf(transactionApplication.getFundcode()));
            if (!(fundParaConfig.getBuyupperamount()==null)||!(fundParaConfig.getBuyupperamount()).equals(0)){
                BigDecimal applicationamount = transactionApplication.getApplicationamount();
                BigDecimal buyupperamount = fundParaConfig.getBuyupperamount();
                if (applicationamount.compareTo(buyupperamount)==1){
                    logger.error("个人购买额度超过最高额度");
                    throw  new ApplyException();
                }
            }
//            当前净值类型非申购类型 下   个人购买额度超过总额度
            AcctShareKey acctShareKey = new AcctShareKey();
            copyFields(transactionApplication, acctShareKey);
            AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);
            if (fundParaConfig.getNetvaluetype().equals(1)){
                BigDecimal nav = fundParaConfig.getNav();
                BigDecimal applicationamount = transactionApplication.getApplicationamount();
                BigDecimal share = applicationamount.divide(nav, 20, BigDecimal.ROUND_HALF_UP);
                BigDecimal totalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
//                个人购买额度超过总额度
                if(totalvolofdistributorinta.compareTo(share)==1){
                    logger.error("个人购买额度超过总额度");
                    throw new ApplyException();
                }else {
//                    @TODO 存储 确认的份额
//                    share
                }
            }else {
                logger.error("当前净值类型非申购类型");
                throw  new ApplyException();
            }

    }
    }
    // 判断所获期望是否合理，不同业务需具体实现
    @Override
    void validateConfirmExpectation(ExpectationModel expect) throws ConfirmExpectationException {
        TransactionExpectation transactionExpectation =(TransactionExpectation) expect;


        //        -- Table structure for acct_share（账户份额表）
//        -- Table structure for fund_date（产品日历表）
//        -- Table structure for fund_info（产品基本信息表）
//        -- Table structure for fund_para（产品参数表）
      /*  FundDateKey fundDateKey = new FundDateKey();
        copyFields(transactionExpectation,fundDateKey);

        FundDate fundDate = FactoryDao().getFundDateDao().selectByPrimaryKey(fundDateKey);
        fundDate.getTransactioncfmdate();

        FundParaConfig fundParaConfig = FactoryDao().getFundParaConfigDao().selectByPrimaryKey(Integer.valueOf(transactionExpectation.getFundcode()));
        String fundcode = fundParaConfig.getFundcode();*/
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
}
