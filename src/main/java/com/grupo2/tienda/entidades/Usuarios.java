package com.grupo2.tienda.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuarios {
	
	@Id
	@Column(name = "cedula")
	private Long cedula;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "usuario")
	private String usuario;
	
	@Column(name = "correo")
	private String correo;
	
	@Column(name = "clave")
	private String clave;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "autoridades_usuarios", joinColumns = @JoinColumn(name = "cedula"), inverseJoinColumns = @JoinColumn(name = "autoridad"))
	private Set<Autoridades> autoridad;

	@Override
	public int hashCode() {
		
		final int primo = 31;
		int resultado = 1;
		
		resultado = primo * resultado + ((cedula == null) ? 0 : cedula.hashCode());
		
		return resultado;
		
	}
	
	@Override
	public boolean equals(Object objetoReferencia) {
		
		if (this == objetoReferencia)
			return true;
		
		if (objetoReferencia == null)
			return false;
		
		if (getClass() != objetoReferencia.getClass())
			return false;
		
		Usuarios usuarioReferencia = (Usuarios) objetoReferencia;
		
		if (cedula == null) {
			
			if(usuarioReferencia.cedula != null)
				
				return false;
			
		} else if (!cedula.equals(usuarioReferencia.cedula)) 
			
			return false;
		
		return true;
		
	}

	public long getCedula() {
		
		return cedula;
		
	}

	public String getNombre() {
		
		return nombre;
		
	}
	
	public String getUsuario() {
		
		return usuario;
		
	}

	public String getCorreo() {
		
		return correo;
		
	}

	public String getClave() {

		return clave;

	}
	
	public Set<Autoridades> getAutoridad(){
		
		return autoridad;
		
	}

}
