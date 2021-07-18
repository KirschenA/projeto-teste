package com.escola.core.disciplina.exception;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Getter
public class TMSCriarDisciplinaConstraintException extends ConstraintViolationException {

    private static final long serialVersionUID = 1L;

    public TMSCriarDisciplinaConstraintException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }
}
