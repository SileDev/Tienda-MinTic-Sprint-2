package com.grupo2.tienda.modelos;

public class ClienteDTO {
	
	private long cedula;
	
	private String nombre;
	
	private String direccion;
	
	private String telefono;
	
	private String correo;

	public ClienteDTO(long cedula, String nombre, String direccion, String telefono, String correo) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
	}

	public long getCedula() {
		return cedula;
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

	public String getCorreo() {
		return correo;
	}
	
}
