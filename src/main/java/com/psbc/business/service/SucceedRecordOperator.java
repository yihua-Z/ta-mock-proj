package com.psbc.business.service;

import com.psbc.mapper.*;
import com.psbc.pojo.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.psbc.utils.DateAndTimeUtil.*;

@Data
@Component
public class SucceedRecordOperator {

    private RecordOperator operator = new RecordOperator();
    private AccountInfo accountInfo = new AccountInfo();
    private AcctShare acctShare = new AcctShare();
    private String returnCode;
    private AccountConfirmation accountConfirmation = new AccountConfirmation();

    @Autowired
    TaPropertyDao taPropertyDao;

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
            this.accountConfirmation = (AccountConfirmation) this.operator.getTargetObject(expectation, accountConfirmation);
        }

        this.getAccountConfirmation("0");

        this.returnCode = expectation.getReturncode();

        return this.returnCode;
    }

    public void getAccountConfirmation(String taCode) {
//      taCode应该配置
        TaProperty taProperty = taPropertyDao.selectByPrimaryKey(taCode);
        String accountprefix = taProperty.getAccountPrefix();
        Integer accountindex = taProperty.getAccountIndex();

//        TransactionCfmDate: T+1;  无字段（已手动添加） 无明确解释
//        MultiAcctFlag: 0；  为了生成确认文件

//      TaAccountid 随机生成待改进
        this.accountConfirmation.setTaaccountid(accountprefix + accountindex + getNowDate());
        this.accountConfirmation.setTransactioncfmdate("");
        this.accountConfirmation.setTaserialno(getFullNowDateTime() + (int) (Math.random() * (1000)));
        this.accountConfirmation.setFromtaflag("0");
        this.accountConfirmation.setRegioncode("0001");
        this.accountConfirmation.setReturncode(this.returnCode);


    }

    public void generateRecord(AccountApplication application, String returnCode) {


        AccountConfirmation accountConfirmation = new AccountConfirmation();
        this.accountConfirmation = (AccountConfirmation) this.operator.getTargetObject(application, accountConfirmation.newInstanceWithoutArgs());
        this.returnCode = returnCode;
        this.getAccountConfirmation("0");


    }


    public void generateSucceed(AccountApplication accountApplication) {


//        写入 "account_info "表
        AccountInfo accountInfo = new AccountInfo();
        accountInfo = (AccountInfo) this.operator.getTargetObject(accountApplication, accountInfo.newInstanceWithoutArgs());


        accountInfo.setTaaccountid(this.accountConfirmation.getTaaccountid());
        accountInfo.setAccountinfoid(1);
        accountInfo.setTransactionaccountid("1");


        accountInfoDao.insert(accountInfo);

//        初始化 "acct_share" 表

        AcctShare acctShare = new AcctShare();
        acctShare = (AcctShare) this.operator.getTargetObject(accountApplication, acctShare.newInstanceWithoutArgs());
        acctShare = (AcctShare) this.operator.getTargetObject(accountInfo, acctShare);
        acctShare.setTotalvolofdistributorinta(BigDecimal.valueOf(0));

        acctShare.setDistributorcode("0");
        acctShare.setTransactioncfmdate("");

        acctShareDao.insert(acctShare);

//        写入确认表


        accountConfirmationDao.insert(this.accountConfirmation);

        System.out.println();
    }

}
