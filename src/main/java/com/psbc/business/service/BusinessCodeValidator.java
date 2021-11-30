package com.psbc.business.service;

import com.psbc.pojo.TableModel;
import com.psbc.reader.xmlModel.XMLNode;
import com.psbc.utils.StringProcessor;
import com.psbc.utils.helper.XMLParser;
import lombok.Data;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Data
@Component
public class BusinessCodeValidator {

    final private static String BUSINESS_CODE = "BusinessCode";

    /**
     * 根据尚未基础合法性检查的原始申请记录，获取文件对应的合法业务代码
     * @param record 尚未基础合法性检查的原始申请记录
     * @return businessCode 记录对应的合法业务代码
     */
    public static String validate(@NonNull final TableModel record){
        String businessCode = null;
        try {
            final Field field = record.getClass().getDeclaredField(BUSINESS_CODE);
            field.setAccessible(true);
            businessCode = (String) field.get(record);
            final String fileNo = StringProcessor.getFileNoFromFileClazzName(record.getClass().getCanonicalName());
            if(!isValidBusinessCode(fileNo, businessCode)){
                return null;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return businessCode;
    }

    /**
     * 判断业务代码是否属于该文件类型
     * @return
     */
    private static boolean isValidBusinessCode(@NonNull final String originFileNo, @NonNull final String businessCode){
        final String businessFileMappingPath = ".\\src\\main\\resources\\xml\\business_file_mapping\\business_file_mapping.xml";
        XMLNode xmlNode = XMLParser.parseXml(businessFileMappingPath);
        for (XMLNode childrenNode : xmlNode.getChildrenNodes()) {
            String fileType = childrenNode.getAttributeValue("file");
            String businessCodes = childrenNode.getAttributeValue("business");
            if(originFileNo.equals(fileType) && businessCodes.contains(businessCode)){
                return true;
            }
        }
        return false;
    }
}