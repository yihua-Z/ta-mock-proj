package com.psbc.business.service.annotation;

import com.psbc.business.service.FieldValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = FieldValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Validator {
    String message() default "{内容不合法}";  // 错误信息
    String rule() default "RE";             // enum, RE(regularExpression), time, date
    String content() default ".*?";         // enum或正则表达式的具体内容
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}