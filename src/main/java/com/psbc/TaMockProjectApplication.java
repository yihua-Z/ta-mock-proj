package com.psbc;

import com.psbc.business.processor.Processor_001;
import com.psbc.business.service.AccountExpectationChecker;
import com.psbc.business.service.SpringContextUtil;
import com.psbc.pojo.AccountApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TaMockProjectApplication {

    public static List<AccountApplication> getApplication(Processor_001 processor_001) {
        String applicationFilePath = ".\\src\\main\\resources\\data\\OFD_037_999_20211025_01.TXT";
        processor_001.setApplicationFilePath(applicationFilePath);
        List<AccountApplication> accountApplications = processor_001.accountApplicationProcessor();

        return accountApplications;

    }


    public static void main(String[] args) {

        SpringApplication.run(TaMockProjectApplication.class, args);

        Processor_001 processor_001 = SpringContextUtil.getBean(Processor_001.class);
        AccountExpectationChecker accountExpectationChecker = SpringContextUtil.getBean(AccountExpectationChecker.class);

//        List<AccountApplication> accountApplications = getApplication(processor_001);

        accountExpectationChecker.ExpectationOperate();
        System.out.println();


    }

}
