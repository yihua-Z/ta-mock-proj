package com.psbc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

public class DateAndTimeUtil {
    public static String getFullNowDateTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String s = simpleDateFormat.format(date);
        return s;
    }

    public static String getNowDateTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = simpleDateFormat.format(date);
        return s;
    }


    public static String getNowDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String s = simpleDateFormat.format(date);
        return s;
    }

    public static String getNowTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        String s = simpleDateFormat.format(date);
        return s;
    }

    public static void main(String[] args) {
//        System.out.println(getNowDate());
//        System.out.println(getNowTime());
        System.out.println(getFullNowDateTime());


    }




}
