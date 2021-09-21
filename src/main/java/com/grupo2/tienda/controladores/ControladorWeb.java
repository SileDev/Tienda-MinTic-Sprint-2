package com.grupo2.tienda.controladores;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.grupo2.tienda.implemetaciones.ImplementacionUsuarios;
import com.grupo2.tienda.modelos.UsuarioDTO;

@Controller
public class ControladorWeb {
	
	@Autowired
	private ImplementacionUsuarios servicioUsuarios;
	
	@GetMapping({"/","/Ingreso"})
	public String Ingreso(Model modelo) {
		
		List<UsuarioDTO> FilasUsuarios = servicioUsuarios.ListarUsuarios();
		
		if( FilasUsuarios.size() == 0 ) {
			
			//Recuperacion del usuario inicial
			servicioUsuarios.AgregarUsuario(0, "Administrador Inicial", "admininicial", "No registrado", "admin123456");
			
		}
		
		return "Ingreso/index";
		
	}
	
	@GetMapping("/Inicio")
	public String Inicio(Principal principal, Model modelo) {

		UsuarioDTO usuarioSesion = servicioUsuarios.ObtenerUsuarioSesion(principal.getName());
		
		modelo.addAttribute("usuarioSesion", usuarioSesion);
		
		return "Inicio/index";
	}
	
}
