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

    @Override
    void validateApply(ApplicationModel apply) throws ApplyException {

        RepositoryFactory repositoryFactory = SpringContextUtil.getBean(RepositoryFactory.class);
        repositoryFactory.getTransactionApplicationDao().selectAll();
    }

    @Override
    void validateConfirmExpectation(ExpectationModel expect) throws ConfirmExpectationException {

    }

    @Override
    void updateRepository(ApplicationModel apply, List<ConfirmationModel> confirm, ApplyException applyException) {

    }

    @Override
    void generateConfirm(ApplicationModel apply, ConfirmationModel confirm, ApplyException applyException) {

    }
}
