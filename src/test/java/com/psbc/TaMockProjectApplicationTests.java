package com.psbc;

import com.psbc.business.processor.Processor_001;
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


    @Autowired
    AccountExpectationMapper accountExpectationMapper;

    @Autowired
    AccountInfoMapper accountInfoMapper;

    @Test
    void testController() {

        String applicationFilePath = ".\\src\\main\\resources\\data\\OFD_037_999_20211025_01.TXT";

        Processor_001 processor_001 = new Processor_001();
        processor_001.setApplicationFilePath(applicationFilePath);


    }

    @Test
    public void accountExpectationMapper() {


        List<AccountExpectation> accountExpectations = accountExpectationMapper.selectAll();
        System.out.println();
    }

    @Test
    void testMybatis() {


    }


}
