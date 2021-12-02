package com.psbc;

import com.psbc.business.processor.Processor_001;
import com.psbc.business.service.RepositoryFactory;
import com.psbc.business.service.SpringContextUtil;
import com.psbc.mapper.AccountExpectationDao;
import com.psbc.mapper.AccountInfoDao;
import com.psbc.pojo.AccountExpectation;;
import com.psbc.pojo.Holiday;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TaMockProjectApplicationTests {


    @Autowired
    AccountExpectationDao accountExpectationDao;

    @Autowired
    AccountInfoDao accountInfoDao;

    @Test
    void testController() {

        String applicationFilePath = ".\\src\\main\\resources\\data\\OFD_037_999_20211025_01.TXT";

        Processor_001 processor_001 = new Processor_001();
        processor_001.setApplicationFilePath(applicationFilePath);


    }

    @Test
    public void accountExpectationDao() {


        List<AccountExpectation> accountExpectations = accountExpectationDao.selectAll();
        System.out.println();
    }

    @Test
    void testMybatis() {


    }
    @Test
    void test(){
        RepositoryFactory repositoryFactory = SpringContextUtil.getBean(RepositoryFactory.class);
        Holiday holiday = new Holiday();
        holiday.setDay("12");
        List<Holiday> holidays = repositoryFactory.getHolidayDao().selectByCondition(holiday);
        System.out.println(holidays);


    }


}
