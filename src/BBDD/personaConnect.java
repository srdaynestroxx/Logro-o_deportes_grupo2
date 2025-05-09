package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.DatosPersona;
import Model.Persona;

public class personaConnect {

	public static Connection conexion() {

		String url = "jdbc:mysql://localhost:3306/logronodeportes";
		String user = "root";
		String password = "";

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Base de datos conectada!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;

	}

	public static ArrayList<DatosPersona> visualizarUsuarios() throws SQLException {
		Connection con = conexion();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM persona;";
		ResultSet resultSet = (ResultSet) st.executeQuery(consulta);
		ArrayList<DatosPersona> personaList = new ArrayList<DatosPersona>();

		try {
			while (resultSet.next()) {
				DatosPersona pers = new DatosPersona();
				pers.setDNI(resultSet.getString("DNI"));
				pers.setNombre(resultSet.getString("nombre"));
				pers.setApellido(resultSet.getString("apellido"));
				pers.setRol(resultSet.getString("rol"));
				pers.setMail(resultSet.getString("mail"));
				pers.setTelefono(resultSet.getInt("telefono"));
				pers.setContrasena(resultSet.getString("contrasena"));

				personaList.add(pers);
				// System.out.println(futbolistaList.toString());
			}
		} catch (Exception e) {
			System.err.println("Error en visualizar Usuarios");
		}

		return personaList;
	}

}