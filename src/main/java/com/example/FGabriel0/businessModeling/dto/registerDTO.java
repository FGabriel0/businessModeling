package com.example.FGabriel0.businessModeling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class registerDTO {

	private String nome;
	private String cpf;
	private String password;
	private String role;
}
