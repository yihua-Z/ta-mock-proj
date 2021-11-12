package com.psbc.business.service;


import com.psbc.pojo.TableModel;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Component
public class BusinessCodeChecker {


    private String businessCode="";
    private String businessRootPath = ".\\src\\main\\resources\\xml\\business_configs\\";
    ;

    public String businessCodeChecker(TableModel tableModel) {


        Field field = null;
        try {
            field = tableModel.getClass().getDeclaredField("BusinessCode");
            field.setAccessible(true);
            Object o = field.get(tableModel);
            this.businessCode = o.toString();

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if (this.businessCode != null) {
            if (this.businessCode.length() != 0) {

                //businessCode 是不是在001-199之间
                File file = new File(this.businessRootPath);
                List<String> stringList = new ArrayList(Arrays.asList(file.list()));
                if (stringList.contains(this.businessCode + ".xml")) {
                    return businessCode;
                }
            }
        }
        return null;
    }



}
