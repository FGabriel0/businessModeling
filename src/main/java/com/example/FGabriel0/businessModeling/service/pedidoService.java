package com.example.FGabriel0.businessModeling.service;

import java.util.Optional;

import com.example.FGabriel0.businessModeling.dto.pedidoDTO;
import com.example.FGabriel0.businessModeling.entity.Pedido;
import com.example.FGabriel0.businessModeling.enums.statusPedido;

public interface pedidoService {

	Pedido salvar(pedidoDTO dto);
	
	Optional<Pedido> obterPedido(Integer id);
	
	void atualizarStatus(Integer id, statusPedido status );
}
