package com.psbc.business.processor;

import com.psbc.TaMockProjectApplication;
import com.psbc.business.service.*;
import com.psbc.mapper.AccountApplicationDao;
import com.psbc.mapper.ExceptionDao;
import com.psbc.pojo.*;
import com.psbc.pojo.Exception;
import com.psbc.reader.DataFileReader;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Data
@Component
public class Processor_001 {

    @Autowired
    AccountApplicationDao accountApplicationDao;

    @Autowired
    ExceptionDao exceptionDao;

    private RecordOperator recordOperator = new RecordOperator();
    private ExceptionRecordOperator errorOperate;
    private SucceedRecordOperator generateSucceed;
    private AccountExpectation expectation;
    private CheckDataLegality checkDataLegality;

    private ExpectationChecker expectationChecker;
    private boolean checkAppSheetSeriaNo = false;
    private String returnCode = "0000";

    private String applicationFilePath = "";
    private static final Logger logger = Logger.getLogger(TaMockProjectApplication.class);

    @Transactional
    public DatabaseModel insertAccountApplicationImpl(TableModel tableModel) {

//        @todo
//        referenceNo的处理
        

//      检查字段是否合法，合法返回对应的AccountApplication
        AccountApplication accountApplication = new AccountApplication();
        accountApplication = (AccountApplication) this.recordOperator.getTargetObject(tableModel, accountApplication.newInstanceWithoutArgs());
//      插入记录到 account_application
        accountApplication.setTACode("0");
        accountApplication.setReferenceNo(37);
        accountApplication.setRecordStatus("0");
        try {
            this.accountApplicationDao.insert(accountApplication);
        } catch (java.lang.Exception e) {
            logger.error(e);
        }
        return accountApplication;
    }

    @Transactional
    public void insertExceptionImpl(TableModel tableModel, RecordOperator recordOperator) {
        Exception exception = new Exception();
        exception = (Exception) recordOperator.getTargetObject(tableModel, exception.newInstanceWithoutArgs());
        exception.setErrortype("0");
        exceptionDao.insert(exception);
        System.out.println("申请失败：" + tableModel.toString());
    }


    public List<TableModel> readRecords(String applicationFilePath) {

        DataFileReader applyReader = new DataFileReader(new File(applicationFilePath));
        List<TableModel> applyRecords = applyReader.read();
        return applyRecords;
    }

    public void ExpectationFlow(DatabaseModel accountApplication) {

        if (this.returnCode.equals("0000")) {
            this.generateSucceed.generateSucceed(accountApplication);
        } else {
            this.errorOperate.errorOperate(accountApplication, this.returnCode);
        }

    }


    public void generateConfirmation(List<DatabaseModel> databaseModelList) {

        for (DatabaseModel application : databaseModelList
        ) {

//          @todo
//          联合主键查询
            boolean checkAppSheetSeriaNo = this.expectationChecker.ExpectationOperate(application);
            // 记录存在 Expectation 中
            if (checkAppSheetSeriaNo) {
//                    使用expectation的returnCode
                this.returnCode = this.generateSucceed.generateRecord(application, expectation);
                this.ExpectationFlow(application);
            }
//                记录不存在 Expectation 中
            else {
//                    校验记录的数据业务合法性
                this.checkDataLegality.Check(application);
//                  数据合法 生成记录
                if (this.checkDataLegality.isLegality()) {
//                        使用合法性检查后checkDataLegality的returnCode
                    this.generateSucceed.generateRecord(application, this.checkDataLegality.getReturnCode());
                    this.ExpectationFlow(application);
                } else {
//                  数据不合法 异常登记
                    this.returnCode = this.checkDataLegality.getReturnCode();
                    this.errorOperate.errorOperate(application, this.returnCode);
                }

            }
        }


    }

    public List<DatabaseModel> ApplicationProcessor() {
        List<DatabaseModel> databaseModelList = new ArrayList<>();
        BusinessCodeChecker businessCodeChecker = new BusinessCodeChecker();

        List<TableModel> applyRecords = readRecords(this.applicationFilePath);

        boolean checkerFiledValue = false;
        for (TableModel tableModel : applyRecords
        ) {
            String businessCode = businessCodeChecker.businessCodeChecker(tableModel);
            if (businessCode != null) {
                this.recordOperator.setBusinessCode(businessCode);
//                检查业务值是否合法
                checkerFiledValue = this.recordOperator.checkerFiledValue(tableModel);

                if (tableModel.getClass().getSimpleName().equals("File01")) {
                    if (checkerFiledValue) {
                        databaseModelList.add(this.insertAccountApplicationImpl(tableModel));
                    } else {
                        this.insertExceptionImpl(tableModel, recordOperator);
                    }
                }

            }
        }
        return databaseModelList;
    }

    public void processor(String applicationFilePath,boolean notInsertFlag) {

        this.setApplicationFilePath(applicationFilePath);

        List<DatabaseModel> databaseModelList = null;

        if (notInsertFlag) {
            databaseModelList = this.ApplicationProcessor();
        } else {
            databaseModelList = accountApplicationDao.selectAll();
        }

        this.generateConfirmation(databaseModelList);


    }


}
