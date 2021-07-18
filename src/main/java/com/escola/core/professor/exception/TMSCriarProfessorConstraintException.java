package com.escola.core.professor.exception;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Getter
public class TMSCriarProfessorConstraintException extends ConstraintViolationException {


    private static final long serialVersionUID = 6753621026401646717L;

    public TMSCriarProfessorConstraintException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }
}
