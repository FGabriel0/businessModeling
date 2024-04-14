package com.example.FGabriel0.businessModeling.controller.form;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class itemPedidoForm {
	
	private Integer produto;
	private Integer quantidade;
	
}
