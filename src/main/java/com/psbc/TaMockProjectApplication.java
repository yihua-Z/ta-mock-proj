package com.psbc;

import com.psbc.business.processor.Processor_001;
import com.psbc.business.service.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaMockProjectApplication {





    public static void main(String[] args) {

        SpringApplication.run(TaMockProjectApplication.class, args);

        Processor_001 processor_001 = SpringContextUtil.getBean(Processor_001.class);
        String applicationFilePath = ".\\src\\main\\resources\\data\\OFD_037_999_20211025_01.TXT";
        processor_001.setApplicationFilePath(applicationFilePath);
        processor_001.processor();
        System.out.println(processor_001.toString());

    }

}
