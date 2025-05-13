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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ConnectBDD.Connect;
import model.Administrador;
import model.Empleado;
import model.Reserva;
import model.Sesion;

public class LogronoAPP {
	
	public static void main(String[] args) throws SQLException, ParserConfigurationException, TransformerException
	{
		
		//importarXML();
		//exportarXML();
	}
	
	public static void exportarXML() throws ParserConfigurationException, TransformerException, SQLException {
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();

	        Connect conexion = new Connect();
	        Connection con = conexion.conexion();
	  
	        Statement st = con.createStatement();
	    	String consulta = "SELECT * FROM sesion";
	    	ResultSet rs = st.executeQuery(consulta);
	    	
	    	
	       // Create a new Document
	        Document document = builder.newDocument();
	        
	        // Create root element
	        Element root = document.createElement("sesion");
	        document.appendChild(root);
	        
			while(rs.next()) {
	        // Create book elements and add text content
	        Element codigo = document.createElement("Codigo");
	        codigo.appendChild(document.createTextNode(rs.getString("Codigo")));
	        Element fecha = document.createElement("Fecha");
	        fecha.appendChild(document.createTextNode(rs.getString("Fecha")));
	        Element aforo = document.createElement("Aforo");
	        aforo.appendChild(document.createTextNode(rs.getString("Aforo")));
	        Element actividad = document.createElement("Actividad_Codigo");
	        actividad.appendChild(document.createTextNode(rs.getString("Actividad_Codigo")));
	        root.appendChild(codigo);
	        root.appendChild(fecha);
	        root.appendChild(aforo);
	        root.appendChild(actividad);
			}
			
	        // Write to XML file
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(document);

	        // Specify your local file path
	        StreamResult result = new StreamResult("C:\\Users\\1AW3-24\\git\\Logro-o_deportes_grupo2\\src\\main\\XML\\sesiones.xml");
	        transformer.transform(source, result);

	        System.out.println("XML file created successfully!");
	}

	
	public static void importarXML()  throws SQLException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document documento = null;
	try
		{
	Connect conexion = new Connect();
    Connection con = conexion.conexion();
	DocumentBuilder builder = factory.newDocumentBuilder(); 
	documento = builder.parse( new File("C:/Users/1AW3-24/git/Logro-o_deportes_grupo2/src/main/XML/pruebaReto.xml") );
	NodeList nodeList = documento.getElementsByTagName("reserva");
    for (int i = 0; i < nodeList.getLength(); i = i++) {
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
