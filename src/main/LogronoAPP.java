package main;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.DriverManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ConnectBDD.Connect;
import model.Administrador;
import model.Empleado;
import model.Reserva;
import model.Sesion;

public class LogronoAPP {
	
	public static void main(String[] args) throws SQLException
	{
		
		importarXML();
		/*ArrayList<Empleado> empleados = new ArrayList<>();

		Connect con = new Connect();
		Administrador admin = new Administrador();
		Empleado empleado = new Empleado();
		con.cargarEmpleado(empleados);
		
		for(int i = 0;i<con.cargarEmpleado().size();i++) {					
			System.out.println(con.cargarEmpleado().get(i).getDNI());		
		};
		for(int i = 0;i<con.cargarEmpleado().size();i++) {					
				System.out.println(con.cargarEmpleado().get(i).getDNI());		
			};

*/
	}
	
	public static void importarXML()  throws SQLException{
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
Document documento = null;
try
{
	Connect conexion = new Connect();
    Connection con = conexion.conexion();
	DocumentBuilder builder = factory.newDocumentBuilder(); 
	documento = builder.parse( new File("C:/Users/1AW3-24/git/Logro-o_deportes_grupo2/src/main/pruebaReto.xml") );
	NodeList nodeList = documento.getElementsByTagName("reserva");
    for (int i = 0; i < nodeList.getLength(); i = i+2) {
        Node node = nodeList.item(i);
        String reserva = node.getTextContent();
        String[] datos = reserva.split("\n");
        
        String dni = datos[1].replaceAll("\\s", "");
        int sesion = Integer.valueOf(datos[2].replaceAll("\\s", ""));
        
    	Statement st = con.createStatement();
    	String consulta = "INSERT INTO reserva (DNI_Persona, Sesion_Codigo) VALUES ('"+ dni +"', "+ sesion +");";
    	st.executeUpdate(consulta);
    }}

catch (Exception spe)
{
	System.out.println("ERROR");
	// Algún tipo de error: fichero no accesible, formato de XML incorrecto, etc.

}
	}
}
