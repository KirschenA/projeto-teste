package com.escola.core.aluno.exception;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Getter
public class CriarAlunoConstraintException extends ConstraintViolationException {


    private static final long serialVersionUID = -2102612226881012373L;

    public CriarAlunoConstraintException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }
}
