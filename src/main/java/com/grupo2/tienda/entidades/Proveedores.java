package com.grupo2.tienda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proveedores")
public class Proveedores {

	@Id
	@Column(name = "nit")
	private Long nit;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "ciudad")
	private String ciudad;

	
	public Long getNit() {
		return nit;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getCiudad() {
		return ciudad;
	}	
	
}
