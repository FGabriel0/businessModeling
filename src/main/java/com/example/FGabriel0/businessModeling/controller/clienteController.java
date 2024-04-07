package com.example.FGabriel0.businessModeling.controller;


import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.FGabriel0.businessModeling.entity.Cliente;
import com.example.FGabriel0.businessModeling.entity.Produto;
import com.example.FGabriel0.businessModeling.repository.clienteRepository;

import jakarta.validation.Valid;

@RestController // Annotation que especializada de controller
@RequestMapping("/api/clientes")
public class clienteController {

	private clienteRepository repository;

	public clienteController(clienteRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/{id}")
	public Cliente exibircliente(@PathVariable Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND,
						"Cliente não encontrado"));

	}

	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvaCliente(@RequestBody  @Valid Cliente cliente) {
		return repository.save(cliente);
	}
	

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCliente(@PathVariable Integer id) {
		 repository.findById(id)
		 .map(cliente -> {
			 repository.delete(cliente);
			 return cliente;
		 })
		 .orElseThrow(() -> new ResponseStatusException(
				 	HttpStatus.NOT_FOUND, 
				 	"Cliente não encontrado"));
		 

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarCliente(@PathVariable Integer id,
							   @RequestBody @Valid Cliente cliente) {
		 repository.findById(id).map(update -> {
			cliente.setId(update.getId());
			repository.save(cliente);
			return update;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<Cliente> find(Cliente filtro){
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example example = Example.of(filtro, matcher);
		return repository.findAll(example);
	}

}
