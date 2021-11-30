package com.psbc.business.processor;

import com.psbc.TaMockProjectApplication;
import com.psbc.business.service.*;
import com.psbc.exceptions.ApplyException;
import com.psbc.exceptions.ConfirmExpectationException;
import com.psbc.mapper.AccountApplicationDao;
import com.psbc.mapper.ExceptionDao;
import com.psbc.pojo.Exception;
import com.psbc.pojo.*;
import com.psbc.writer.DataFileWriterDataBase;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.psbc.business.service.CommonProcessUtils.readRecords;

@Data
@Component
public class Processor022 extends BiDirectionProcessor{

    @Autowired
    AccountApplicationDao accountApplicationDao;

    @Autowired
    ExceptionDao exceptionDao;

    private RecordOperator recordOperator = new RecordOperator();
    private ExceptionRecordOperator errorOperate;
    private SucceedRecordOperator generateSucceed;
    private AccountExpectation expectation;
    private CheckDataLegality checkDataLegality;
    private DataFileWriterDataBase dataFileWriterDataBase;
    private ExpectationChecker expectationChecker;

    private String returnCode = "0000";

    private String applicationFilePath = "";
    private static final Logger logger = Logger.getLogger(TaMockProjectApplication.class);

    @Transactional
    public DatabaseModel insertAccountApplicationImpl(TableModel tableModel) {

//        @TODO referenceNo的处理

//      检查字段是否合法，合法返回对应的AccountApplication
        AccountApplication accountApplication = new AccountApplication();
        accountApplication = (AccountApplication) this.recordOperator.getTargetObject(tableModel, accountApplication.newInstanceWithoutArgs());
//        accountApplication = (AccountApplication) this.recordOperator.getBeanUtilsCopyProperties(tableModel, accountApplication.newInstanceWithoutArgs());

        //      插入记录到 account_application
        accountApplication.setTacode("0");
        accountApplication.setReferenceno(37);
        accountApplication.setRecordstatus("0");
//        accountApplication.setInstrepridtype("0");
//        accountApplication.setTransactorcerttype("0");
//        accountApplication.setMultiacctflag("");
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

    public DatabaseModel expectationFlow(DatabaseModel accountApplication) {

        DatabaseModel confirm;
        if (this.returnCode.equals("0000")) {
            confirm = this.generateSucceed.generateSucceed(accountApplication);
        } else {
            confirm = this.errorOperate.errorOperate(accountApplication, this.returnCode);
        }
        return confirm;
    }


    public List<DatabaseModel> generateConfirmation(List<DatabaseModel> databaseModelList) {

        List<DatabaseModel> confirmList = new ArrayList<>();
        for (DatabaseModel application : databaseModelList
        ) {
            boolean checkAppSheetSeriaNo = this.expectationChecker.ExpectationOperate(application);
            // 记录存在 Expectation 中
            if (checkAppSheetSeriaNo) {
//              使用expectation的returnCode
                this.returnCode = this.generateSucceed.generateRecord(application, expectation);
                confirmList.add(this.expectationFlow(application));
            }
//          记录不存在 Expectation 中
            else {
//              校验记录的数据业务合法性
                this.checkDataLegality.Check(application);
//              数据合法 生成记录
                if (this.checkDataLegality.isLegality()) {
//                  使用合法性检查后checkDataLegality的returnCode
                    this.generateSucceed.generateRecord(application, this.checkDataLegality.getReturnCode());
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

    public void processor(String applicationFilePath, boolean notInsertFlag, boolean isWriteFlag) {

        this.setApplicationFilePath(applicationFilePath);

        List<DatabaseModel> databaseModelList = null;

        if (notInsertFlag) {
            databaseModelList = this.ApplicationProcessor();
        } else {
            databaseModelList = accountApplicationDao.selectAll();
        }

        List<DatabaseModel> generateConfirmation = this.generateConfirmation(databaseModelList);

        logger.info(generateConfirmation.toString());
        if (isWriteFlag) {
            dataFileWriterDataBase.initialData(".\\src\\main\\resources\\xml\\file_structure_configs\\file_structure_configs.xml", "D:\\TONG\\JAVA\\ta-mock-proj\\src\\main\\resources\\data\\");
            dataFileWriterDataBase.write();
        }

    }


    @Override
    void validateApply(ApplicationModel apply) throws ApplyException {

    }

    @Override
    void validateConfirmExpectation(TransactionExpectation expect) throws ConfirmExpectationException {

    }

    @Override
    void updateRepository(ApplicationModel apply, List<DatabaseModel> confirm, ApplyException applyException) {

    }

    @Override
    void generateConfirm(ApplicationModel apply, TransactionConfirmation confirm, ApplyException applyException) {

    }
}
