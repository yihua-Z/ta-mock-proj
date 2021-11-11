package com.psbc.utils.random;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

/**
 * 随机返回一个小数
 */
public class RateRandom implements Random {

    @Override
    public String next(Map<String,String> attrs) {
        int length = Integer.parseInt(attrs.get("length"));
        int precision = Integer.parseInt(attrs.get("precision"));
        String decimal = RandomStringUtils.random(length - precision, false, true);
        return "0." + decimal;
    }
}
