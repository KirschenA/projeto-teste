package com.escola.util;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

@Component
public class ValidateService {

    private final Validator validator;

    public ValidateService() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.usingContext().getValidator();
    }

    public <T> Optional<Set<ConstraintViolation<T>>> validate(T obj, Class<?>... groups) {
        Assert.notNull(obj, "could not be null");
        Set<ConstraintViolation<T>> violations = this.validator.validate(obj, groups);
        return violations.isEmpty() ? Optional.empty() : Optional.ofNullable(violations);

    }
}
