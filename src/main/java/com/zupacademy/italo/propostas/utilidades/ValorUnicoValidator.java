package com.zupacademy.italo.propostas.utilidades;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;


public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {
    private Class<?> target;
    private String field;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        this.target = constraintAnnotation.target();
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String query = "SELECT c FROM " + target.getName() + " c WHERE c." + field + " = '" + o + "'";

        List lista = manager.createQuery(query).getResultList();

        return lista.isEmpty();
    }
}
