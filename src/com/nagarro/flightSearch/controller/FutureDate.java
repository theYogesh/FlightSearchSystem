package com.nagarro.flightSearch.controller;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import javax.validation.Payload;

@Documented
@Constraint(validatedBy = FutureDateValidator.class)
@Target( {ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
public @interface FutureDate {
 
     
    String message() default "Invalid Date: Date is passed";
     
    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};
      
}