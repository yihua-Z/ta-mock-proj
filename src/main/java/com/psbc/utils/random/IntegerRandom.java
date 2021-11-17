package com.psbc.utils.random;

public class IntegerRandom {
    public static int next(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }
    public static int next(int end){
        return (int) (Math.random() * (end + 1));
    }
}