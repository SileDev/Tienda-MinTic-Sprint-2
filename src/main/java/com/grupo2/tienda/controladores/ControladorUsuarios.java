package com.grupo2.tienda.controladores;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grupo2.tienda.implemetaciones.ImplementacionUsuarios;
import com.grupo2.tienda.modelos.UsuarioDTO;

@Controller
public class ControladorUsuarios {

	@Autowired
	private ImplementacionUsuarios servicioUsuarios;

	@GetMapping("/Usuarios")
	public String Usuarios(@Param("buscar") boolean buscar, @Param("resultado") boolean resultado,
			@Param("cedula") String cedula, @Param("agregado") boolean agregado, @Param("existente") boolean existente,
			@Param("editado") boolean editado, @Param("eliminado") boolean eliminado, Principal principal,
			Model modelo) {

		UsuarioDTO usuarioSesion = servicioUsuarios.ObtenerUsuarioSesion(principal.getName());

		modelo.addAttribute("usuarioSesion", usuarioSesion);

		if (resultado) {

			UsuarioDTO ResultadoBusqueda = servicioUsuarios.ObtenerUsuario(Long.parseLong(cedula));

			modelo.addAttribute("ResultadoBusqueda", ResultadoBusqueda);

		}

		List<UsuarioDTO> ListaUsuarios = servicioUsuarios.ListarUsuarios();

		modelo.addAttribute("ListaUsuarios", ListaUsuarios);

		return "Usuarios/index";
	}

	@GetMapping("/AgregarUsuario")
	public String AgregarUsuario(@RequestParam("cedula") long cedula, @RequestParam("nombre") String nombre,
			@RequestParam("usuario") String usuario, @RequestParam("correo") String correo,
			@RequestParam("clave") String clave) {

		String resultado;

		if (!servicioUsuarios.ValidarUsuario(cedula)) {

			servicioUsuarios.AgregarUsuario(cedula, nombre, usuario, correo, clave);

			resultado = "?agregado=true";

		} else {

			resultado = "?existente=true&cedula=" + cedula;

		}

		return "redirect:Usuarios" + resultado;

	}

	@GetMapping("/EditarUsuario")
	public String EditarUsuario(@RequestParam("cedula") long cedula, Principal principal, Model modelo) {

		UsuarioDTO usuarioSesion = servicioUsuarios.ObtenerUsuarioSesion(principal.getName());

		modelo.addAttribute("usuarioSesion", usuarioSesion);

		UsuarioDTO usuarioObtenido = servicioUsuarios.ObtenerUsuario(cedula);

		modelo.addAttribute("usuarioObtenido", usuarioObtenido);

		return "/Usuarios/Editar/index";

	}

	@GetMapping("/ModificarUsuario")
	public String ProcesoEditarUsuario(@RequestParam("cedula") long cedula, @RequestParam("nombre") String nombre,
			@RequestParam("usuario") String usuario, @RequestParam("correo") String correo,
			@RequestParam("clave") String clave) {

		servicioUsuarios.EditarUsuario(cedula, nombre, usuario, correo, clave);

		return "redirect:Usuarios?editado=true";

	}

	@GetMapping("/EliminarUsuario")
	public String EliminarUsuario(@RequestParam("cedula") long cedula, Model modelo) {

		servicioUsuarios.EliminarUsuario(cedula);

		return "redirect:Usuarios?eliminado=true";

	}

	@GetMapping("/BuscarUsuario")
	public String BuscarUsuario(@RequestParam("cedula") long cedula) {

		if (servicioUsuarios.ValidarUsuario(cedula)) {

			return "redirect:Usuarios?buscar=true&resultado=true&cedula=" + cedula;

		} else {

			return "redirect:Usuarios?buscar=true";

		}

	}

}
