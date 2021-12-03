package com.psbc.business.service;

import com.psbc.business.service.annotation.Validator;
import com.psbc.pojo.AccountApplication;
import lombok.Data;
import lombok.Getter;
import org.apache.log4j.Logger;

import javax.validation.*;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件记录中的字段内容格式校验
 * 例如：email需满足email特定格式
 */
@Getter
public final class FieldValidator implements ConstraintValidator<Validator, String> {

    private static final Logger log = Logger.getLogger(FieldValidator.class);

    private static final String DATA_FORMAT = "yyyyMMdd";
    private static final String TIME_FORMAT = "HHmmss";

    // email: \w+@(\w+.)+[a-z]{2,3}
    // amount: \d+\.\d+
    // 纯数字： [0-9]+
    // [0-9A-Za-z]+
    // 正实数：[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)

    private String message;  // 错误信息
    private String rule;     // enum, RE(regularExpression), time, date,
    private String content;  // enum或正则表达式的具体内容

    @Override
    public void initialize(Validator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.message = constraintAnnotation.message();
        this.rule = constraintAnnotation.rule();
        this.content = constraintAnnotation.content();
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        if("enum".equals(rule)){
            return isEnumValid(value);
        }
        else if("RE".equals(rule)){
            return isRegularExpressValid(value);
        }
        else if("time".equals(rule)){
            return isTimeValid(value);
        }
        else if("date".equals(rule)){
            return isDateValid(value);
        }
        return false;
    }


    /**
     * 根据正则表达式校验
     */
    private boolean isRegularExpressValid(final String value){
        if(value == null || value.length() < 1 || "".equals(value.trim())){
            return true;
        }
        final Pattern p = Pattern.compile(this.content);
        final Matcher m = p.matcher(value);
        final boolean isMatched = m.matches();
        if(!isMatched){
            log.error(message + "格式有误");
        }
        return isMatched;
    }

    /**
     * 校验日期的格式
     */
    public boolean isDateValid(final String date) {
        if(date == null){
            return true;
        }
        final SimpleDateFormat dateFormat = new SimpleDateFormat(DATA_FORMAT);
        try{
            Date parsedDate = dateFormat.parse(date);
            if(dateFormat.format(parsedDate).equals(date)){
                return true;
            }
        } catch (ParseException e){
            log.error(message + "格式有误");
            return false;
        }
        log.error(message + "格式有误");
        return false;
    }


    /**
     * 检验时间的格式
     */
    public boolean isTimeValid(final String time){
        if(time == null){
            return true;
        }
        final SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
        try{
            Date parsedDate = dateFormat.parse(time);
            if(dateFormat.format(parsedDate).equals(time)){
                return true;
            }
        } catch (ParseException e){
            log.error(message + "格式有误");
            return false;
        }
        log.error(message + "格式有误");
        return false;
    }

    /**
     * 检验枚举的格式
     */
    public boolean isEnumValid(final String value){
        if(value == null){
            return true;
        }
        final String[] pool = value.split(",");
        for (String ele : pool) {
            if(ele.equals(value)){
                return true;
            }
        }
        log.error(message + "格式有误");
        return false;
    }

    public static void main(String[] args) {
        AccountApplication test = new AccountApplication();
        test.setAppsheetserialno("1qa");
        Outer outer = new Outer();
        outer.method(test);

    }
}


@Data
class Outer {
    public void method(AccountApplication test){

        try {
            boolean isValid = true;
            Field[] fields = AccountApplication.class.getDeclaredFields();
            System.out.println(fields.length);
            for (Field field : fields) {
                Validator validator = field.getAnnotation(Validator.class);
                FieldValidator fieldValidator = new FieldValidator();
                fieldValidator.initialize(validator);
                field.setAccessible(true);
                if(!fieldValidator.isValid((String) field.get(test), null)) {
                    isValid = false;
                    break;
                }
            }
            System.out.println(isValid);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}