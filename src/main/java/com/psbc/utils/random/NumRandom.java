package com.psbc.utils.random;

import java.util.Map;

/**
 * 随机返回一个整数数字
 */
public class NumRandom implements Random {
    private static final int upBounder = 10000;
    @Override
    public String next(Map<String,String> attrs) {
        if(attrs.containsKey("randomRange")){
            System.out.println("使用！！！！！！！1");
            String[] values = attrs.get("randomRange").split("-");
            int left = Integer.parseInt(values[0]);
            int right = Integer.parseInt(values[1]);
            return String.valueOf(IntegerRandom.next(left,right));
        }
        return String.valueOf(IntegerRandom.next(upBounder));
    }
}
