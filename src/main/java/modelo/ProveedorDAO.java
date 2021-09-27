package modelo;

import java.sql.*;
import java.util.ArrayList;

import controlador.ConexionBD;

public class ProveedorDAO {
	private ConexionBD conexionBD;

	public ProveedorDAO(ConexionBD conexionBD) {
		this.conexionBD = conexionBD;
	}

	public boolean agregarProveedor(String NIT, String NombreProveedor, String Direccion, String Telefono,
			String Ciudad) {
		PreparedStatement ps;
		String sql = "INSERT INTO Proveedor VALUES(?,?,?,?,?)";
		try {
			conexionBD.establecerConexionBD();
			ps = conexionBD.getCnn().prepareStatement(sql);
			ps.setString(1, NIT);
			ps.setString(2, NombreProveedor);
			ps.setString(3, Direccion);
			ps.setString(4, Telefono);
			ps.setString(5, Ciudad);
			int result = ps.executeUpdate();
			if (result > 0) {
				conexionBD.cerrarConexionBD();
				return true;
			} else {
				conexionBD.cerrarConexionBD();
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	public Proveedor buscarProveedor(Proveedor prov) {
		PreparedStatement ps;
		ResultSet rs;
		String sql = "SELECT NIT, Nombre_Proveedor, Direccion, Telefono, Ciudad FROM Proveedor WHERE NIT = ?";
		Proveedor proveedorEncontrado = null;
		try {
			conexionBD.establecerConexionBD();
			ps = conexionBD.getCnn().prepareStatement(sql);
			ps.setString(1, prov.getNIT());
			rs = ps.executeQuery();
			if (rs.next()) {
				String NIT = rs.getString(1);
				String NombreProveedor = rs.getString(2);
				String Direccion = rs.getString(3);
				String Telefono = rs.getString(4);
				String Ciudad = rs.getString(5);
				proveedorEncontrado = new Proveedor(NIT, NombreProveedor, Direccion, Telefono, Ciudad);
			}
			conexionBD.cerrarConexionBD();
		} catch (Exception e) {
			proveedorEncontrado = null;
		}

		return proveedorEncontrado;
	}

	public boolean modificarProveedor(Proveedor prov) {

		PreparedStatement ps;
		String sql = "UPDATE Proveedores SET Nombre_Proveedor = ? , Direccion = ?, Telefono = ?, Ciudad = ? WHERE NIT = ?";
		try {
			conexionBD.establecerConexionBD();
			ps = conexionBD.getCnn().prepareStatement(sql);
			ps.setString(5, prov.getNIT());
			ps.setString(1, prov.getNombreProveedor());
			ps.setString(2, prov.getDireccion());
			ps.setString(3, prov.getTelefono());
			ps.setString(4, prov.getCiudad());
			int result = ps.executeUpdate();
			if (result > 0) {
				conexionBD.cerrarConexionBD();
				return true;
			} else {
				conexionBD.cerrarConexionBD();
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}

	public boolean eliminarProveedor(Proveedor prov) {
		PreparedStatement ps;
		String sql = "DELETE FROM Proveedores WHERE NIT = ?";
		try {
			conexionBD.establecerConexionBD();
			ps = conexionBD.getCnn().prepareStatement(sql);
			ps.setString(1, prov.getNIT());
			int result = ps.executeUpdate();
			if (result > 0) {
				conexionBD.cerrarConexionBD();
				return true;
			} else {
				conexionBD.cerrarConexionBD();
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}

	public String mostrarDirectorioProveedores() {
		ArrayList<Proveedor> lstProveedor = new ArrayList<Proveedor>();
		PreparedStatement ps;
		ResultSet rs;
		String sql = "SELECT * FROM BD_UsuariosTienda.Proveedor";
		String listado = "";

		try {
			conexionBD.establecerConexionBD();
			ps = conexionBD.getCnn().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Proveedor prov = new Proveedor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				lstProveedor.add(prov);
			}
			for (Proveedor Proveedor : lstProveedor) {
				listado = listado.concat(Proveedor.toString() + "\n\n");
			}
			conexionBD.cerrarConexionBD();
		} catch (Exception e) {
			return "";
		}
		return listado;
	}

}
