package com.psbc.utils.random;

import java.util.Map;

/**
 * 从已提供的选项中随机选择一个
 */
public class ItemRandom implements Random {
    @Override
    public String next(Map<String,String> attrs) {
        String[] items = attrs.get("randomRange").split(",");
        return items[IntegerRandom.next(items.length - 1)];
    }
}