package com.psbc.utils;

import com.nlf.calendar.util.HolidayUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        try {
            if (HolidayUtil.getHoliday(transactiondate) != null) {
                while (true) {
                    //  日期加1后再次判断是否是节假日
                    transactiondate = addDay(transactiondate, 1);
                    //  当前日期不是节假日与周末时
                    if (HolidayUtil.getHoliday(transactiondate) == null && !isWeekend(transactiondate)) {
                    //  获得交易日日期
                        return transactiondate;
                    }
                }
            } else {
                return transactiondate;

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return transactiondate;

    }

    public static Boolean isWeekend(String bDate) throws ParseException {
        DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
        Date bdate = format1.parse(bDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        } else {
            return false;
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
