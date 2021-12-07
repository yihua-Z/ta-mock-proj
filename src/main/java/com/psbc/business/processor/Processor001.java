package com.psbc.business.processor;

import com.psbc.exceptions.ApplyException;
import com.psbc.exceptions.ConfirmExpectationException;
import com.psbc.mapper.*;
import com.psbc.pojo.*;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.Exception;
import java.math.BigDecimal;
import java.util.List;


import static com.psbc.business.service.CommonProcessUtils.FactoryDao;
import static com.psbc.business.service.CommonProcessUtils.validateApplyFromXML;
import static com.psbc.business.service.ObjectProcessor.copyFields;
import static com.psbc.utils.DateAndTimeUtil.getNextTransactionDayFromDB;
import static com.psbc.utils.DateAndTimeUtil.getNowDate;

/**
 * @author Huilin Tong
 * @date 2021年11月30日 14:23
 */
@Data
@Component
public class Processor001 extends BiDirectionProcessor {
    private static final Logger logger = Logger.getLogger(Processor001.class);

    @Override
    void validateApply(ApplicationModel apply) throws ApplyException {
        AccountApplication accountApplication = (AccountApplication) apply;
        accountApplication.setTacode("0");
        accountApplication.setReferenceno(37);
        accountApplication.setRecordstatus("0");
        accountApplication.setInstrepridtype("0");
        accountApplication.setTransactorcerttype("0");
        accountApplication.setMultiacctflag("0");
        accountApplication.setCertificatetype("0");
        accountApplication.setIndividualorinstitution("0");

        validateApplyFromXML(apply, logger);
    }

    @Override
    void validateConfirmExpectation(ExpectationModel expect) throws ConfirmExpectationException {
        AccountExpectation accountExpectation = (AccountExpectation) expect;


    }

    @Override
    void updateRepository(ApplicationModel apply, List<ConfirmationModel> confirm, ApplyException applyException) {
        try {
            AccountApplication accountApplication = (AccountApplication) apply;
            AccountConfirmationDao accountConfirmationDao = FactoryDao().getAccountConfirmationDao();
            for (ConfirmationModel c : confirm
            ) {

                AccountConfirmation accountConfirmation = (AccountConfirmation) c;
                if (accountConfirmation.getReturncode().equals("0000")) {

                    TaProperty taProperty = FactoryDao().getTaPropertyDao().selectByPrimaryKey(accountApplication.getTacode());
                    String accountprefix = taProperty.getAccountPrefix();
                    String accountindex = String.valueOf(taProperty.getAccountIndex());

//                  生成TAAccountID
                    accountConfirmation.setTaaccountid(accountprefix + accountindex + (int) (Math.random() * (10000)));

                    AcctShare acctShare = new AcctShare();
                    AccountInfo accountInfo = new AccountInfo();

                    copyFields(accountApplication,acctShare);
                    copyFields(accountApplication,accountInfo);

                    copyFields(accountConfirmation, acctShare);
                    copyFields(accountConfirmation, accountInfo);
                    //@TODO 动态生成fundcode
                    acctShare.setFundcode("AM213035");
                    acctShare.setTotalvolofdistributorinta(BigDecimal.valueOf(0));
                    acctShare.setTotalamountofdistributorinta(BigDecimal.valueOf(0));
                    acctShare.setUpdatedate(getNowDate());

                    accountInfo.setTaacountid(accountConfirmation.getTaaccountid());
                    try {
                        FactoryDao().getAcctShareDao().insert(acctShare);
                        FactoryDao().getAccountInfoDao().insert(accountInfo);

                    } catch (Exception e) {
                        logger.error(e);
                    }

                }


                try {
                    //生成交易确认记录
                    accountConfirmationDao.insert(accountConfirmation);
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

        AccountApplication accountApplication = (AccountApplication) apply;


        AccountConfirmation accountConfirmation = (AccountConfirmation) confirm;
        if (accountConfirmation.getReturncode() == null) {
            String transactiondate = accountConfirmation.getTransactiondate();
            copyFields(accountApplication, accountConfirmation);
            //      获得交易日日期
            if (applyException != null && applyException.getReturncode() != null) {
                accountConfirmation.setReturncode(applyException.getReturncode());
            } else {
                accountConfirmation.setReturncode("0000");
            }
            accountConfirmation.setTransactioncfmdate(getNextTransactionDayFromDB(transactiondate));
            accountConfirmation.setTransactiondate(getNextTransactionDayFromDB(transactiondate));
            accountConfirmation.setBusinesscode("1" + accountApplication.getBusinesscode().substring(1));

        }
//      TODO 改进确认日期的生成
        accountConfirmation.setTransactiondate(getNextTransactionDayFromDB(getNowDate()));
        accountConfirmation.setTransactioncfmdate(getNextTransactionDayFromDB(getNowDate()));
        accountConfirmation.setBusinesscode("1" + accountApplication.getBusinesscode().substring(1));


    }
}
