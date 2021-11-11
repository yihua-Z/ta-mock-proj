package com.psbc.utils.random;

import java.util.Map;

public class KeepSame implements Random {
    String value;
    public void setValue(String value){
        this.value = value;
    }
    @Override
    public String next(Map<String, String> attrs) {
        return value;
    }
}
