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

import com.example.FGabriel0.businessModeling.entity.Produto;
import com.example.FGabriel0.businessModeling.repository.produtoRepository;

@RestController
@RequestMapping("/api/produto")
public class produtoController {
	
	private produtoRepository repository;

	public produtoController(produtoRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/{id}")
	public Produto exibirProdutos(@PathVariable Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, 
						"Produto não encontrado"));
	}
		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto salvaProduto(@RequestBody Produto produto) {
		return repository.save(produto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduto(@PathVariable Integer id) {
		repository.findById(id)
		.map(produto -> {
			repository.delete(produto);
			return produto;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateProduto(@PathVariable Integer id, @RequestBody Produto produto) {
		repository.findById(id)
		.map(update -> {
			produto.setId(update.getId());
			repository.save(produto);
			return update;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
	@GetMapping
	public List<Produto> find(Produto filtro){
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example example = Example.of(filtro, matcher);
		return repository.findAll(example);
	}
	
}
