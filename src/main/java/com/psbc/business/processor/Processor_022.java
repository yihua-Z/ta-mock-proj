package com.psbc.business.processor;

import com.psbc.TaMockProjectApplication;
import com.psbc.business.service.*;
import com.psbc.mapper.AccountApplicationDao;
import com.psbc.mapper.ExceptionDao;
import com.psbc.mapper.TransactionApplicationDao;
import com.psbc.pojo.Exception;
import com.psbc.pojo.*;
import com.psbc.reader.DataFileReader;
import com.psbc.writer.DataFileWriterDataBase;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public class Processor_022 {

    @Autowired
    AccountApplicationDao accountApplicationDao;
    @Autowired
    TransactionApplicationDao transactionApplicationDao;

    @Autowired
    ExceptionDao exceptionDao;

    private TransactionApplication transactionApplication =new TransactionApplication();
    private TransactionConfirmation transactionConfirmation =new TransactionConfirmation();
    private TransactionExpectation transactionExpectation =new TransactionExpectation();

    private RecordOperator recordOperator = new RecordOperator();
    private ExceptionRecordOperator errorOperate;
    private SucceedRecordOperator generateSucceed;
    private TransactionExpectation expectation;
    private CheckDataLegality checkDataLegality;
    private DataFileWriterDataBase dataFileWriterDataBase;
    private ExpectationChecker expectationChecker;

    private String returnCode = "0000";

    private String transactionApplicationPath = "";
    private static final Logger logger = Logger.getLogger(TaMockProjectApplication.class);

    @Transactional
    public DatabaseModel insertTransactionApplicationImpl(TableModel tableModel) {

//        @TODO referenceNo的处理

//      检查字段是否合法，合法返回对应的transactionApplication
        TransactionApplication transactionApplication=new TransactionApplication();
        transactionApplication = (TransactionApplication) this.recordOperator.getTargetObject(tableModel, transactionApplication.newInstanceWithoutArgs());

//      插入记录到 account_application
//        accountApplication.setTACode("0");
//        accountApplication.setReferenceNo(37);
//        accountApplication.setRecordStatus("0");

        try {
            this.transactionApplicationDao.insert(transactionApplication);
        } catch (java.lang.Exception e) {
            logger.error(e);
        }
        return transactionApplication;
    }

    @Transactional
    public void insertExceptionImpl(TableModel tableModel, RecordOperator recordOperator) {
        Exception exception = new Exception();
        exception = (Exception) recordOperator.getTargetObject(tableModel, exception.newInstanceWithoutArgs());
        exception.setErrortype("0");
        exceptionDao.insert(exception);
        System.out.println("申请失败：" + tableModel.toString());
    }


    public List<TableModel> readRecords(String transactionApplicationPath) {

        DataFileReader applyReader = new DataFileReader(new File(transactionApplicationPath));
        List<TableModel> applyRecords = applyReader.read();
        return applyRecords;
    }

    public DatabaseModel expectationFlow(DatabaseModel TransactionApplication) {

        DatabaseModel confirm;
//       需要改进
        if (this.returnCode.equals("0000")) {
            confirm = this.generateSucceed.generateTransactionSucceed(TransactionApplication);
        } else {
            confirm = this.errorOperate.errorOperate(TransactionApplication, this.returnCode);
        }
        return confirm;
    }


    public List<DatabaseModel> generateConfirmation(List<DatabaseModel> databaseModelList) {

        List<DatabaseModel> confirmList = new ArrayList<>();
        for (DatabaseModel application : databaseModelList
        ) {
            boolean checkAppSheetSeriaNo = this.expectationChecker.ExpectationTransactionOperate(application);
            // 记录存在 Expectation 中
            if (checkAppSheetSeriaNo) {
//              使用expectation的returnCode
                this.returnCode = this.generateSucceed.generateTransactionRecord(application, expectation);
                confirmList.add(this.expectationFlow(application));
            }
//          记录不存在 Expectation 中
            else {
//              校验记录的数据业务合法性
                this.checkDataLegality.CheckTransaction(application);
//              数据合法 生成记录
                if (this.checkDataLegality.isLegality()) {
//                  使用合法性检查后checkDataLegality的returnCode
                    String returnCode = this.checkDataLegality.getReturnCode();
                    this.generateSucceed.generateTransactionRecord(application, this.checkDataLegality.getReturnCode());
                    confirmList.add(this.expectationFlow(application));
                } else {
//                  数据不合法 异常登记
                    this.returnCode = this.checkDataLegality.getReturnCode();
                    confirmList.add(this.errorOperate.errorOperate(application, this.returnCode));
                }

            }
        }
        return confirmList;
    }

    public List<DatabaseModel> TransactionApplicationProcessor() {
        List<DatabaseModel> databaseModelList = new ArrayList<>();
        BusinessCodeChecker businessCodeChecker = new BusinessCodeChecker();

        List<TableModel> applyRecords = readRecords(this.transactionApplicationPath);

        boolean checkerFiledValue = false;
        for (TableModel tableModel : applyRecords
        ) {
            String businessCode = businessCodeChecker.businessCodeChecker(tableModel);
            if (businessCode != null) {
                this.recordOperator.setBusinessCode(businessCode);
//                检查业务值是否合法
                checkerFiledValue = this.recordOperator.checkerFiledValue(tableModel);

                if (tableModel.getClass().getSimpleName().equals("File03")) {
                    if (checkerFiledValue) {
                        databaseModelList.add(this.insertTransactionApplicationImpl(tableModel));
                    } else {
                        this.insertExceptionImpl(tableModel, recordOperator);
                    }
                }

            }
        }
        return databaseModelList;
    }

    public void processor(String transactionApplicationPath, boolean notInsertFlag, boolean isWriteFlag) {

        this.setTransactionApplicationPath(transactionApplicationPath);

        List<DatabaseModel> databaseModelList = null;

        if (notInsertFlag) {
            databaseModelList = this.TransactionApplicationProcessor();
        } else {
            databaseModelList = transactionApplicationDao.selectAll();
        }

        List<DatabaseModel> generateConfirmation = this.generateConfirmation(databaseModelList);

        logger.info(generateConfirmation.toString());
        if (isWriteFlag) {

            dataFileWriterDataBase.write();
        }

    }


}
