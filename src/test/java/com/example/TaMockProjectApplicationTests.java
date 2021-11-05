package com.example;

import com.example.mapper.AccountInfoMapper;
import com.example.pojo.AccountInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

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


}
