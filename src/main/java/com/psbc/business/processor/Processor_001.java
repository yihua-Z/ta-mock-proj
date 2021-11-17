package com.psbc.business.processor;

import com.psbc.business.service.BusinessCodeChecker;
import com.psbc.business.service.RecordOperator;
import com.psbc.mapper.AccountApplicationDao;
import com.psbc.mapper.ExceptionDao;
import com.psbc.pojo.AccountApplication;
import com.psbc.pojo.TableModel;
import com.psbc.reader.DataFileReader;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.psbc.pojo.Exception;

@Data
@Component
public class Processor_001 {

    @Autowired
    AccountApplicationDao applicationMapper;

    @Autowired
    ExceptionDao exceptionDao;

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

                } else {
                    Exception exception = new Exception();
                    exception = (Exception) this.recordOperator.getTargetObject(tableModel, exception.newInstanceWithoutArgs());
                    exception.setErrortype("0");
                    exceptionDao.insert(exception);
                    System.out.println("申请失败：" + tableModel.toString());

                }
            }

        }
        return this.accountApplicationList;
    }


}
