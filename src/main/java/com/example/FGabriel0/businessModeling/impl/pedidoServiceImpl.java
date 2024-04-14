package com.example.FGabriel0.businessModeling.impl;

import org.springframework.stereotype.Service;

import com.example.FGabriel0.businessModeling.controller.form.itemPedidoForm;
import com.example.FGabriel0.businessModeling.controller.form.pedidoForm;
import com.example.FGabriel0.businessModeling.entity.Pedido;
import com.example.FGabriel0.businessModeling.entity.Produto;
import com.example.FGabriel0.businessModeling.enums.statusPedido;
import com.example.FGabriel0.businessModeling.exception.PedidoNaoEncontradoException;
import com.example.FGabriel0.businessModeling.exception.RegraNegocioException;
import com.example.FGabriel0.businessModeling.repository.clienteRepository;
import com.example.FGabriel0.businessModeling.repository.itemPedidoRepository;
import com.example.FGabriel0.businessModeling.repository.pedidoRepository;
import com.example.FGabriel0.businessModeling.repository.produtoRepository;
import com.example.FGabriel0.businessModeling.service.pedidoService;

import jakarta.transaction.Transactional;

import com.example.FGabriel0.businessModeling.entity.Cliente;
import com.example.FGabriel0.businessModeling.entity.ItemPedido;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class pedidoServiceImpl implements pedidoService {
	
	private final pedidoRepository repository;
	private final clienteRepository clienterepository;
	private final produtoRepository produtorepository;
	private final itemPedidoRepository itempedidorepository;

	@Override
	@Transactional
	public Pedido salvar(pedidoForm dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clienterepository
			.findById(idCliente)
			.orElseThrow(() -> new RegraNegocioException("Código inválido"));
		
		
		Pedido pedido  = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		pedido.setStatus(statusPedido.REALIZADO);
		
		List<ItemPedido> itemPedido = salvarItens(pedido, dto.getItens());
		repository.save(pedido);
		itempedidorepository.saveAll(itemPedido);
		pedido.setItens(itemPedido);
		return pedido;
	}
	
	private List<ItemPedido> salvarItens(Pedido pedido,List<itemPedidoForm> itens) {
		
		if(itens.isEmpty()) {
			throw new RegraNegocioException("Não é possivel realizar um pedido");
		}
		
		return itens
				.stream()//você obtém um fluxo que pode ser processado de várias maneiras, como filtrar, mapear, reduzir ou coletar os elementos. 
				.map(dto -> {
					Integer idProduto = dto.getProduto();
					Produto produto = produtorepository
						.findById(idProduto)
			 			.orElseThrow(() -> new RegraNegocioException("Código do Produto Inválido: "
								+ idProduto));
					
					ItemPedido itempedido = new ItemPedido();
					itempedido.setQuantidade(dto.getQuantidade());
					itempedido.setPedido(pedido);
					itempedido.setProduto(produto);
					return itempedido;
				}).collect(Collectors.toList());
	}
	
	

	@Override
	public Optional<Pedido> obterPedido(Integer id) {
		return repository.findByIdFetchItens(id);
	}

	@Override
	@Transactional
	public void atualizarStatus(Integer id, statusPedido status) {
		repository.findById(id)
		.map(pedido -> {
			pedido.setStatus(status);
			return repository.save(pedido);
		}).orElseThrow(() -> new PedidoNaoEncontradoException());
	}
	
	
}
