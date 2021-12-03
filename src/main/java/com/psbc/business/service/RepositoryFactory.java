package com.psbc.business.service;

import com.psbc.mapper.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Data
@Component
//@Getter(onMethod = @__(@Autowired))
@Setter(onMethod = @__(@Autowired))
public class RepositoryFactory {
    private AccountApplicationDao accountApplicationDao;
    private AccountConfirmationDao accountConfirmationDao;
    private AccountExpectationDao accountExpectationDao;
    private AccountInfoDao accountInfoDao;
    private AcctReconciliationDao acctReconciliationDao;
    private AcctShareDao acctShareDao;
    private CustomerInfoDao customerInfoDao;
    private DividendDao dividendDao;
    private ExceptionDao exceptionDao;
    private FundDateDao fundDateDao;
    private FundInfoDao fundInfoDao;
    private FundParaConfigDao fundParaConfigDao;
    private PrepaymentAcctDao prepaymentAcctDao;
    private TaBusinessConfigDao taBusinessConfigDao;
    private TaPropertyConfigDao taPropertyConfigDao;
    private TaPropertyDao taPropertyDao;
    private TimedApplicationDao timedApplicationDao;
    private TimedTaskDao timedTaskDao;
    private TransactionApplicationDao transactionApplicationDao;
    private TransactionConfirmationDao transactionConfirmationDao;
    private TransactionExpectationDao transactionExpectationDao;
    private HolidayDao holidayDao;
}