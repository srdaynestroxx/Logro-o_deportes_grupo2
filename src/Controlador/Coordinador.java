package Controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import BBDD.personaConnect;
import BBDD.reservaConnect;
import Model.DatosPersona;
import Model.Persona;

public class Coordinador {
	
	personaConnect myPersonaConnect = new personaConnect();
	reservaConnect myReservaConnect = new reservaConnect();
	Persona myPersona = new DatosPersona(); 
	
	public Coordinador(Persona paramPersona,personaConnect paramPersonaConnect,reservaConnect paramReservaConnect){
		this.myPersonaConnect = paramPersonaConnect;
		this.myReservaConnect = paramReservaConnect;
		this.myPersona = paramPersona;
		
	}
	
	public Coordinador() {
		
	}
	
	
	public static void mostrarDatos(DefaultTableModel tablemodel) {
		
		personaConnect perDb = new personaConnect();
		
		try {
			ArrayList<DatosPersona> personaList = perDb.visualizarUsuarios();
			
			tablemodel.setRowCount(0);
			for(Persona per: personaList) {
				tablemodel.addRow(new Object[] {per.getDNI(), per.getNombre(),per.getApellido(),per.getRol(),per.getMail(),per.getTelefono(),per.getContrasena()});
			}
			System.out.println("Se han visualizado los datos correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
