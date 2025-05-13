package Controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import BBDD.Connect;
import Model.Administrador;
import Model.Empleado;
import Model.Reserva;
import Model.Sesion;

public class Coordinador {
	
	Connect myConnect = new Connect();
	Administrador myAdministrador = new Administrador();
	Empleado myEmpleado = new Empleado();
	Reserva myReserva = new Reserva();
	Sesion mySesion = new Sesion();
	
	public Coordinador(Connect paramConnect,Administrador paramAdmin,Empleado paramEmpleado,Reserva paramReserva,Sesion paramSesion){
		this.myConnect = paramConnect;
		this.myAdministrador = paramAdmin;
		this.myEmpleado = paramEmpleado;
		this.myReserva = paramReserva;
		this.mySesion = paramSesion;
	}
	
	public Coordinador() {
		
	}
	
	
	public static void mostrarDatos(DefaultTableModel tablemodel) {
		
		Connect perDb = new Connect();
		
		try {
			ArrayList<Administrador> adminList = perDb.cargarAdmins();
			ArrayList<Empleado> empleList = perDb.cargarEmpleado();
			
			tablemodel.setRowCount(0);
			for(Administrador admin: adminList) {
				tablemodel.addRow(new Object[] {admin.getDNI(), admin.getNombre(),admin.getApellido(),admin.getRol(),admin.getMail(),admin.getTelefono(),admin.getContrasena()});
			}
			
			for(Empleado emple: empleList) {
				tablemodel.addRow(new Object[] {emple.getDNI(), emple.getNombre(),emple.getApellido(),emple.getRol(),emple.getMail(),emple.getTelefono(),emple.getContrasena()});
			}
			System.out.println("Se han visualizado los datos correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void realizarFicheroBinario(JButton btnCopiaSeguridad) throws SQLException {
		
		Connect admin = new Connect();
		Connect emp = new Connect();
		ArrayList<Empleado> empleado = emp.cargarEmpleado();
		ArrayList<Administrador> administrador = admin.cargarAdmins();

		// Creamos un objeto de tipo fila para asignarle un archivo
		File archivo = new File("escritura.dat");

		try {
			// Para poder escribir utilizaremos un FileOutputStream pasandole
			// como referencia el archivo de tipo File.
			FileOutputStream fos = new FileOutputStream(archivo);

			// Y crearemos también una instancia del tipo ObjectOutputStream
			// al que le pasaremos por parámetro
			// el objeto de tipo FileOutputStream
			ObjectOutputStream escribir = new ObjectOutputStream(fos);

			// Escribimos los objetos en el archivo.
			for (int i = 0; i < empleado.size(); i++) {
				escribir.writeObject(empleado.get(i));
			}
			for (int i = 0; i < administrador.size(); i++) {
				escribir.writeObject(administrador.get(i));
			}

			// Cerramos los objetos para no consumir recursos.
			escribir.close();
			fos.close();
	
			
		} catch (Exception e) {
			System.out.println("Error al escribir en el archivo. " + e.getMessage());
		}
	}
	}


