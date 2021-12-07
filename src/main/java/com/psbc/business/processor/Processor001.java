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
        validateApplyFromXML(apply,logger);
    }

    @Override
    void validateConfirmExpectation(ExpectationModel expect) throws ConfirmExpectationException {
        AccountExpectation accountExpectation = (AccountExpectation) expect;


    }

    @Override
    void updateRepository(ApplicationModel apply, List<ConfirmationModel> confirm, ApplyException applyException) {
        try {
            AccountConfirmationDao accountConfirmationDao = FactoryDao().getAccountConfirmationDao();
            for (ConfirmationModel c : confirm
            ) {

                AccountConfirmation accountConfirmation = (AccountConfirmation) c;
                try {
                    //生成交易确认记录
                    accountConfirmationDao.insert(accountConfirmation);
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
