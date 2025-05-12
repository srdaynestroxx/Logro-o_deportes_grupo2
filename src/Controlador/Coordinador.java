package Controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ConnectBDD.Connect;
import model.Administrador;
import model.Empleado;
import model.Reserva;

public class Coordinador {
	
	ConnectBDD.Connect myConnect = new ConnectBDD.Connect();
	
	public Coordinador(Administrador paramAdmin,ConnectBDD.Connect paramConnect){
		this.myConnect = paramConnect;
		
	}
	
	public Coordinador() {
		
	}
	
	
	public static void mostrarDatos(DefaultTableModel tablemodel) {
		
		ConnectBDD.Connect perDb = new ConnectBDD.Connect();
		
		try {
			ArrayList<model.Reserva> reservas = perDb.cargarReserva();
			
			tablemodel.setRowCount(0);
			for(Reserva reserva: reservas) {
				tablemodel.addRow(new Object[] {reserva.getDniPersona(), reserva.getSesionCodigo()});
			}
			
			System.out.println("Se han visualizado los datos correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	


}