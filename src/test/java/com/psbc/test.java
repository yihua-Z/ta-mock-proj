package com.psbc;

import com.nlf.calendar.util.HolidayUtil;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.psbc.utils.DateAndTimeUtil.addDay;


/**
 * @author Dealyz
 * @version 1.0
 * @date 2021/12/2 10:15
 */
public class test {
    public String test(String time,int addDay) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
//        String time = "20190930";
        Date date = ft.parse(time);
        String format = ft.format(new Date(date.getTime() + addDay * 24 * 60 * 60 * 1000));
        System.out.println(format);
        return format;
    }
    @Test
    public void add() throws ParseException {
        String test = test("20211230",2);
    }

    @Test
    public void dar(){

        String transactioncfmdate = "202112310929990";
        String transactionDate = transactioncfmdate.substring(0,8);
        String transactionYear = transactionDate.substring(0,4);
        String transactionMonth = transactionDate.substring(4,6);
        String transactionDay = transactionDate.substring(6,8);
        String transactionDateNew=transactionDate;
        while (HolidayUtil.getHoliday(Integer.valueOf(transactionYear),Integer.valueOf(transactionMonth),Integer.valueOf(transactionDay))!=null){
            try {
                transactionDateNew = addDay(transactionDateNew, 1);
                transactionYear = transactionDateNew.substring(0,4);
                transactionMonth = transactionDateNew.substring(4,6);
                transactionDay = transactionDateNew.substring(6,8);
            }catch (ParseException e){
//                logger.error(e);
            }
        }
//        判断日期是否为节假日
        if (transactionDateNew!=transactionDate){
            transactioncfmdate=transactionDateNew+transactioncfmdate.substring(9);
            System.out.println(transactioncfmdate);
        }
        System.out.println(transactioncfmdate);

    }






}
