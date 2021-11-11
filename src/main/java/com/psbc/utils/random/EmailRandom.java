package com.psbc.utils.random;

import java.util.Map;

/**
 * 随机产生邮箱地址
 */
public class EmailRandom implements Random {

    private final String[] suffix = {"@gmail.com","@yahoo.com","@msn.com","@hotmail.com","@aol.com"};

    @Override
    public String next(Map<String,String> attrs) {
        return new SerialRandom().next(attrs) + suffix[IntegerRandom.next(suffix.length - 1)];
    }
}