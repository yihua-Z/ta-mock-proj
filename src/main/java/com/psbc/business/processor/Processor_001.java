package com.psbc.business.processor;

import com.psbc.business.service.BusinessCodeChecker;
import com.psbc.business.service.RecordOperator;
import com.psbc.mapper.AccountApplicationDao;
import com.psbc.pojo.AccountApplication;
import com.psbc.pojo.TableModel;
import com.psbc.reader.DataFileReader;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Data
@Component
public class Processor_001 {

    @Autowired
    AccountApplicationDao applicationMapper;

    private String applicationFilePath = "";

    private BusinessCodeChecker businessCodeChecker = new BusinessCodeChecker();

    private RecordOperator recordOperator = new RecordOperator();

    private List<AccountApplication> accountApplicationList = new ArrayList<>();

    public List<AccountApplication> accountApplicationProcessor() {

        DataFileReader applyReader = new DataFileReader(new File(this.applicationFilePath));
        List<TableModel> applyRecords = applyReader.read();
        boolean checkerFiledValue = false;
        for (TableModel tableModel : applyRecords
        ) {
            String businessCode = this.businessCodeChecker.businessCodeChecker(tableModel);
            if (businessCode != null) {
                this.recordOperator.setBusinessCode(businessCode);
//                检查业务值是否合法
                checkerFiledValue = this.recordOperator.checkerFiledValue(tableModel);
                if (checkerFiledValue) {
//                    检查字段是否合法，合法返回对应的AccountApplication
                    AccountApplication accountApplication = new AccountApplication();
                    accountApplication = (AccountApplication) this.recordOperator.getTargetObject(tableModel, accountApplication.newInstanceWithoutArgs());
//                    插入记录到 account_application
                    this.applicationMapper.insert(accountApplication);
                    this.accountApplicationList.add(accountApplication);

                }
            }

        }
        return this.accountApplicationList;
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
