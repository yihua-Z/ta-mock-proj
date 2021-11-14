package com.psbc.business.processor;

import com.psbc.business.service.BusinessCodeChecker;
import com.psbc.business.service.RecordOperator;
import com.psbc.business.service.SpringContextUtil;
import com.psbc.mapper.AccountApplicationMapper;
import com.psbc.pojo.TableModel;
import com.psbc.pojo.AccountApplication;
import com.psbc.reader.DataFileReader;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Data
@Component
public class Processor_001 {
    @Autowired
    AccountApplicationMapper applicationMapper;

    private String applicationFilePath = "";

    private BusinessCodeChecker businessCodeChecker = new BusinessCodeChecker();

    private RecordOperator recordOperator = new RecordOperator();

    public List<AccountApplication> processor() {

        List<AccountApplication> accountApplicationList = new ArrayList<>();
        DataFileReader applyReader = new DataFileReader(new File(this.applicationFilePath));
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
                    accountApplication = (AccountApplication) this.recordOperator.getTargetObject(tableModel, accountApplication.newInstanceWithoutArgs());
                    this.applicationMapper.insert(accountApplication);
                    accountApplicationList.add(accountApplication);

                }
            }

        }
        return accountApplicationList;
    }


//    public static Processor_001 processor_001;
//    @PostConstruct
//    public void init() {
//        processor_001 = this;
//        processor_001.applicationMapper = this.applicationMapper;
//    }

    public static void main(String[] args) {

        String applicationFilePath = ".\\src\\main\\resources\\data\\OFD_037_999_20211025_01.TXT";

//        processor_001.setApplicationFilePath(applicationFilePath);
//        processor_001.processor();


    }


}
