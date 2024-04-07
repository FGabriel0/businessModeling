package com.example.FGabriel0.businessModeling.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public apiErros handlerMethodNotValidExecption(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getAllErrors()
		.stream()
		.map(erro -> erro.getDefaultMessage())
			.collect(Collectors.toList());
		
		return new apiErros(errors);
	}
}
