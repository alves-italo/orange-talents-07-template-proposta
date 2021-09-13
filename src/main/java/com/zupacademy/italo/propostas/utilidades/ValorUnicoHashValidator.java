package com.zupacademy.italo.propostas.utilidades;

import com.zupacademy.italo.propostas.utilidades.criptografia.Codificador;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;


public class ValorUnicoHashValidator implements ConstraintValidator<ValorUnicoHash, Object> {
    private Class<?> target;
    private Object field;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ValorUnicoHash constraintAnnotation) {
        this.target = constraintAnnotation.target();
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Codificador codificador = Codificador.getCodificador();
        String hash = codificador.hash((String) o);

        String query = "SELECT c FROM " + target.getName() + " c WHERE c." + field + " = '" + hash + "'";

        return manager.createQuery(query).getResultList().isEmpty();
    }
}
