package com.psbc;

import com.psbc.business.processor.Processor024;
import com.psbc.business.service.SpringContextUtil;
import com.psbc.exceptions.ConfirmExpectationException;
import com.psbc.pojo.TransactionApplication;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@EnableTransactionManagement
@SpringBootApplication
public class TaMockApplication {


    private static final Logger logger = Logger.getLogger(TaMockApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(TaMockApplication.class, args);

        Processor024 processor024 = SpringContextUtil.getBean(Processor024.class);
        List<TransactionApplication> applyList = processor024.getApplyList();
        for (TransactionApplication application : applyList
        ) {
            try {
                if (application.getBusinesscode().equals("024")&&!application.getRecordstatus().equals("2")) {
                    processor024.process(application);
                }
            } catch (ConfirmExpectationException e) {
                logger.error(e);
            }
        }

    }

}
