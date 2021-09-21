package com.grupo2.tienda.implemetaciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.grupo2.tienda.modelos.ProveedorDTO;

public class ImplementacionProveedores {

	private JdbcTemplate jdbcTemp;

	public ImplementacionProveedores(DataSource fuenteDatos) {

		jdbcTemp = new JdbcTemplate(fuenteDatos);

	}

	public void AgregarProveedor(long nit, String nombre, String direccion, String telefono, String ciudad) {

		jdbcTemp.execute("INSERT INTO proveedores (nit, nombre, direccion, telefono, ciudad) VALUES ('" + nit + "', '"
				+ nombre + "', '" + direccion + "', '" + telefono + "', '" + ciudad + "')");

	}

	public void EditarProveedor(long nitAnterior, long nit, String nombre, String direccion, String telefono,
			String ciudad) {

		jdbcTemp.execute("UPDATE proveedores SET nit = '" + nit + "', nombre = '" + nombre + "', direccion = '"
				+ direccion + "', telefono = '" + telefono + "', ciudad = '" + ciudad + "' WHERE nit = '" + nitAnterior
				+ "'");

	}

	public void EliminarProveedor(long nit) {

		jdbcTemp.execute("DELETE FROM proveedores WHERE nit = '" + nit + "'");

	}

	public ProveedorDTO ObtenerProveedor(long nit) {

		List<ProveedorDTO> ResultadoBusqueda = jdbcTemp.query("SELECT * FROM proveedores WHERE nit = '" + nit + "'",
				new RowMapper<ProveedorDTO>() {

					@Override
					public ProveedorDTO mapRow(ResultSet resultado, int indice) throws SQLException {

						ProveedorDTO proveedor = new ProveedorDTO(

								resultado.getLong("nit"), resultado.getString("nombre"),
								resultado.getString("direccion"), resultado.getString("telefono"),
								resultado.getString("ciudad")

				);

						return proveedor;

					}

				});

		ProveedorDTO Proveedor = new ProveedorDTO(

				ResultadoBusqueda.get(0).getNit(), ResultadoBusqueda.get(0).getNombre(),
				ResultadoBusqueda.get(0).getDireccion(), ResultadoBusqueda.get(0).getTelefono(),
				ResultadoBusqueda.get(0).getCiudad()

		);

		return Proveedor;

	}

	public boolean ValidarProveedor(long nit) {

		List<ProveedorDTO> lista = jdbcTemp.query("SELECT * FROM proveedores WHERE nit = '" + nit + "'",
				new RowMapper<ProveedorDTO>() {

					@Override
					public ProveedorDTO mapRow(ResultSet resultado, int indice) throws SQLException {

						ProveedorDTO proveedor = new ProveedorDTO(

								resultado.getLong("nit"), resultado.getString("nombre"),
								resultado.getString("direccion"), resultado.getString("telefono"),
								resultado.getString("ciudad")

				);

						return proveedor;

					}

				});

		if (lista.size() != 0) {

			return true;

		} else {

			return false;

		}

	}

	public List<ProveedorDTO> ListarProveedores() {

		List<ProveedorDTO> lista = jdbcTemp.query("SELECT * FROM proveedores", new RowMapper<ProveedorDTO>() {

			@Override
			public ProveedorDTO mapRow(ResultSet resultado, int indice) throws SQLException {

				ProveedorDTO proveedor = new ProveedorDTO(

						resultado.getLong("nit"), resultado.getString("nombre"), resultado.getString("direccion"),
						resultado.getString("telefono"), resultado.getString("ciudad")

				);

				return proveedor;

			}

		});

		return lista;

	}

}
