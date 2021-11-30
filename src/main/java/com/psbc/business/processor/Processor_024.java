package com.psbc.business.processor;

import com.psbc.mapper.AccountApplicationDao;
import com.psbc.mapper.ExceptionDao;
import com.psbc.pojo.DatabaseModel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Processor_024 {


    @Autowired
    AccountApplicationDao accountApplicationDao;

    @Autowired
    ExceptionDao exceptionDao;



    private String applicationFilePath = "";

    public void processor(String applicationFilePath, boolean notInsertFlag, boolean isWriteFlag) {

        this.setApplicationFilePath(applicationFilePath);

        List<DatabaseModel> databaseModelList = null;

        if (notInsertFlag) {
            databaseModelList = this.ApplicationProcessor();
        } else {
            databaseModelList = accountApplicationDao.selectAll();
        }

//        List<DatabaseModel> generateConfirmation = this.generateConfirmation(databaseModelList);

//        logger.info(generateConfirmation.toString());
//        if (isWriteFlag) {
//            dataFileWriterDataBase.write();
//        }

    }

    private List<DatabaseModel> ApplicationProcessor() {

        return null;
    }

}
