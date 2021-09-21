package com.grupo2.tienda.modelos;

public class UsuarioDTO {

	private long cedula;
	private String nombre;
	private String usuario;
	private String correo;

	public UsuarioDTO(long cedula, String nombre, String usuario, String correo) {

		this.cedula = cedula;
		this.nombre = nombre;
		this.usuario = usuario;
		this.correo = correo;

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

}
