package com.psbc;

import com.psbc.business.processor.Processor_001;
import com.psbc.business.service.*;
import com.psbc.writer.DataFileWriterDataBase;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class TaMockProjectApplication {


    private static final Logger logger = Logger.getLogger(TaMockProjectApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(TaMockProjectApplication.class, args);

        Processor_001 processor_001 = SpringContextUtil.getBean(Processor_001.class);
        ExpectationChecker expectationChecker = SpringContextUtil.getBean(ExpectationChecker.class);
        ExceptionRecordOperator errorOperate = SpringContextUtil.getBean(ExceptionRecordOperator.class);
        SucceedRecordOperator generateSucceed = SpringContextUtil.getBean(SucceedRecordOperator.class);
        CheckDataLegality checkDataLegality = SpringContextUtil.getBean(CheckDataLegality.class);

        DataFileWriterDataBase dataFileWriterDataBase = SpringContextUtil.getBean(DataFileWriterDataBase.class);

        processor_001.setExpectationChecker(expectationChecker);
        processor_001.setErrorOperate(errorOperate);
        processor_001.setGenerateSucceed(generateSucceed);
        processor_001.setCheckDataLegality(checkDataLegality);


        String applicationFilePath = ".\\src\\main\\resources\\data\\OFD_037_999_20211101_01.TXT";

        dataFileWriterDataBase.initialData(".\\src\\main\\resources\\xml\\file_structure_configs\\file_structure_configs.xml", "D:\\TONG\\JAVA\\ta-mock-proj\\src\\main\\resources\\data\\");

        processor_001.processor(applicationFilePath, false, false);
        logger.info("process 001 done!");


    }

}
