package ConnectBDD;
/**
 * @author Grupo2 - Logroño Deportes
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Administrador;
import model.Empleado;
import model.Reserva;
import model.Sesion;

/**
 * Clase encargada de la conexión con la base de datos.
 */
public class Connect {

	/**
	 * Constructor vacio para Connect
	 */
	public Connect() {}
	/**
	 * Método encargado de realizar la conexión con la base de datos.
	 * @return El conector de la base de datos.
	 */
	public Connection conexion() {

		String url = "jdbc:mysql://localhost:3306/LOGRONO";

		String username = "root";

		String password = "";

		Connection con = null;

		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error conectando a la base de datos");
			e.printStackTrace();
		}

		return con;
	}

	/**
	 * Método encargado de cargar los administradores desde la base de datos.
	 * @return Devuelve un ArrayList que contiene los administradores.
	 * @throws SQLException Devuelve el posible error de SQL.
	 */
//CARGAR ADMINISTRADORES
	public ArrayList<Administrador> cargarAdmins() throws SQLException {

		Connection con = conexion();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM Persona WHERE Rol = 'Administrador'";
		ResultSet rs = st.executeQuery(consulta);

		ArrayList<Administrador> administradores = new ArrayList<>();

		try {
			while (rs.next()) {

				Administrador admin = new Administrador();
				admin.setDNI(rs.getString("DNI"));
				admin.setNombre(rs.getString("Nombre"));
				admin.setApellido(rs.getString("Apellido"));
				admin.setRol(rs.getString("Rol"));
				admin.setMail(rs.getString("Mail"));
				admin.setTelefono(rs.getInt("Telefono"));
				admin.setContrasena(rs.getString("Contrasena"));

				administradores.add(admin);
			}
		} catch (SQLException e) {
			System.out.println("ERROR AÑADIENDO ADMINISTRADORES");
			e.printStackTrace();
		}

		return administradores;
	}

	/**
	 * Método encargado de cargar los empleados desde la base de datos.
	 * @return Devuelve un ArrayList que contiene los empleados.
	 * @throws SQLException Devuelve el posible error de SQL.
	 */
//CARGAR EMPLEADOS
	public ArrayList<Empleado> cargarEmpleado() throws SQLException {

		Connection con = conexion();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM Persona WHERE Rol = 'Usuario'";
		ResultSet rs = st.executeQuery(consulta);

		ArrayList<Empleado> empleados = new ArrayList<>();

		try {
			while (rs.next()) {

				Empleado empleado = new Empleado();
				empleado.setDNI(rs.getString("DNI"));
				empleado.setNombre(rs.getString("Nombre"));
				empleado.setApellido(rs.getString("Apellido"));
				empleado.setRol(rs.getString("Rol"));
				empleado.setMail(rs.getString("Mail"));
				empleado.setTelefono(rs.getInt("Telefono"));
				empleado.setContrasena(rs.getString("Contrasena"));

				empleados.add(empleado);
			}
		} catch (SQLException e) {
			System.out.println("ERROR AÑADIENDO EMPLEADOS");
			e.printStackTrace();
		}

		return empleados;
	}

	/**
	 * Método encargado de cargar las reservas de la base de datos.
	 * @return Devuelve un ArrayList que contiene las reservas.
	 * @throws SQLException Devuelve el posible error de SQL.
	 */
//CARGAR RESERVAS
	public ArrayList<Reserva> cargarReserva() throws SQLException {

		Connection con = conexion();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM Reserva";
		ResultSet rs = st.executeQuery(consulta);

		ArrayList<Reserva> reservas = new ArrayList<>();

		try {
			while (rs.next()) {

				Reserva reserva = new Reserva();
				reserva.setDniPersona(rs.getString("DNI_Persona"));
				reserva.setSesionCodigo(rs.getInt("Sesion_Codigo"));

				reservas.add(reserva);
			}
		} catch (SQLException e) {
			System.out.println("ERROR AÑADIENDO RESERVAS");
			e.printStackTrace();
		}

		return reservas;
	}

	/**
	 * Método encargado de cargar las sesiones de la base de datos.
	 * @return Devuelve un ArrayList que contiene las sesiones.
	 * @throws SQLException Devuelve el posible error de SQL.
	 */
//CARGAR SESIONES
	public ArrayList<Sesion> cargarSesion() throws SQLException {

		Connection con = conexion();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM Sesion";
		ResultSet rs = st.executeQuery(consulta);

		ArrayList<Sesion> sesiones = new ArrayList<>();

		try {
			while (rs.next()) {

				Sesion sesion = new Sesion();
				sesion.setId(rs.getInt("Codigo"));
				sesion.setFecha(rs.getString("Fecha"));
				sesion.setAforo(rs.getInt("Aforo"));
				sesion.setActividad(rs.getInt("Actividad_Codigo"));

				sesiones.add(sesion);
			}
		} catch (SQLException e) {
			System.out.println("ERROR AÑADIENDO SESIONES");
			e.printStackTrace();
		}

		return sesiones;
	}
}
