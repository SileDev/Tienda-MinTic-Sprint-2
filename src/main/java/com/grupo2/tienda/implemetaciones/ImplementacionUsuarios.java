package com.grupo2.tienda.implemetaciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.grupo2.tienda.modelos.UsuarioDTO;
//import com.grupo2.tienda.repositorios.RepositorioUsuarios;
import com.grupo2.tienda.utilidades.CodificadorClaves;

public class ImplementacionUsuarios {

	CodificadorClaves codificadorClaves = new CodificadorClaves();

	private JdbcTemplate jdbcTemp;

	public ImplementacionUsuarios(DataSource fuenteDatos) {

		jdbcTemp = new JdbcTemplate(fuenteDatos);

	}

	public void AgregarUsuario(long cedula, String nombre, String usuario, String correo, String clave) {

		jdbcTemp.execute(
				"INSERT INTO usuarios (cedula, nombre, usuario, correo, clave) VALUES ('" + cedula + "', '" + nombre
						+ "', '" + usuario + "', '" + correo + "', '" + codificadorClaves.CodificarClave(clave) + "')");

		jdbcTemp.execute("INSERT INTO autoridades (cedula, autoridad) VALUES ('" + cedula + "', 'ROLE_USER')");

		jdbcTemp.execute(
				"INSERT INTO autoridades_usuarios (cedula, autoridad) VALUES ('" + cedula + "', '" + cedula + "')");

	}

	public void EditarUsuario(long cedula, String nombre, String usuario, String correo, String clave) {

		jdbcTemp.execute("UPDATE usuarios SET nombre = '" + nombre + "', usuario = '" + usuario + "', correo = '"
				+ correo + "', clave = '" + codificadorClaves.CodificarClave(clave) + "' WHERE cedula = '" + cedula + "'");

	}

	public void EliminarUsuario(long cedula) {

		jdbcTemp.execute("DELETE FROM autoridades_usuarios WHERE cedula = '" + cedula + "'");

		jdbcTemp.execute("DELETE FROM autoridades WHERE cedula = '" + cedula + "'");

		jdbcTemp.execute("DELETE FROM usuarios WHERE cedula = '" + cedula + "'");

	}

	public UsuarioDTO ObtenerUsuario(long cedula) {

		List<UsuarioDTO> ResultadoBusqueda = jdbcTemp.query(
				"SELECT cedula, nombre, usuario, correo FROM usuarios WHERE cedula = '" + cedula + "'",
				new RowMapper<UsuarioDTO>() {

					int i = 0;

					@Override
					public UsuarioDTO mapRow(ResultSet resultado, int indice) throws SQLException {

						i++;

						System.out.println("Se hallaron " + i + " filas de usuarios");

						UsuarioDTO usuario = new UsuarioDTO(

								resultado.getLong("cedula"), resultado.getString("nombre"),
								resultado.getString("usuario"), resultado.getString("correo")

				);

						return usuario;

					}

				});

		UsuarioDTO Usuario = new UsuarioDTO(

				ResultadoBusqueda.get(0).getCedula(), ResultadoBusqueda.get(0).getNombre(),
				ResultadoBusqueda.get(0).getUsuario(), ResultadoBusqueda.get(0).getCorreo()

		);

		return Usuario;

	}

	public boolean ValidarUsuario(long cedula) {

		List<UsuarioDTO> lista = jdbcTemp.query("SELECT * FROM usuarios WHERE cedula = '" + cedula + "'",
				new RowMapper<UsuarioDTO>() {

					@Override
					public UsuarioDTO mapRow(ResultSet resultado, int indice) throws SQLException {

						UsuarioDTO usuario = new UsuarioDTO(

								resultado.getLong("cedula"), resultado.getString("nombre"),
								resultado.getString("usuario"), resultado.getString("correo")

				);

						return usuario;

					}

				});

		if (lista.size() != 0) {

			return true;

		} else {

			return false;

		}

	}

	public List<UsuarioDTO> ListarUsuarios() {

		List<UsuarioDTO> lista = jdbcTemp.query("SELECT cedula, nombre, usuario, correo FROM usuarios",
				new RowMapper<UsuarioDTO>() {

					int i = 0;

					@Override
					public UsuarioDTO mapRow(ResultSet resultado, int indice) throws SQLException {

						i++;

						System.out.println("Se hallaron " + i + " filas de usuarios");

						UsuarioDTO usuario = new UsuarioDTO(

								resultado.getLong("cedula"), resultado.getString("nombre"),
								resultado.getString("usuario"), resultado.getString("correo")

				);

						return usuario;

					}

				});

		return lista;

	}

	public UsuarioDTO ObtenerUsuarioSesion(String usuario) {
		// TODO Auto-generated method stub
		List<UsuarioDTO> usuarioObtenido = jdbcTemp.query(
				"SELECT cedula, nombre, usuario, correo FROM usuarios WHERE usuario = '" + usuario + "'",
				new RowMapper<UsuarioDTO>() {

					@Override
					public UsuarioDTO mapRow(ResultSet resultado, int indice) throws SQLException {

						UsuarioDTO usuario = new UsuarioDTO(

								resultado.getLong("cedula"), resultado.getString("nombre"),
								resultado.getString("usuario"), resultado.getString("correo")

				);

						return usuario;

					}

				});

		UsuarioDTO Usuario = new UsuarioDTO(

				usuarioObtenido.get(0).getCedula(), usuarioObtenido.get(0).getNombre(),
				usuarioObtenido.get(0).getUsuario(), usuarioObtenido.get(0).getCorreo()

		);

		return Usuario;
	}

}
