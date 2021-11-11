package com.psbc.utils.random;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

/**
 * 随机产生一串序列字符
 */
public class SerialRandom implements Random {

    @Override
    public String next(Map<String,String> attrs) {
        return RandomStringUtils.random(Integer.parseInt(attrs.get("length")), true, true);
    }
}
