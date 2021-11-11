package com.psbc.utils.random;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Map;

/**
 * 随机产生金额
 */
public class MoneyRandom implements Random {

    @Override
    public String next(Map<String,String> attrs) {
        int length = Integer.parseInt(attrs.get("length"));
        int precision = Integer.parseInt(attrs.get("precision"));
        return RandomStringUtils.random(length-precision, false, true) +
                "." +
                RandomStringUtils.random(precision, false, true);
    }
}
