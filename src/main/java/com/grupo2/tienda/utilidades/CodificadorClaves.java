package com.grupo2.tienda.utilidades;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CodificadorClaves {

	public String CodificarClave(String clave) {

		BCryptPasswordEncoder codificador = new BCryptPasswordEncoder(4);

		return codificador.encode(clave);

	}

}
