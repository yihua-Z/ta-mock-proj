package com.psbc.business.service;

import com.psbc.exceptions.ApplyException;
import com.psbc.exceptions.ProcessingException;
import com.psbc.mapper.AccountApplicationDao;
import com.psbc.mapper.TransactionApplicationDao;
import com.psbc.pojo.AccountApplication;
import com.psbc.pojo.ApplicationModel;
import com.psbc.pojo.TableModel;
import com.psbc.pojo.TransactionApplication;
import com.psbc.reader.DataFileReader;
import com.psbc.reader.xmlModel.XMLNode;
import com.psbc.utils.helper.XMLParser;
import org.apache.log4j.Logger;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.psbc.business.service.ObjectProcessor.copyFields;
import static com.psbc.business.service.RecordOperator.invokeGetMethod;

public class CommonProcessUtils {
    public static List<TableModel> readRecords(String applicationFilePath) {

        DataFileReader applyReader = new DataFileReader(new File(applicationFilePath));
        List<TableModel> applyRecords = applyReader.read();
        return applyRecords;
    }


    public static RepositoryFactory FactoryDao() {
        RepositoryFactory repositoryFactory = SpringContextUtil.getBean(RepositoryFactory.class);
        return repositoryFactory;
    }


    public static String ReturnCodeDescription(String code) {
        XMLNode return_codes = XMLParser.parseXml(".\\src\\main\\resources\\xml\\return_code\\return_codes.xml");
        String description = "未知错误";
        List<XMLNode> childrenNodes = return_codes.getChildrenNodes();
        for (XMLNode x : childrenNodes
        ) {
            Map<String, String> attributes = x.getAttributes();
            Set<String> keySet = attributes.keySet();

            for (String key : keySet
            ) {
                if (key.equals("code")) {
                    if (attributes.get(key).equals(code)) {
                        description = attributes.get("description");
                        return description;
                    }
                }

            }
        }

        return description;
    }

    public static ProcessingException getProcessingException(ProcessingException exception, String code ,Logger logger) {

        String description = ReturnCodeDescription(code);
        exception.setReturncode(code);
        exception.setErrortype("1");
        exception.setSpeification(description);
        logger.error(description);
        return exception;
    }

    public static List<TransactionApplication> getTransactionApplicationList() {
        TransactionApplicationDao applicationDao = FactoryDao().getTransactionApplicationDao();
        List<TransactionApplication> transactionApplications = applicationDao.selectAll();
        return transactionApplications;
    }
    public static List<AccountApplication> getAccountApplicationList() {
        AccountApplicationDao applicationDao = FactoryDao().getAccountApplicationDao();
        List<AccountApplication> accountApplications = applicationDao.selectAll();
        return accountApplications;
    }

    public static void validateApplyFromXML(ApplicationModel apply,Logger logger) throws ApplyException {

        ApplyException applyException = new ApplyException();
        if(apply.getClass().getSimpleName().equals("AccountApplication")){
            AccountApplication application = (AccountApplication) apply;
            copyFields(application, applyException);
            String businesscode = application.getBusinesscode();
            XMLNode business_configs = XMLParser.parseXml(".\\src\\main\\resources\\xml\\business_configs\\BUSINESSCODE.xml".replace("BUSINESSCODE", businesscode));
            List<XMLNode> childrenNodes = business_configs.getChildrenNodes();
            for (XMLNode c : childrenNodes
            ) {
                Map<String, String> attributes = c.getAttributes();
                Set<String> keySet = attributes.keySet();
                for (String key : keySet
                ) {
                    if (key.equals("fieldName")) {
                        try {
                            String fieldName = attributes.get(key).toLowerCase();
                            String required = attributes.get("required");
                            Field field = application.getClass().getDeclaredField(fieldName);
                            field.setAccessible(true);
                            Object o = invokeGetMethod(application, field.getName(), null);
                            if ((required.equals("true") && o == null) || (required.equals("true") && o.toString().equals(""))) {
                                logger.error(fieldName + "字段缺失");
                                applyException.setReturncode("9999");
                                applyException.setErrortype("1");
                                applyException.setSpeification(fieldName + "字段缺失");
                                logger.error(fieldName + "字段缺失");
                                throw applyException;
                            }
                        } catch (NoSuchFieldException e) {
                            logger.error(e);
                        }
                    }
                }
            }
        }

        if(apply.getClass().getSimpleName().equals("TransactionApplication")){
            TransactionApplication application = (TransactionApplication) apply;
            copyFields(application, applyException);
            String businesscode = application.getBusinesscode();
            XMLNode business_configs = XMLParser.parseXml(".\\src\\main\\resources\\xml\\business_configs\\BUSINESSCODE.xml".replace("BUSINESSCODE", businesscode));
            List<XMLNode> childrenNodes = business_configs.getChildrenNodes();

            for (XMLNode c : childrenNodes
            ) {
                Map<String, String> attributes = c.getAttributes();
                Set<String> keySet = attributes.keySet();
                for (String key : keySet
                ) {
                    if (key.equals("fieldName")) {
                        try {
                            String fieldName = attributes.get(key).toLowerCase();
                            String required = attributes.get("required");
                            Field field = application.getClass().getDeclaredField(fieldName);
                            field.setAccessible(true);
                            Object o = invokeGetMethod(application, field.getName(), null);
                            if ((required.equals("true") && o == null) || (required.equals("true") && o.toString().equals(""))) {
                                logger.error(fieldName + "字段缺失");
                                applyException.setReturncode("9999");
                                applyException.setErrortype("1");
                                applyException.setSpeification(fieldName + "字段缺失");
                                logger.error(fieldName + "字段缺失");
                                throw applyException;
                            }
                        } catch (NoSuchFieldException e) {
                            logger.error(e);
                        }
                    }
                }
            }
        }


    }

}
