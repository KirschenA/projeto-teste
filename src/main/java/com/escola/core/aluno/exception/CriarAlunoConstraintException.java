package com.escola.core.aluno.exception;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Getter
public class CriarAlunoConstraintException extends ConstraintViolationException {


    private static final long serialVersionUID = 7927953991751530655L;

    public CriarAlunoConstraintException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }
}
