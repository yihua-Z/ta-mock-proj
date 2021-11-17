package com.psbc.business.service;

import com.psbc.mapper.AccountConfirmationDao;
import com.psbc.mapper.AccountInfoDao;
import com.psbc.mapper.AcctShareDao;
import com.psbc.mapper.TaPropertyConfigDao;
import com.psbc.pojo.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.Exception;
import java.math.BigDecimal;

import static com.psbc.utils.DateAndTimeUtil.getNowDateTime;

@Data
@Component
public class SucceedRecordOperator {

    private RecordOperator operator = new RecordOperator();
    private AccountInfo accountInfo = new AccountInfo();
    private AcctShare acctShare = new AcctShare();
    private String returnCode;
    private AccountConfirmation accountConfirmation = new AccountConfirmation();

    @Autowired
    TaPropertyConfigDao taPropertyConfigDao;

    @Autowired
    AccountInfoDao accountInfoDao;

    @Autowired
    AcctShareDao acctShareDao;

    @Autowired
    AccountConfirmationDao accountConfirmationDao;

    public String generateRecord(AccountApplication application, AccountExpectation expectation) {

        AccountConfirmation accountConfirmation = new AccountConfirmation();
        accountConfirmation = (AccountConfirmation) this.operator.getTargetObject(application, accountConfirmation.newInstanceWithoutArgs());
        if (expectation != null) {
            accountConfirmation = (AccountConfirmation) this.operator.getTargetObject(expectation, accountConfirmation);
        }

        this.getAccountConfirmation(accountConfirmation);

        this.returnCode = expectation.getReturncode();

        return this.returnCode;
    }

    public void getAccountConfirmation(AccountConfirmation accountConfirmation) {

        TaPropertyConfig taPropertyConfig = taPropertyConfigDao.selectByPrimaryKey("0");
        String accountprefix = taPropertyConfig.getAccountprefix();
        Integer accountindex = taPropertyConfig.getAccountindex();

//        TransactionCfmDate: T+1;  无字段（已手动添加） 无明确解释
//        MultiAcctFlag: 0；  无字段
        accountConfirmation.setTaaccountid(accountprefix + accountindex + getNowDateTime());
        accountConfirmation.setTransactioncfmdate("");
//        int 不合理 未按照要求生成
        accountConfirmation.setTaserialno((int) (Math.random() * (10000)));
        accountConfirmation.setFromtaflag("0");
        accountConfirmation.setReturncode("0001");

        this.accountConfirmation = accountConfirmation;

    }

    public String generateRecord(AccountApplication application, String returnCode) {


        AccountConfirmation accountConfirmation = new AccountConfirmation();
        accountConfirmation = (AccountConfirmation) this.operator.getTargetObject(application, accountConfirmation.newInstanceWithoutArgs());
        this.getAccountConfirmation(accountConfirmation);

        this.returnCode = returnCode;

        return this.returnCode;

    }


    public void generateSucceed(AccountApplication accountApplication) {


//        写入 "account_info "表
        AccountInfo accountInfo = new AccountInfo();
        accountInfo = (AccountInfo) this.operator.getTargetObject(accountApplication, accountInfo.newInstanceWithoutArgs());
        accountInfo.setTaacountid(getNowDateTime());
//        accountInfoDao.insert(accountInfo);

//        初始化 "acct_share" 表

        AcctShare acctShare = new AcctShare();
        acctShare = (AcctShare) this.operator.getTargetObject(accountApplication, acctShare.newInstanceWithoutArgs());
        acctShare = (AcctShare) this.operator.getTargetObject(accountInfo, acctShare.newInstanceWithoutArgs());
        acctShare.setTotalvolofdistributorinta(BigDecimal.valueOf(0));

//        acctShareDao.insert(acctShare);

//        写入确认表
        accountConfirmationDao.insert(this.accountConfirmation);

        System.out.println();
    }

}