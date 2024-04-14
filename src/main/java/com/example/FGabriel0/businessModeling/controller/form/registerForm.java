package com.example.FGabriel0.businessModeling.controller.form;

import com.example.FGabriel0.businessModeling.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class registerForm {

	private String nome;
	private String password;
	private UserRole role;
}
