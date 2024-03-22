package com.example.FGabriel0.businessModeling.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class inforPedidoDTO {
	
	private Integer codigo;
	private String cpf;
	private String nomeCliente;
	private BigDecimal total;
	private String dataPedido;
	private String status;
	private List<inforItemPedidoDTO> item;
}
