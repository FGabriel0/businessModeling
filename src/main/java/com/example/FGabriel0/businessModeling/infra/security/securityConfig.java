package com.example.FGabriel0.businessModeling.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class securityConfig {
	
	@Autowired
	securityFilter filter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		return httpSecurity.csrf(
				csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize			
						.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
						.requestMatchers(HttpMethod.GET,"/api/produto").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/produto").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT,"/api/produto").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/api/produto").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/clientes").permitAll()
						.requestMatchers(HttpMethod.POST,"/api/pedido").permitAll()
						.requestMatchers(HttpMethod.GET,"/api/pedido").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PATCH,"/api/pedido").hasRole("ADMIN")
				.anyRequest().authenticated()
				)
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
