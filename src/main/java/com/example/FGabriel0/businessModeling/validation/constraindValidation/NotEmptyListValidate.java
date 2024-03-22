package com.example.FGabriel0.businessModeling.validation.constraindValidation;

import java.util.List;

import com.example.FGabriel0.businessModeling.validation.NotEmptyList;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEmptyListValidate implements ConstraintValidator<NotEmptyList, List>{

	@Override
	public boolean isValid(List value, ConstraintValidatorContext context) {
		return value != null && !value.isEmpty();
	}

	@Override
	public void initialize(NotEmptyList constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	

}
