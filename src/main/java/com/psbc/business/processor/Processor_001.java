package com.psbc.business.processor;

import com.psbc.pojo.TableModel;
import com.psbc.pojo.AccountApplication;
import com.psbc.reader.DataFileReader;
import lombok.Data;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;


@Data
@Component
public class Processor_001 {

    private String applicationFilePath = "";

    private BusinessCodeChecker businessCodeChecker;

    private RecordOperator recordOperator;

    public AccountApplication processor() {

        this.applicationFilePath = applicationFilePath;
        DataFileReader applyReader = new DataFileReader(new File(applicationFilePath));
        List<TableModel> applyRecords = applyReader.read();
        AccountApplication accountApplication = new AccountApplication();
        boolean checkerFiledValue = false;
        for (TableModel tableModel : applyRecords
        ) {
            String businessCode = this.businessCodeChecker.businessCodeChecker(tableModel);
            if (businessCode != null) {
                this.recordOperator.setBusinessCode(businessCode);
                checkerFiledValue = this.recordOperator.checkerFiledValue(tableModel);
                if (checkerFiledValue) {
                    accountApplication = this.recordOperator.recordToDatabase(tableModel, accountApplication);
                    return accountApplication;
                }
            }

        }
        return null;

    }

    public static void main(String[] args) {


        String applicationFilePath = ".\\src\\main\\resources\\data\\OFD_037_999_20211025_01.TXT";

        Processor_001 processor_001 = new Processor_001();

        RecordOperator recordOperator = new RecordOperator();
        BusinessCodeChecker businessCodeChecker = new BusinessCodeChecker();

        processor_001.setRecordOperator(recordOperator);
        processor_001.setBusinessCodeChecker(businessCodeChecker);
        processor_001.setApplicationFilePath(applicationFilePath);


        processor_001.processor();


    }


}
