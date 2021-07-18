package com.escola.core.disciplina.exception;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Getter
public class CriarDisciplinaConstraintException extends ConstraintViolationException {

    private static final long serialVersionUID = 2627807828909014365L;

    public CriarDisciplinaConstraintException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }
}
