package com.grupo2.tienda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "autoridades")
public class Autoridades {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cedula;
	
	@Column
	private String autoridad;

	public String getAutoridad() {
		return autoridad;
	}

	public void setAutoridad(String autoridad) {
		this.autoridad = autoridad;
	}

}
