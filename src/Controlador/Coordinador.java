package Controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import BBDD.Connect;
import Model.Administrador;
import Model.Empleado;

public class Coordinador {
	
	Connect myConnect = new Connect();
	
	public Coordinador(Administrador paramAdmin,Connect paramConnect){
		this.myConnect = paramConnect;
		
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
	


}
