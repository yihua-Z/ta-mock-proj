package com.psbc.business.service;


import com.psbc.pojo.TableModel;
import com.psbc.reader.xmlModel.XMLNode;
import com.psbc.utils.helper.XMLParser;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

@Data
@Component
public class BusinessCodeChecker {

    private String businessFileMappingPath = ".\\src\\main\\resources\\xml\\business_file_mapping\\business_file_mapping.xml";

    public String businessCodeChecker(TableModel tableModel) {

        String businessCode = "";

        XMLNode xmlNode = XMLParser.parseXml(this.businessFileMappingPath);
        List<XMLNode> childrenNodes = xmlNode.getChildrenNodes();
        String simpleName = tableModel.getClass().getSimpleName();

        Field field = null;
        try {
            field = tableModel.getClass().getDeclaredField("businesscode");
            field.setAccessible(true);
            Object o = field.get(tableModel);
            businessCode = o.toString();

            if (businessCode != null) {
                if (businessCode.length() != 0) {
                    for (XMLNode x : childrenNodes
                    ) {
                        Map<String, String> attributes = x.getAttributes();
                        Set<String> keySet = attributes.keySet();
                        for (String key : keySet
                        ) {
                            if(key.equals("file")){
                                if (attributes.get(key).equals(simpleName)) {
                                    List<String> business = Arrays.asList(attributes.get("business").split(","));
                                    if (business.contains(businessCode)) {
                                        return businessCode;
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


        return null;
    }


}
