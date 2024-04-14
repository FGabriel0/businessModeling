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

import com.example.FGabriel0.businessModeling.controller.form.authForm;
import com.example.FGabriel0.businessModeling.controller.form.registerForm;
import com.example.FGabriel0.businessModeling.dto.loginResponseDTO;
import com.example.FGabriel0.businessModeling.entity.Cliente;
import com.example.FGabriel0.businessModeling.entity.Usuario;
import com.example.FGabriel0.businessModeling.enums.UserRole;
import com.example.FGabriel0.businessModeling.infra.security.tokenService;
import com.example.FGabriel0.businessModeling.repository.clienteRepository;
import com.example.FGabriel0.businessModeling.repository.usuarioRepository;

import jakarta.validation.Valid;

@RequestMapping("/auth")
@RestController
public class authController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private usuarioRepository repository;
	
	@Autowired
	private tokenService service;
	
	@PostMapping("/login")
	 public ResponseEntity login(@RequestBody @Valid authForm data) {
       
            var userNamePassword = new UsernamePasswordAuthenticationToken(data.nome(), data.password());            
            var authentication = authenticationManager.authenticate(userNamePassword);
            
            String token = service.generateToken((Usuario)authentication.getPrincipal());

            return ResponseEntity.ok(new loginResponseDTO(token));

    }

	
	
	@PostMapping("/register")
	public ResponseEntity<Usuario> register(@RequestBody @Valid registerForm data) {
		if(repository.findByLogin(data.getNome()) != null) {
			return ResponseEntity.badRequest().build();
		}
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
		
		Usuario newUsuario = new Usuario(data.getNome(),encryptedPassword,data.getRole());
				
		repository.save(newUsuario);
		
		return ResponseEntity.ok().build();
		
		
	}
}
