package com.psbc;


import com.psbc.business.processor.Processor022;

import com.psbc.business.processor.Processor001;
import com.psbc.business.processor.Processor024;
import com.psbc.business.service.SpringContextUtil;
import com.psbc.exceptions.ConfirmExpectationException;
import com.psbc.pojo.AccountApplication;
import com.psbc.pojo.TransactionApplication;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

import static com.psbc.business.service.CommonProcessUtils.getAccountApplicationList;
import static com.psbc.business.service.CommonProcessUtils.getTransactionApplicationList;

@EnableTransactionManagement
@SpringBootApplication
public class TaMockApplication {


    private static final Logger logger = Logger.getLogger(TaMockApplication.class);

    public static void Process001() {
        Processor001 processor001 = SpringContextUtil.getBean(Processor001.class);
        List<AccountApplication> applyList = getAccountApplicationList();
        for (AccountApplication application : applyList
        ) {
            try {
                if (application.getBusinesscode().equals("001") && !application.getRecordstatus().equals("2")) {
                    processor001.process(application);
                }
            } catch (ConfirmExpectationException e) {
                logger.error(e);
            }
        }
    }


    public static void Process024() {
        Processor024 processor024 = SpringContextUtil.getBean(Processor024.class);
        List<TransactionApplication> applyList = processor024.getApplyList();
        for (TransactionApplication application : applyList
        ) {
            try {
//                && !application.getRecordstatus().equals("2")
                if (application.getBusinesscode().equals("024")) {
                    processor024.process(application);
                }
            } catch (ConfirmExpectationException e) {
                logger.error(e);
            }
        }
    }


    public static void Process022() {
        Processor022 processor022 = SpringContextUtil.getBean(Processor022.class);

        List<TransactionApplication> applyList = processor022.getApplyList();
        for (TransactionApplication application : applyList
        ) {
            try {

                if (application.getBusinesscode().equals("022") && !application.getRecordstatus().equals("2")) {
                    processor022.process(application);
                }
            } catch (ConfirmExpectationException e) {
                logger.error(e);
            }
        }
    }

    public static void main(String[] args) {

        SpringApplication.run(TaMockApplication.class, args);

        Process001();
        Process024();
        Process022();
    }

}
