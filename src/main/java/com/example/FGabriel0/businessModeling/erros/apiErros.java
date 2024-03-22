package com.example.FGabriel0.businessModeling.erros;

import java.util.List;
import java.util.Arrays;

import lombok.Getter;


public class apiErros {
	
	@Getter
	private List<String> errors;

	public apiErros(String msgError) {
		this.errors = Arrays.asList(msgError);
	}
	
	public apiErros(List<String> errors) {
		this.errors = errors;
	}
	
	
	
}
