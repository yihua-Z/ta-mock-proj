package com.psbc;

import com.psbc.mapper.AccountInfoMapper;
import com.psbc.mapper.AcctShareMapper;
import com.psbc.mapper.DividendMapper;
import com.psbc.pojo.AccountInfo;
import com.psbc.pojo.AcctShare;
import com.psbc.pojo.Dividend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class TaMockProjectApplicationTests {

    @Autowired
    DataSource dataSource;

//    @Test
//    void contextLoads() throws SQLException {
//
//        System.out.println(dataSource);
//        System.out.println(dataSource.getConnection());
//    }

    @Autowired
    AccountInfoMapper accountInfoMapper;

    @Test
    void testMybatis() {

        AccountInfo accountInfo = new AccountInfo();
        String data="5";
        accountInfo.setTAAccountID(data);
        accountInfo.setName(data);
        accountInfo.setSex(data);
        accountInfo.setNationality(data);
        accountInfo.setVocation(data);
        accountInfo.setAddress(data);
        accountInfo.setTelNo(data);
        accountInfo.setCertificateType(data);
        accountInfo.setCertificateNo(data);
        accountInfo.setCertValidDate(data);
        accountInfo.setTransactionAccountID(data);
        accountInfo.setDistributorCode(data);
        accountInfo.setBranchCode(data);
        accountInfo.setAccountStatus(data);
        accountInfo.setTACode(data);
        accountInfo.setCustomerNo("4");



        AccountInfo info = accountInfoMapper.selectByPrimaryKey("1");
        System.out.println(info.toString());
//        int insert = accountInfoMapper.insert(accountInfo);
//        accountInfoMapper.deleteByPrimaryKey("2");
//        accountInfoMapper.updateByPrimaryKey(accountInfo);
//        AccountInfo a = accountInfoMapper.selectByPrimaryKey("5");


    }

    @Autowired
    AcctShareMapper acctShareMapper;

    @Test
    void testAcctShare(){
        AcctShare acctShare = new AcctShare();
        acctShare.setTAAccountID("4");
        acctShare.setDistributorCode("1");
        acctShare.setTACode("1");
        acctShare.setTotalFrozenVol(1.0);
        acctShare.setTotalVolOfDistributorInTA(0.1);
        acctShare.setTotalFrozenVol(0.1);
        acctShare.setTransactionCfmDate("2021");

        AcctShare acctShare1 = acctShareMapper.selectByPrimaryKey("1");
        System.out.println(acctShare1.toString());
        int insert = acctShareMapper.insert(acctShare);


    }

    @Autowired
    DividendMapper dividendMapper;
    @Test
    void Wang(){
        Dividend dividend = dividendMapper.selectByPrimaryKey("1");
        System.out.println(dividend.toString());
    }


}
