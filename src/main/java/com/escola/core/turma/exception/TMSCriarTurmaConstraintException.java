package com.escola.core.turma.exception;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Getter
public class TMSCriarTurmaConstraintException extends ConstraintViolationException {

    private static final long serialVersionUID = 1L;

    public TMSCriarTurmaConstraintException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }
}
