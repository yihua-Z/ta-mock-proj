package com.psbc.utils;

import com.psbc.exceptions.BusinessFileMappingException;
import com.psbc.exceptions.ConfirmationTypeNotFoundException;
import com.psbc.pojo.TableModel;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.psbc.utils.Reflector.getFileObjectFrom;
import static com.psbc.utils.Reflector.invokeGetMethod;
import static com.psbc.utils.StringProcessor.*;

/**
 * 确认文件生成器 (辅助性类)
 * <p>说明：</P>
 * <P>一个该类的对象可以接受同一种申请(如001)下的多个记录</P>
 * <p>考虑到数据的多样性，相应的每个确认数据都需要不同的随机内容</P>
 */
public class ConfirmationGenerator {

    private String applicationType;
    private List<TableModel> applications;
    private List<Map<String,String>> randomValues;  // <fieldName, value>

    public ConfirmationGenerator(String applicationType, List<TableModel> applications, List<Map<String,String>> randomValue){
        this.applicationType = applicationType;
        this.applications = applications;
        this.randomValues = randomValue;
    }

    /**
     * 根据申请内容生成确认内容
     */
    public List<TableModel> generate(){
        List<TableModel> res = new LinkedList<>();
        try {
            // 相同的申请类型对应相同的确认类型
            String confirmationType = getConfirmationBusinessCodeFrom(applicationType);
            String fileType = getFileTypeFrom(confirmationType);
            int recordNums = applications.size();
            // 每个记录数据
            for (int i = 0; i < recordNums; ++i) {
                TableModel confirmation = getFileObjectFrom(fileType);
                TableModel application = applications.get(i);

                Field[] confirmationFields = confirmation.getClass().getDeclaredFields();
                Field[] applicationFields = application.getClass().getDeclaredFields();

                // 每个申请文件和确认文件相同字段的赋值
                for (Field conField : confirmationFields) {
                    conField.setAccessible(true);
                    // "BusinessCode"的值填写确认业务码
                    if ("BusinessCode".equals(conField.getName())) {
                        conField.set(confirmation, confirmationType);
                        continue;
                    }
                    for (Field appField : applicationFields) {
                        if (appField.getName().equals(conField.getName())) {
                            String value = (String) invokeGetMethod(application, appField.getName());
                            conField.set(confirmation, value);
                            break;
                        }
                    }
                }
                // 确认文件中独有字段的赋值
                for (Field conField : confirmationFields) {
                    conField.setAccessible(true);
                    Object o = conField.get(confirmation);
                    if (o == null) {
                        String s = randomValues.get(i).get(conField.getName());
                        conField.set(confirmation, s);
                    }
                }
                res.add(confirmation);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | BusinessFileMappingException | ConfirmationTypeNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }
}