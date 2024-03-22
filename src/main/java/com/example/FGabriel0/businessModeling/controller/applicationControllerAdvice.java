package com.example.FGabriel0.businessModeling.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.FGabriel0.businessModeling.erros.apiErros;
import com.example.FGabriel0.businessModeling.exception.PedidoNaoEncontradoException;
import com.example.FGabriel0.businessModeling.exception.RegraNegocioException;

//Tratamento de Erros
@RestControllerAdvice
public class applicationControllerAdvice {
	
	@ExceptionHandler(RegraNegocioException.class)//Vai marcar o método como tratante de erro
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public apiErros handlerRegraNegocioException(RegraNegocioException ex) {
		String mensagemError = ex.getMessage();
		return new apiErros(mensagemError);
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public apiErros handlerPedidoNotFoundException(PedidoNaoEncontradoException ex) {
		return new apiErros(ex.getMessage());
	}
}
