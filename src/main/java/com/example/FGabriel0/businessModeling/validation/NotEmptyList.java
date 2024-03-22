package com.example.FGabriel0.businessModeling.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.FGabriel0.businessModeling.validation.constraindValidation.NotEmptyListValidate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidate.class)
public @interface NotEmptyList  {
		
	String message() default "A lista não pode esta vazia";
	  Class<?>[] groups() default {};
	  Class<? extends Payload>[] payload() default {};
}
