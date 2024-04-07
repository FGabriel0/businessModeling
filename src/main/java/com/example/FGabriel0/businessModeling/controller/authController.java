package com.example.FGabriel0.businessModeling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FGabriel0.businessModeling.dto.authDTO;
import com.example.FGabriel0.businessModeling.dto.loginResponseDTO;
import com.example.FGabriel0.businessModeling.dto.registerDTO;
import com.example.FGabriel0.businessModeling.entity.Cliente;
import com.example.FGabriel0.businessModeling.infra.tokenService;
import com.example.FGabriel0.businessModeling.repository.clienteRepository;

import jakarta.validation.Valid;

@RequestMapping("/auth")
@RestController
public class authController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private clienteRepository repository;
	
	@Autowired
	private tokenService service;
	
	@PostMapping("/login")
	 public ResponseEntity login(@RequestBody @Valid authDTO data) {
       
            var userNamePassword = new UsernamePasswordAuthenticationToken(data.nome(), data.password());            
            var authentication = this.authenticationManager.authenticate(userNamePassword);
            
            String token = service.generateToken((Cliente)authentication.getPrincipal());

            return ResponseEntity.ok(new loginResponseDTO(token));

    }

	
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid registerDTO data) {
		if(repository.findByNome(data.getNome()) != null) {
			return ResponseEntity.badRequest().build();
		}
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
		
		Cliente newCliente = Cliente.builder()
				.nome(data.getNome())
				.cpf(data.getCpf())
				.password(encryptedPassword)
				.role(data.getRole())
				.build();
		
		
		repository.save(newCliente);
		
		return ResponseEntity.ok().build();
		
		
	}
}
