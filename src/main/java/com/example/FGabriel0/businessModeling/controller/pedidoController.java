package com.example.FGabriel0.businessModeling.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.FGabriel0.businessModeling.dto.inforItemPedidoDTO;
import com.example.FGabriel0.businessModeling.dto.inforPedidoDTO;
import com.example.FGabriel0.businessModeling.dto.pedidoDTO;
import com.example.FGabriel0.businessModeling.dto.updateStatusPedidoDTO;
import com.example.FGabriel0.businessModeling.entity.ItemPedido;
import com.example.FGabriel0.businessModeling.entity.Pedido;
import com.example.FGabriel0.businessModeling.enums.statusPedido;
import com.example.FGabriel0.businessModeling.exception.RegraNegocioException;
import com.example.FGabriel0.businessModeling.service.pedidoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/pedido")
public class pedidoController {

		private pedidoService service;

		public pedidoController(pedidoService service) {
			this.service = service;
		}
		
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public Integer save(@RequestBody @Valid pedidoDTO dto) {
			Pedido pedido = service.salvar(dto);
			return pedido.getId();
		}
		
		
		@GetMapping("/{id}")
		public inforPedidoDTO getById(@PathVariable Integer id) {
			return service.obterPedido(id)
					.map(p -> converter(p)
						).orElseThrow(() -> new RegraNegocioException("Pedido não encontrado"));
		}
		
		
		@PatchMapping("/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void updateStatus(@PathVariable Integer id
								,@RequestBody @Valid updateStatusPedidoDTO dto) {
			String novoStatus = dto.getNovoStatus();
			service.atualizarStatus(id, statusPedido.valueOf(novoStatus));
		}
		
		private inforPedidoDTO converter(Pedido pedido) {
			return inforPedidoDTO.builder()
				.codigo(pedido.getId())
				.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
				.nomeCliente(pedido.getCliente().getNome())
				.cpf(pedido.getCliente().getCpf())
				.total(pedido.getTotal())
				.status(pedido.getStatus().name())
				.item(converter(pedido.getItens()))
				.build();
		}
		
		private List<inforItemPedidoDTO> converter(List<ItemPedido> item){
			if(CollectionUtils.isEmpty(item)) {
				return Collections.emptyList();
			}
			
			return item.stream()
					.map(itens -> 
						inforItemPedidoDTO.builder()
						.descricao(itens.getProduto().getDescricao())
						.precoUnitario(itens.getProduto().getPreco())
						.quantidade(itens.getQuantidade())
						.build()
						).collect(Collectors.toList());
						
		}
}
