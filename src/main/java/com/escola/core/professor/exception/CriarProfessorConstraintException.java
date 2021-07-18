package com.escola.core.professor.exception;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Getter
public class CriarProfessorConstraintException extends ConstraintViolationException {


    private static final long serialVersionUID = -3867112790224451517L;

    public CriarProfessorConstraintException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }
}
