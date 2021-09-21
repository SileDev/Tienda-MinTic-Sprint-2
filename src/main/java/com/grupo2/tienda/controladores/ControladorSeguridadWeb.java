package com.grupo2.tienda.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.grupo2.tienda.servicios.ServicioSesion;

@Configuration
@EnableWebSecurity
public class ControladorSeguridadWeb extends WebSecurityConfigurerAdapter{
	
	String[] recursos = new String[] {
			"/Estilo/**","/Recursos/**"/*,"/Js/**","/Scripts/**"*/
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http
			.authorizeRequests()
			.antMatchers(recursos).permitAll()
			.antMatchers("/Ingreso").permitAll()
			/*.antMatchers("/Inicio*", "/Usuarios*").access("hasRole('USER')")*/
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/Ingreso").permitAll()
				.defaultSuccessUrl("/Inicio")
				.failureUrl("/Ingreso?error=true")
				.usernameParameter("requestUser")
				.passwordParameter("requestPassword")
				.and()
			.logout()
			.permitAll()
			.logoutSuccessUrl("/Ingreso?out=true");
		
	}
	
	
	BCryptPasswordEncoder CodificadorContraseñas;
	
	@Bean
	public BCryptPasswordEncoder codificadorContraseñas() {
		
		CodificadorContraseñas = new BCryptPasswordEncoder(4);
		
		return CodificadorContraseñas;
		
	}
	
	@Autowired
	ServicioSesion servicioDetallesUsuario;
	
	@Autowired
	public void configureGlobal( AuthenticationManagerBuilder autentificador) throws Exception {
		
		autentificador.userDetailsService(servicioDetallesUsuario).passwordEncoder(codificadorContraseñas());
		
	}

}
