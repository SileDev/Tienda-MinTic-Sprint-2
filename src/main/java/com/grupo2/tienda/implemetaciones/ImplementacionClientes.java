package com.grupo2.tienda.implemetaciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.grupo2.tienda.modelos.ClienteDTO;

public class ImplementacionClientes {
	
	private JdbcTemplate jdbcTemp;

	public ImplementacionClientes(DataSource fuenteDatos) {

		jdbcTemp = new JdbcTemplate(fuenteDatos);

	}

	public void AgregarCliente(long cedula, String nombre, String direccion, String telefono, String correo) {

		jdbcTemp.execute("INSERT INTO clientes (cedula, nombre, direccion, telefono, correo) VALUES ('" + cedula + "', '"
				+ nombre + "', '" + direccion + "', '" + telefono + "', '" + correo + "')");

	}

	public void EditarCliente(long cedulaAnterior, long cedula, String nombre, String direccion, String telefono,
			String correo) {

		jdbcTemp.execute("UPDATE clientes SET cedula = '" + cedula + "', nombre = '" + nombre + "', direccion = '"
				+ direccion + "', telefono = '" + telefono + "', correo = '" + correo + "' WHERE cedula = '" + cedulaAnterior
				+ "'");

	}

	public void EliminarCliente(long cedula) {

		jdbcTemp.execute("DELETE FROM clientes WHERE cedula = '" + cedula + "'");

	}

	public ClienteDTO ObtenerCliente(long cedula) {

		List<ClienteDTO> ResultadoBusqueda = jdbcTemp.query("SELECT * FROM clientes WHERE cedula = '" + cedula + "'",
				new RowMapper<ClienteDTO>() {

					@Override
					public ClienteDTO mapRow(ResultSet resultado, int indice) throws SQLException {

						ClienteDTO cliente = new ClienteDTO(

								resultado.getLong("cedula"), resultado.getString("nombre"),
								resultado.getString("direccion"), resultado.getString("telefono"),
								resultado.getString("correo")

				);

						return cliente;

					}

				});

		ClienteDTO Cliente = new ClienteDTO(

				ResultadoBusqueda.get(0).getCedula(), ResultadoBusqueda.get(0).getNombre(),
				ResultadoBusqueda.get(0).getDireccion(), ResultadoBusqueda.get(0).getTelefono(),
				ResultadoBusqueda.get(0).getCorreo()

		);

		return Cliente;

	}

	public boolean ValidarCliente(long cedula) {

		List<ClienteDTO> lista = jdbcTemp.query("SELECT * FROM clientes WHERE cedula = '" + cedula + "'",
				new RowMapper<ClienteDTO>() {

					@Override
					public ClienteDTO mapRow(ResultSet resultado, int indice) throws SQLException {

						ClienteDTO cliente = new ClienteDTO(

								resultado.getLong("cedula"), resultado.getString("nombre"),
								resultado.getString("direccion"), resultado.getString("telefono"),
								resultado.getString("correo")

				);

						return cliente;

					}

				});

		if (lista.size() != 0) {

			return true;

		} else {

			return false;

		}

	}

	public List<ClienteDTO> ListarClientes() {

		List<ClienteDTO> lista = jdbcTemp.query("SELECT * FROM clientes", new RowMapper<ClienteDTO>() {

			@Override
			public ClienteDTO mapRow(ResultSet resultado, int indice) throws SQLException {

				ClienteDTO cliente = new ClienteDTO(

						resultado.getLong("cedula"), resultado.getString("nombre"), resultado.getString("direccion"),
						resultado.getString("telefono"), resultado.getString("correo")

				);

				return cliente;

			}

		});

		return lista;

	}

}
