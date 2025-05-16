package Controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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
import view.gestionReserva;

public class Coordinador {

	ConnectBDD.Connect myConnect = new ConnectBDD.Connect();

	public Coordinador(Administrador paramAdmin, ConnectBDD.Connect paramConnect) {
		this.myConnect = paramConnect;

	}

	public Coordinador() {

	}

	public static void mostrarDatosReserva(DefaultTableModel tablemodel) {

		ConnectBDD.Connect perDb = new ConnectBDD.Connect();

		try {
			ArrayList<model.Reserva> reservas = perDb.cargarReserva();

			tablemodel.setRowCount(0);
			for (Reserva reserva : reservas) {
				tablemodel.addRow(new Object[] { reserva.getDniPersona(), reserva.getSesionCodigo() });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void mostrarDatosUsuario(DefaultTableModel tablemodel) {

		Connect perDb = new Connect();

		try {
			ArrayList<Administrador> adminList = perDb.cargarAdmins();
			ArrayList<Empleado> empleList = perDb.cargarEmpleado();

			tablemodel.setRowCount(0);
			for (Administrador admin : adminList) {
				tablemodel.addRow(new Object[] { admin.getDNI(), admin.getNombre(), admin.getApellido(), admin.getRol(),
						admin.getMail(), admin.getTelefono(), admin.getContrasena() });
			}

			for (Empleado emple : empleList) {
				tablemodel.addRow(new Object[] { emple.getDNI(), emple.getNombre(), emple.getApellido(), emple.getRol(),
						emple.getMail(), emple.getTelefono(), emple.getContrasena() });
			}
			System.out.println("Se han visualizado los datos correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String exploradorArchivos() {
		String filepath = "";
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML File", "xml");
		JFileChooser selector = new JFileChooser();
		
		selector.setFileFilter(filter);
		selector.setSelectedFile(new File("*.xml"));
		selector.setCurrentDirectory(new File("./src/archivosXML-TXT/XML"));
		
		int result = selector.showOpenDialog(selector);
		if (result == JFileChooser.APPROVE_OPTION) {

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

			String dni = (model.getValueAt(row, 0).toString());
			int sesion = Integer.valueOf(model.getValueAt(row, 1).toString());

			String consulta = "DELETE FROM reserva WHERE DNI_Persona = '" + dni + "' AND Sesion_Codigo = '" + sesion
					+ "'";

			st.executeUpdate(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int[] rows = gestionReserva.table.getSelectedRows();
		for (int i = 0; i < rows.length; i++) {
			model.removeRow(rows[i] - i);
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
			for (int i = 0; i < model.getRowCount(); i++) {

				String dni = model.getValueAt(i, 0).toString();
				int sesion = Integer.valueOf(model.getValueAt(i, 1).toString());
				;

				String consulta = "UPDATE reserva SET DNI_Persona='" + dni + "',Sesion_Codigo='" + sesion
						+ "' WHERE DNI_Persona = '" + dni + "'";

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

		Document document = builder.newDocument();

		Element root = document.createElement("sesiones");
		document.appendChild(root);

		while (rs.next()) {
			Element sesion = document.createElement("sesion");

			Element codigo = document.createElement("codigo");
			codigo.appendChild(document.createTextNode(rs.getString("Codigo")));
			Element fecha = document.createElement("fecha");
			fecha.appendChild(document.createTextNode(rs.getString("Fecha")));
			Element aforo = document.createElement("aforo");
			aforo.appendChild(document.createTextNode(rs.getString("Aforo")));
			Element piscina = document.createElement("piscina");
			piscina.appendChild(document.createTextNode(rs.getString("Actividad_Codigo")));
			
			root.appendChild(sesion);
			sesion.appendChild(codigo);
			sesion.appendChild(fecha);
			sesion.appendChild(aforo);
			sesion.appendChild(piscina);
		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(document);

		StreamResult result = new StreamResult(exploradorArchivos());
		transformer.transform(source, result);

		System.out.println("Se ha creado el fichero XML.");
	}

//Importar XML a la Base de Datos (Reservas)
	public static void importarXML() throws SQLException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document documento = null;
		try {
			Connect conexion = new Connect();
			Connection con = conexion.conexion();
			DocumentBuilder builder = factory.newDocumentBuilder();
			documento = builder.parse(new File(exploradorArchivos()));
			NodeList nodeList = documento.getElementsByTagName("reserva");
			for (int i = 0; i < nodeList.getLength(); i = i + 2) {
				Node node = nodeList.item(i);
				String reserva = node.getTextContent();
				String[] datos = reserva.split("\n");

				String dni = datos[1].replaceAll("\\s", "");
				int sesion = Integer.valueOf(datos[2].replaceAll("\\s", ""));

				Statement st = con.createStatement();
				String consulta = "INSERT INTO reserva (DNI_Persona, Sesion_Codigo) VALUES ('" + dni + "', " + sesion
						+ ");";
				st.executeUpdate(consulta);
				JOptionPane.showMessageDialog(gestionReserva.table,
						"Se ha importado el XML y se ha generado un archivo .txt.");
				try {
					FileWriter writer = new FileWriter(
							"C:\\Users\\1AW3-24\\git\\Logro-o_deportes_grupo2\\src\\archivosXML-TXT\\TXT\\consulta.txt");
					writer.write(consulta);
					writer.close();
				} catch (IOException e) {
					System.out.println("Error al generar el fichero .txt");
					e.printStackTrace();
				}
			}
		}

		catch (Exception spe) {
			JOptionPane.showMessageDialog(gestionReserva.table, "Ha ocurrido un error al importar o se ha cancelado la operación.");

		}
	}

}