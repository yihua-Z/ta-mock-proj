package com.psbc.business.service;

import com.psbc.business.service.annotation.Validator;
import com.psbc.exceptions.ApplyException;
import com.psbc.pojo.AccountApplication;
import com.psbc.pojo.ApplicationModel;
import com.psbc.pojo.TransactionApplication;
import com.psbc.reader.xmlModel.XMLNode;
import com.psbc.utils.helper.XMLParser;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;

@Service
public class ApplyFormatValidator {

    private static final Logger log = Logger.getLogger(ApplyFormatValidator.class);
    public static final String BUSINESS_CONFIG = ".\\src\\main\\resources\\xml\\business_configs\\";
    public static final String MISS_RETURN_CODE = "3105";
    /**
     * 检验每个字段的内容格式合法性
     */
    public void validateFieldFormat(final ApplicationModel record) throws ApplyException {
        try {
            Field[] fields;
            if(record.getClass().isAssignableFrom(AccountApplication.class)) {
                fields = AccountApplication.class.getDeclaredFields();
            } else if(record.getClass().isAssignableFrom(TransactionApplication.class)) {
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
                    if(!fieldValidator.isValid((String) field.get(record), null)) {
                        String error = "字段“" + field.getName() + "”的格式不合法";
                        log.error(error);
                        ApplyException applyException = new ApplyException();
                        ObjectProcessor.copyFields(record,applyException);
                        applyException.setErrortype("1");
                        applyException.setReturncode(MISS_RETURN_CODE);
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

    /**
     * 检验每个字段是否满足”必填性“
     */
    public void validateFieldRequirement(final ApplicationModel record) throws ApplyException {
        String businessCode = "";
        AccountApplication accountApplication = null;
        TransactionApplication transactionApplication = null;
        if(record.getClass().isAssignableFrom(AccountApplication.class)) {
            businessCode = ((AccountApplication) record).getBusinesscode();
            accountApplication = (AccountApplication) record;
        } else if(record.getClass().isAssignableFrom(TransactionApplication.class)) {
            businessCode = ((TransactionApplication) record).getBusinesscode();
            transactionApplication = (TransactionApplication) record;
        } else {
            log.error("传入参数不合法");
            return;
        }

        final XMLNode xmlNode = XMLParser.parseXml(BUSINESS_CONFIG + businessCode + "xml");

        try {
            for (XMLNode childrenNode : xmlNode.getChildrenNodes()) {
                Map<String, String> attributes = childrenNode.getAttributes();
                String fieldName = attributes.get("fieldName");
                String required = attributes.get("required");
                if ("true".equals(required)) {

                    Field field = record.getClass().getDeclaredField(fieldName.toLowerCase(Locale.ROOT));
                    field.setAccessible(true);
                    Object o = field.get(record);

                    if(o == null || ((String) o).length() < 1 || "".equals(((String) o).trim())){
                        ApplyException exception = new ApplyException();
                        String serialNo = "";
                        if(accountApplication != null){
                            ObjectProcessor.copyFields(accountApplication,exception);
                            serialNo = accountApplication.getAppsheetserialno();
                        } else {
                            ObjectProcessor.copyFields(transactionApplication,exception);
                            serialNo = transactionApplication.getNetno();
                        }
                        exception.setErrortype("1");
                        exception.setReturncode(MISS_RETURN_CODE);
                        String errorContent = "申请记录" + serialNo + "中必填字段" + fieldName + "内容缺失";
                        exception.setSpeification(errorContent);
                        log.error("申请记录中必填字段缺失：" + errorContent);
                    }
                }
            }
        } catch (NoSuchFieldException e) {
            log.error("申请记录中必输字段丢失：" + e.getMessage());
            ApplyException exception = new ApplyException();
            exception.setErrortype("1");
            exception.setReturncode(MISS_RETURN_CODE);
            exception.setSpeification("字段缺失");
            e.printStackTrace();
            throw exception;
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}