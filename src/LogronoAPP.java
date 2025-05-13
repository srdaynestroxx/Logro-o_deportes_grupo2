
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import BBDD.Connect;
import Controlador.Coordinador;
import Model.Administrador;
import Model.Empleado;
import Model.Reserva;
import Model.Sesion;

public class LogronoAPP {

	public static void main(String[] args) throws SQLException {

		Administrador adm = new Administrador();
		Empleado em = new Empleado();
		Reserva res = new Reserva();
		Sesion ses = new Sesion();
		Connect conn = new Connect();

		Coordinador coord = new Coordinador(conn, adm, em, res, ses);

		// Creamos dos personas para agregar a un archivo con extensión ".dat"
		// es decir, escribiremos los datos en un archivo binario.
	}
}