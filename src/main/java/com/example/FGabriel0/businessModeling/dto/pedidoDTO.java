package com.example.FGabriel0.businessModeling.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class pedidoDTO {
	
	private Integer cliente;
	private BigDecimal total;
	private List<itemPedidoDTO> itens;
}
