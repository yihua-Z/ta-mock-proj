package com.psbc;

import com.psbc.business.processor.Processor_001;
import com.psbc.mapper.AccountApplicationMapper;
import com.psbc.mapper.AccountExpectationMapper;
import com.psbc.mapper.AccountInfoMapper;
import com.psbc.pojo.AccountExpectation;
import com.psbc.pojo.AccountInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TaMockProjectApplicationTests {




    @Test
    void testController() {

        String applicationFilePath = ".\\src\\main\\resources\\data\\OFD_037_999_20211025_01.TXT";

        Processor_001 processor_001 = new Processor_001();
        processor_001.setApplicationFilePath(applicationFilePath);


    }

    @Autowired
    AccountExpectationMapper accountExpectationMapper;
    @Test
    public void accountExpectationMapper() {


        List<AccountExpectation> accountExpectations = accountExpectationMapper.selectAll();
        System.out.println();
    }


    @Autowired
    AccountInfoMapper accountInfoMapper;

    @Test
    void testMybatis() {

        AccountInfo accountInfo = new AccountInfo();
        String data = "5";


//        AccountInfo info = accountInfoMapper.selectByPrimaryKey("1");
//        System.out.println(info.toString());
        int insert = accountInfoMapper.insert(accountInfo);
//        accountInfoMapper.deleteByPrimaryKey("2");
//        accountInfoMapper.updateByPrimaryKey(accountInfo);
//        AccountInfo a = accountInfoMapper.selectByPrimaryKey("5");


    }


}
