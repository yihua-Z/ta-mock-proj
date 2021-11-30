package com.psbc.business.processor;

import com.psbc.exceptions.ApplyException;
import com.psbc.exceptions.ConfirmExpectationException;
import com.psbc.pojo.ApplicationModel;
import com.psbc.pojo.ConfirmationModel;
import com.psbc.pojo.ExpectationModel;

import java.util.List;

/**
 * @author Huilin Tong
 * @date 2021年11月30日 14:23
 */
public class Processor001 extends BiDirectionProcessor {
    @Override
    void validateApply(ApplicationModel apply) throws ApplyException {

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
