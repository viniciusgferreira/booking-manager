package com.hostfully.bookingmanager.controllers.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateTimeRangeValidator.class)
public @interface DateTimeRange {
    String message() default "Invalid date range. End date must be greater than start date.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}