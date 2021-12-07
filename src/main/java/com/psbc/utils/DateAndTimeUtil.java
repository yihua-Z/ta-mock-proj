package com.psbc.utils;

//import com.nlf.calendar.Holiday;
import com.nlf.calendar.util.HolidayUtil;
import com.psbc.business.service.RepositoryFactory;
import com.psbc.business.service.SpringContextUtil;
import com.psbc.pojo.Holiday;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

public class DateAndTimeUtil {
    public static String getFullNowDateTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return simpleDateFormat.format(date);
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

//        将2020-01-01修改为春节，并追加2099-01-01为元旦节
//        当天年月日YYYYMMDD(8位)+节假日名称下标(1位)+调休标识(1位)+节假日当天YYYYMMDD(8位)
//        20200101 1 1 20200101
//        20990101 0 1 20990101
//        HolidayUtil.fix("202001011120200101209901010120990101");
//        System.out.println(HolidayUtil.getHoliday("2020-01-01"));
//        System.out.println(HolidayUtil.getHoliday("2099-01-01"));
//        将元旦节改为元旦
//        String[] names = HolidayUtil.NAMES;
//        names[0] = "元旦";
//        HolidayUtil.fix(names, null);
//        System.out.println(HolidayUtil.getHoliday("2099-01-01"));

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


    public static String getNextTransactionDayFromDB(String transactiondate) {

        RepositoryFactory repositoryFactory = SpringContextUtil.getBean(RepositoryFactory.class);
        Holiday holiday = new Holiday();
        holiday.setDay(transactiondate);
        repositoryFactory.getHolidayDao();
        List<Holiday> holidays = repositoryFactory.getHolidayDao().selectByCondition(holiday);
        try {
            if (holidays.size() > 0) {
                while (true) {
                    //  日期加1后再次判断是否是节假日
                    transactiondate = addDay(transactiondate, 1);
                    //  当前日期不是节假日与周末时
                    holiday.setDay(transactiondate);
                    if (repositoryFactory.getHolidayDao().selectByCondition(holiday).size() == 0) {
                        if (!isWeekend(transactiondate)) {
                            //  获得交易日日期
                            return transactiondate;
                        }

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


    public static Boolean isWeekend(String bDate) {
        DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
        Date bdate = null;
        try {
            bdate = format1.parse(bDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        List<com.nlf.calendar.Holiday> holidays = HolidayUtil.getHolidays(getNowYear());
        for (com.nlf.calendar.Holiday  h:holidays
             ) {
            System.out.println(h.toString());
        }
        String nextTransactionDay = getNextTransactionDay("20211001");
        System.out.println(nextTransactionDay);

//        String nextTransactionDayFromDB = getNextTransactionDayFromDB("20210101");
//        System.out.println(nextTransactionDayFromDB);
        System.out.println(getNowDate());
        System.out.println(isWeekend("20211204"));

    }
    //    用于判断是否为周末和节假日
    public static String getNextTransactionDate(String transactionDate) {
        String  transactionCfdate=transactionDate;
        while (isWeekend(transactionCfdate)){
            try {
                transactionCfdate = addDay(transactionCfdate, 1);
            } catch (ParseException e) {
//                getProcessingException(applyException,"0000");
//                logger.error(e);
            }
        }
        //        获取交易日期配置表，获取延迟确认天数
        //        获取工作日日历表
        if (HolidayUtil.getHoliday(transactionCfdate) != null) {
            transactionCfdate=getNextTransactionDayFromDB(transactionDate);
        }

        return transactionCfdate;
    }


}
