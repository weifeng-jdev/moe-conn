package com.amano.moeconn.config.annotation;

import com.amano.moeconn.config.Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Validator.class)
public @interface EnumValue {
    String message() default "{custom.value.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<?>> enumClass();

    String enumMethod();
}
