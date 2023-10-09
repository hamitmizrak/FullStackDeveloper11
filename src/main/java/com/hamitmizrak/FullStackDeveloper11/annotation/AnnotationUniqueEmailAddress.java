package com.hamitmizrak.FullStackDeveloper11.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

// Email Address unique
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueEmailAddressValidation.class})
public @interface AnnotationUniqueEmailAddress {

    String message() default "{register.email.validation.constraints.unique.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
} //end class