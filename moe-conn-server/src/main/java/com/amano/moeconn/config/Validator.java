package com.amano.moeconn.config;

import com.amano.moeconn.config.annotation.EnumValue;
import org.assertj.core.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 自定义参数校验规则注解的校验逻辑类
 */
public class Validator implements ConstraintValidator<EnumValue, Object> {

    private Class<? extends Enum<?>> enumClass;
    private String enumMethod;

    @Override
    public void initialize(EnumValue enumValue) {
        enumMethod = enumValue.enumMethod();
        enumClass = enumValue.enumClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return Boolean.TRUE;
        }
        if (enumClass == null || enumMethod == null) {
            return Boolean.TRUE;
        }
        Class<?> valueClass = value.getClass();
        try {
            Method method = enumClass.getMethod(enumMethod, valueClass);
            if (!Boolean.TYPE.equals(method.getReturnType()) && !Boolean.class.equals(method.getReturnType())) {
                throw new RuntimeException(Strings.formatIfArgs("%s method return is not boolean type in the %s class", enumMethod, enumClass));
            }

            if (!Modifier.isStatic(method.getModifiers())) {
                throw new RuntimeException(Strings.formatIfArgs("%s method is not static method in the %s class", enumMethod, enumClass));
            }

            Boolean result = (Boolean) method.invoke(null, value);
            return result == null ? false : result;
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(Strings.formatIfArgs("This %s(%s) method does not exist in the %s", enumMethod, valueClass, enumClass), e);
        }
    }
}