package com.zupacademy.italo.propostas.utilidades;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64Validator implements ConstraintValidator<Base64, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return org.apache.tomcat.util.codec.binary.Base64.isBase64(value);
    }
}