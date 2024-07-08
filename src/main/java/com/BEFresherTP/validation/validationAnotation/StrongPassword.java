package com.BEFresherTP.validation.validationAnotation;

import com.BEFresherTP.validation.StrongPasswordValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = StrongPasswordValidation.class)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrongPassword {
    String message() default "Password must be contain uppercase letters, " +
            "lowercase letters, " +
            "numbers, " +
            "special characters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
