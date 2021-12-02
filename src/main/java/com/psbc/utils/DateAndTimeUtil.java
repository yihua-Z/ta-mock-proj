package com.psbc.utils;

import com.nlf.calendar.util.HolidayUtil;

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

    public static String getNowYear() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String s = simpleDateFormat.format(date);
        return s;
    }

    //      用于增加天数
    public static String addDay(String time, int addDay) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        Date date = ft.parse(time);
        String format = ft.format(new Date(date.getTime() + addDay * 24 * 60 * 60 * 1000));
        return format;
    }


    public static String getNextTransactionDay(String transactiondate) {
        if (HolidayUtil.getHoliday(transactiondate) != null) {
            while (true) {
                try {
//                    日期加1后再次判断是否是节假日
                    transactiondate = addDay(transactiondate, 1);
                    if (HolidayUtil.getHoliday(transactiondate) == null) {
//                        获得交易日日期
                        return transactiondate;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return transactiondate;

        }

    }

    public static void main(String[] args) {
//        List<Holiday> holidays = HolidayUtil.getHolidays(getNowYear());
//        Holiday holiday = HolidayUtil.getHoliday("2021");
//        System.out.println(holiday);

        String nextTransactionDay = getNextTransactionDay("20211001");
        System.out.println(nextTransactionDay);

//        System.out.println(getNowDate());
//        System.out.println(getNowTime());

    }


}
