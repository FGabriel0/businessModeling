package com.example.FGabriel0.businessModeling.exception;

public class PedidoNaoEncontradoException extends RuntimeException{
	
	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado");
	}
}
