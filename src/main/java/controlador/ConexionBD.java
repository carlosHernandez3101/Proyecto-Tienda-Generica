package controlador;

import java.sql.*;

import javax.swing.JOptionPane;

public class ConexionBD {
	
	private Connection cnn;

	public ConexionBD() {

	}

	public void establecerConexionBD() {
		String url = "jdbc:mysql://localhost/bd_usuariostienda";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.cnn = DriverManager.getConnection(url, "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cerrarConexionBD() {
		try {
			cnn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getCnn() {
		return cnn;
	}

	public void setCnn(Connection cnn) {
		this.cnn = cnn;
	}

}
