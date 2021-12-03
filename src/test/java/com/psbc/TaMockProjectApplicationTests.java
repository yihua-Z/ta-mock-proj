package com.psbc;

import com.nlf.calendar.util.HolidayUtil;
import com.psbc.business.processor.Processor_001;
import com.psbc.business.service.RepositoryFactory;
import com.psbc.business.service.SpringContextUtil;
import com.psbc.exceptions.ApplyException;
import com.psbc.exceptions.ProcessingException;
import com.psbc.mapper.AccountExpectationDao;
import com.psbc.mapper.AccountInfoDao;
import com.psbc.mapper.HolidayDao;
import com.psbc.pojo.AccountExpectation;;
import com.psbc.pojo.Holiday;
import com.psbc.pojo.TransactionApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;

import static com.psbc.service.ObjectProcessor.copyFields;
import static com.psbc.utils.DateAndTimeUtil.addDay;

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

/*    @Autowired
    HolidayDao holidayDao;*/
    @Test
    void test(){
        RepositoryFactory repositoryFactory = SpringContextUtil.getBean(RepositoryFactory.class);
        Holiday holiday = new Holiday();
        holiday.setDay("12");
        HolidayDao holidayDao = repositoryFactory.getHolidayDao();
        System.out.println(holidayDao == null);
        List<Holiday> holidays =holidayDao.selectByCondition(holiday);
        System.out.println(holidays);


    }

    @Test
    public void testHoliday() throws ParseException {
        com.nlf.calendar.Holiday holiday = HolidayUtil.getHoliday(("20210501"));
        System.out.println(holiday.toString());
        String s = addDay("20210501", 1);
    }

    @Test
    public void testApplyException(){
        ApplyException applyException= new ApplyException();
        TransactionApplication transactionApplication = new TransactionApplication();
        transactionApplication.setAppsheetserialno("123");

        copyFields(transactionApplication,applyException);

        System.out.println(applyException.getAppsheetserialno());
    }


}
