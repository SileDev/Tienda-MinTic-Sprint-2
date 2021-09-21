package com.grupo2.tienda.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo2.tienda.entidades.Usuarios;

@Repository
public interface RepositorioSesion extends CrudRepository <Usuarios , Long>{
	
	public Optional<Usuarios> findByUsuario(String usuario);

}
