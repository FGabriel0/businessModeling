package com.example.FGabriel0.businessModeling.dto;

import java.math.BigDecimal;
import java.util.List;

import com.example.FGabriel0.businessModeling.validation.NotEmptyList;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class pedidoDTO {
	
	@NotNull(message = "{campo.codigo-cliente.obrigatorio}")
	private Integer cliente;
	
	@NotNull(message = "{campo.total-pedido.obrigatorio}")
	private BigDecimal total;
	
	@NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
	private List<itemPedidoDTO> itens;
}
