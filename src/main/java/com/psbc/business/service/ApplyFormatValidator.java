package com.psbc.business.service;

import com.psbc.business.service.annotation.Validator;
import com.psbc.exceptions.ApplyException;
import com.psbc.pojo.AccountApplication;
import com.psbc.pojo.ApplicationModel;
import com.psbc.pojo.TransactionApplication;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class ApplyFormatValidator {

    private static final Logger log = Logger.getLogger(ApplyFormatValidator.class);

    /**
     * 检验每个字段的内容格式合法性
     */
    public void validateFieldFormat(ApplicationModel test) throws ApplyException {
        try {
            Field[] fields;
            if(test.getClass().isAssignableFrom(AccountApplication.class)) {
                fields = AccountApplication.class.getDeclaredFields();
            } else if(test.getClass().isAssignableFrom(TransactionApplication.class)) {
                fields = TransactionApplication.class.getDeclaredFields();
            } else {
                log.error("传入参数不合法");
                return;
            }
            for (Field field : fields) {
                Validator validator = field.getAnnotation(Validator.class);
                if(validator != null){
                    FieldValidator fieldValidator = new FieldValidator();
                    fieldValidator.initialize(validator);
                    field.setAccessible(true);
                    if(!fieldValidator.isValid((String) field.get(test), null)) {
                        String error = "字段“" + field.getName() + "”的格式不合法";
                        log.error(error);
                        ApplyException applyException = new ApplyException();
                        com.psbc.service.ObjectProcessor.copyFields(test,applyException);
                        applyException.setErrortype("1");
                        applyException.setSpeification(error);
                        throw applyException;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}