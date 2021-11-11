package com.psbc.utils.random;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 随机产生日期(实际返回当前时间)
 */
public class DateRandom implements Random {

    @Override
    public String next(Map<String,String> attrs) {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }
}
