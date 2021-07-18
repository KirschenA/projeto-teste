package com.escola.core.turma.exception;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Getter
public class CriarTurmaConstraintException extends ConstraintViolationException {

    private static final long serialVersionUID = 3724082638640098652L;

    public CriarTurmaConstraintException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }
}
