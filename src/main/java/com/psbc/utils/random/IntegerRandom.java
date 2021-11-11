package com.psbc.utils.random;

class IntegerRandom {
    static int next(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }
    static int next(int end){
        return (int) (Math.random() * (end + 1));
    }
}