package com.grupo2.tienda.modelos;


public class ProveedorDTO {

	private Long nit;
	
	private String nombre;
	
	private String direccion;
	
	private String telefono;
	
	private String ciudad;

	public ProveedorDTO(Long nit, String nombre, String direccion, String telefono, String ciudad) {
		this.nit = nit;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.ciudad = ciudad;
	}

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
