package main;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.DriverManager;

import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
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
import view.gestionReserva;

public class LogronoAPP {
	
	public static void main(String[] args) throws SQLException, ParserConfigurationException, TransformerException
	{
		
		//importarXML();
		//exportarXML();
		//editarReserva();
		//eliminarReserva();
	}
	
	public static String exploradorArchivos ()
	{
	String filepath = "";
	JFileChooser selector = new JFileChooser (); selector.setCurrentDirectory (new File("."));
	int result = selector.showOpenDialog (selector);
	if (result == JFileChooser. APPROVE_OPTION)
	{

	File selectedFile = selector.getSelectedFile(); 

	filepath = selectedFile.getAbsolutePath();
	}
	return filepath;
	}

	//Eliminar Reservas
	public static void eliminarReserva() {
		Connect conexion = new Connect();
		Connection con = conexion.conexion();
		Statement st;

		int row = gestionReserva.table.getSelectedRow(); 
		DefaultTableModel model = (DefaultTableModel) gestionReserva.table.getModel();
		// getValueAt (row index, column index)
		
        try {
			st = con.createStatement();
		
		String dni = (model.getValueAt (row, 0).toString()); 
		int sesion = Integer.valueOf(model.getValueAt (row, 1).toString());
		
		String consulta = "DELETE FROM reserva WHERE DNI_Persona = '" + dni +"' AND Sesion_Codigo = '"+ sesion +"'";
     	
		st.executeUpdate(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		   int[] rows = gestionReserva.table.getSelectedRows();
		   for(int i=0;i<rows.length;i++){
		     model.removeRow(rows[i]-i);		
		   }
	}

	//Editar Reservas
	public static void editarReserva() {
			Connect conexion = new Connect();
			Connection con = conexion.conexion();
			Statement st;
	        DefaultTableModel model = (DefaultTableModel) gestionReserva.table.getModel();
	        
	        try {
	            st = con.createStatement();
	            for(int i = 0; i < model.getRowCount(); i++){
	                
	                String dni = model.getValueAt(i, 0).toString();
	                int sesion = Integer.valueOf(model.getValueAt(i,1).toString());;
	                
	                String consulta = "UPDATE reserva SET DNI_Persona='"+dni+"',Sesion_Codigo='"+sesion+"' WHERE DNI_Persona = '" + dni +"'";
	              
	            	st.executeUpdate(consulta);
	            }
	            
	            int[] updatedRow = st.executeBatch();
	            System.out.println("Se han actualizado los datos correctamente.");
	            
	        } catch (SQLException ex) {
	        	System.out.println("Error al actualizar los datos.");
	        }		
	}

	//Exportar Sesiones XML
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
	        StreamResult result = new StreamResult(exploradorArchivos());
	        transformer.transform(source, result);

	        System.out.println("Se ha creado el fichero XML.");
	}

	//Importar XML a la Base de Datos (Reseras)
	public static void importarXML()  throws SQLException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document documento = null;
	try
		{
	Connect conexion = new Connect();
    Connection con = conexion.conexion();
	DocumentBuilder builder = factory.newDocumentBuilder(); 
	documento = builder.parse( new File(exploradorArchivos()) );
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
