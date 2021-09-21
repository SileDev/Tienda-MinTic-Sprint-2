package com.grupo2.tienda.controladores;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grupo2.tienda.implemetaciones.ImplementacionClientes;
import com.grupo2.tienda.implemetaciones.ImplementacionUsuarios;
import com.grupo2.tienda.modelos.ClienteDTO;
import com.grupo2.tienda.modelos.UsuarioDTO;

@Controller
public class ControladorClientes {

	@Autowired
	private ImplementacionUsuarios servicioUsuarios;

	@Autowired
	private ImplementacionClientes servicioClientes;

	@GetMapping("/Clientes")
	public String Clientes(@Param("buscar") boolean buscar, @Param("resultado") boolean resultado,
			@Param("cedula") String cedula, @Param("agregado") boolean agregado, @Param("existente") boolean existente,
			@Param("editado") boolean editado, @Param("eliminado") boolean eliminado, Principal principal,
			Model modelo) {

		UsuarioDTO usuarioSesion = servicioUsuarios.ObtenerUsuarioSesion(principal.getName());

		modelo.addAttribute("usuarioSesion", usuarioSesion);

		if (resultado) {

			ClienteDTO resultadoBusqueda = servicioClientes.ObtenerCliente(Long.parseLong(cedula));

			modelo.addAttribute("resultadoBusqueda", resultadoBusqueda);

		}

		List<ClienteDTO> ListaClientes = servicioClientes.ListarClientes();

		modelo.addAttribute("ListaClientes", ListaClientes);

		return "Clientes/index";
	}

	@GetMapping("/AgregarCliente")
	public String AgregarCliente(@RequestParam("cedula") long cedula, @RequestParam("nombre") String nombre,
			@RequestParam("direccion") String direccion, @RequestParam("telefono") String telefono,
			@RequestParam("correo") String correo) {

		String resultado;

		if (!servicioClientes.ValidarCliente(cedula)) {

			servicioClientes.AgregarCliente(cedula, nombre, direccion, telefono, correo);

			resultado = "?agregado=true";

		} else {

			resultado = "?existente=true&cedula=" + cedula;

		}

		return "redirect:Clientes" + resultado;

	}

	@GetMapping("/EditarCliente")
	public String EditarCliente(@RequestParam("cedula") long cedula, Principal principal, Model modelo) {

		UsuarioDTO usuarioSesion = servicioUsuarios.ObtenerUsuarioSesion(principal.getName());

		modelo.addAttribute("usuarioSesion", usuarioSesion);

		ClienteDTO clienteObtenido = servicioClientes.ObtenerCliente(cedula);

		modelo.addAttribute("clienteObtenido", clienteObtenido);

		return "/Clientes/Editar/index";

	}

	@GetMapping("/ModificarCliente")
	public String ProcesoEditarCliente(@RequestParam("cedulaAnterior") long cedulaAnterior,
			@RequestParam("cedula") long cedula, @RequestParam("nombre") String nombre,
			@RequestParam("direccion") String direccion, @RequestParam("telefono") String telefono,
			@RequestParam("correo") String correo) {

		servicioClientes.EditarCliente(cedulaAnterior, cedula, nombre, direccion, telefono, correo);

		return "redirect:Clientes?editado=true";

	}

	@GetMapping("/EliminarCliente")
	public String EliminarCliente(@RequestParam("cedula") long cedula) {

		servicioClientes.EliminarCliente(cedula);

		return "redirect:Clientes?eliminado=true";

	}

	@GetMapping("/BuscarCliente")
	public String BuscarCliente(@RequestParam("cedula") long cedula) {

		if (servicioClientes.ValidarCliente(cedula)) {

			return "redirect:Clientes?buscar=true&resultado=true&cedula=" + cedula;

		} else {

			return "redirect:Clientes?buscar=true";

		}

	}

}
