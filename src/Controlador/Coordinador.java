package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BBDD.Connect;
import Model.Administrador;
import Model.Empleado;
import Model.Reserva;
import Model.Sesion;
import Vistas.gestionReserva;
import Vistas.menuAdministrador;
import Vistas.menuEmpleado;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFileChooser;
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

public class Coordinador implements ActionListener {

	Connect myConnect = new Connect();
	Administrador myAdministrador = new Administrador();
	Empleado myEmpleado = new Empleado();
	Reserva myReserva = new Reserva();
	Sesion mySesion = new Sesion();

	public Coordinador(Connect paramConnect, Administrador paramAdmin, Empleado paramEmpleado, Reserva paramReserva,
			Sesion paramSesion) {
		this.myConnect = paramConnect;
		this.myAdministrador = paramAdmin;
		this.myEmpleado = paramEmpleado;
		this.myReserva = paramReserva;
		this.mySesion = paramSesion;
	}

	public Coordinador() {

	}

	public static void mostrarDatosUsuarioAdministrador(DefaultTableModel tablemodel) {

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

	public static void mostrarDatosUsuarioEmpleado(DefaultTableModel tablemodel) {

		Connect perDb = new Connect();

		try {
			ArrayList<Empleado> empleList = perDb.cargarEmpleado();

			tablemodel.setRowCount(0);

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

	public static void InicioSesion(JButton btnIniciarSesion, JTextField textFieldDNI,
			JPasswordField passwordFieldContraseña) throws SQLException {

		Connect con = new Connect();
		boolean enlista = false;

		for (int i = 0; i < con.cargarAdmins().size(); i++) {

			if (con.cargarAdmins().get(i).getDNI().equalsIgnoreCase(textFieldDNI.getText())
					&& con.cargarAdmins().get(i).getContrasena()
							.equals(String.valueOf(passwordFieldContraseña.getPassword()))
					&& con.cargarAdmins().get(i).getRol().equalsIgnoreCase("Administrador")) {

				JOptionPane.showMessageDialog(null, "¡Inicio de sesión correcto como Administrador!");
				menuAdministrador ventana = new menuAdministrador();
				ventana.setVisible(true);
				enlista = true;

			} else
				for (int b = 0; b < con.cargarEmpleado().size(); b++) {

					if (con.cargarEmpleado().get(b).getDNI().equalsIgnoreCase(textFieldDNI.getText())
							&& con.cargarEmpleado().get(b).getContrasena()
									.equals(String.valueOf(passwordFieldContraseña.getPassword()))
							&& con.cargarEmpleado().get(b).getRol().equalsIgnoreCase("Usuario")) {

						JOptionPane.showMessageDialog(null, "¡Inicio de sesión correcto como Empleado!");
						menuEmpleado ventana2 = new menuEmpleado();
						ventana2.setVisible(true);
						enlista = true;

					}

				}
		}
		if (enlista == false) {
			JOptionPane.showMessageDialog(null, "Las credenciales introducidas no son correctas.");

		}
	}

	public static void mostrarDatosReserva(DefaultTableModel tablemodel) {

		BBDD.Connect perDb = new BBDD.Connect();

		try {
			ArrayList<Model.Reserva> reservas = perDb.cargarReserva();

			tablemodel.setRowCount(0);
			for (Reserva reserva : reservas) {
				tablemodel.addRow(new Object[] { reserva.getDniPersona(), reserva.getSesionCodigo() });
			}

			System.out.println("Se han visualizado los datos correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public static String exploradorArchivos() {
		String filepath = "";
		JFileChooser selector = new JFileChooser();
		selector.setCurrentDirectory(new File("."));
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

		Element root = document.createElement("sesion");
		document.appendChild(root);

		while (rs.next()) {
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

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
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
			System.out.println("ERROR");
// Algún tipo de error: fichero no accesible, formato de XML incorrecto, etc.

		}
	}
}
