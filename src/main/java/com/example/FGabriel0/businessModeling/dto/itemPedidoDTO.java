package com.example.FGabriel0.businessModeling.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class itemPedidoDTO {
	
	private Integer produto;
	private Integer quantidade;
	
}
