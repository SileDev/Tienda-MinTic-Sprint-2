package com.grupo2.tienda.servicios;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grupo2.tienda.entidades.Autoridades;
import com.grupo2.tienda.repositorios.RepositorioSesion;

@Service
public class ServicioSesion implements UserDetailsService {

	@Autowired
	RepositorioSesion repositorioUsuarios;

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {

		com.grupo2.tienda.entidades.Usuarios Usuario = repositorioUsuarios.findByUsuario(usuario)
				.orElseThrow(() -> new UsernameNotFoundException("Esta excepción está cubierta desde otro lugar"));

		List<GrantedAuthority> ListaAutoridades = new ArrayList<GrantedAuthority>();

		for (Autoridades autoridad : Usuario.getAutoridad()) {

			GrantedAuthority autoridadObtenida = new SimpleGrantedAuthority(autoridad.getAutoridad());

			ListaAutoridades.add(autoridadObtenida);

		}
		
		UserDetails resultadoUsuario = (UserDetails) new User(Usuario.getUsuario(), Usuario.getClave(), ListaAutoridades);
		return resultadoUsuario;
		
	}

}
