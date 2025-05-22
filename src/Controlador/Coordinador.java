package Controlador;
/**
 * @author Grupo2 - Logroño Deportes
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
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
import logs.Logs;
import main.LogronoAPP;
import model.Administrador;
import model.Empleado;
import model.Reserva;
import model.Sesion;
import view.ConsultarUsuariosAdministrador;
import view.GestionReserva;
import view.Menu;
import view.MenuEmpleado;

/**
 * Clase encargada de actuar como controlador entre los Models y los Views.
 */
public class Coordinador implements ActionListener {

	ConnectBDD.Connect connect = new ConnectBDD.Connect();

	/**
	 * Constructor para la conexión con la base de datos.
	 * @param paramAdmin Parametro para los administradores.
	 * @param paramConnect Parametro para la conexión con la base de datos.
	 */
	public Coordinador(Administrador paramAdmin, ConnectBDD.Connect paramConnect) {
		this.myConnect = paramConnect;

	}

	Connect myConnect = new Connect();
	Administrador myAdministrador = new Administrador();
	Empleado myEmpleado = new Empleado();
	Reserva myReserva = new Reserva();
	Sesion mySesion = new Sesion();

	/**
	 * Constructor con parametros para Coordinador.
	 * @param paramConnect Parametro para la conexión con la base de datos.
	 * @param paramAdmin Parametro para los administradores.
	 * @param paramEmpleado Parametro para los empleados.
	 * @param paramReserva Parametro para las reservas.
	 * @param paramSesion Parametro para las sesiones.
	 */
	public Coordinador(Connect paramConnect, Administrador paramAdmin, Empleado paramEmpleado, Reserva paramReserva,
			Sesion paramSesion) {
		this.myConnect = paramConnect;
		this.myAdministrador = paramAdmin;
		this.myEmpleado = paramEmpleado;
		this.myReserva = paramReserva;
		this.mySesion = paramSesion;
	}

	/**
	 * Constructor sin parametros para Coordinador.
	 */
	public Coordinador() {

	}

	/**
	 * Metodo para cargar los datos de los administradores en la tabla cuando el usuario acceda al view ConsultarUsuarios.
	 * @param tablemodel Parametro de la tabla de ConsultarUsuariosAdministrador.
	 */
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
			 try (main.LogronoAPP.logger) {
					main.LogronoAPP.logger.logError("Error con la base de datos.", e);
				}
		}

	}

	/**
	 * Metodo para cargar los datos de los empleados en la tabla cuando el usuario acceda a el view 'ConsultarUsuariosAdministrador'.
	 * @param tablemodel Parametro de la tabla de ConsultarUsuariosAdministrador.
	 */
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
			 try (main.LogronoAPP.logger) {
					main.LogronoAPP.logger.logError("Error con la base de datos.", e);
				}
		}

	}

	/**
	 * Método para Realizar una copia de seguridad de los usuarios (en un fichero binario) cuando el usuario presione el botón indicado.
	 * @param btnCopiaSeguridad Parametro utlizado para el boton utilizado para realizar la copia de seguridad.
	 * @throws SQLException Devuelve el posible error de SQL.
	 */
	public static void realizarFicheroBinario(JButton btnCopiaSeguridad) throws SQLException {

		Connect admin = new Connect();
		Connect emp = new Connect();
		ArrayList<Empleado> empleado = emp.cargarEmpleado();
		ArrayList<Administrador> administrador = admin.cargarAdmins();

		File archivo = new File(exploradorArchivosBinario());

		try {
			FileOutputStream fos = new FileOutputStream(archivo);
			ObjectOutputStream escribir = new ObjectOutputStream(fos);

			for (int i = 0; i < empleado.size(); i++) {
				escribir.writeObject(empleado);
			}

			escribir.close();
			fos.close();

		} catch (Exception e) {
			System.out.println("Error al escribir en el archivo. " + e.getMessage());
			 try (main.LogronoAPP.logger) {
					main.LogronoAPP.logger.logError("Error realizando la operación.", e);
				}
		}
	}

	/**
	 * Método para cargar una copia de seguridad de los usuarios creada con anterioridad cuando el usuario presione el botón indicado.
	 * @param tableModel Parametro utlizado para indicar la tabla de ConsultarUsuarios.
	 * @throws IOException Devuelve la posible excepcion IO.
	 * @throws ClassNotFoundException Devuelve el error en caso de no encontrar la clase.
	 */
	public static void cargarFicheroBinario(DefaultTableModel tableModel) throws IOException, ClassNotFoundException {


	    File archivo = new File(exploradorArchivosBinario());
        try {
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream leer;
            
            while (fis.available() > 0) {
                leer = new ObjectInputStream(fis);
                
                ArrayList<Empleado> Empleado = (ArrayList<Empleado>) leer.readObject();
                
                System.out.println(Empleado);
                
                ConsultarUsuariosAdministrador consultar = new ConsultarUsuariosAdministrador();
                
                	tableModel.setRowCount(0);
                    for (int i = 0; i < Empleado.size();i++) {
                        tableModel.addRow(new Object[] { Empleado.get(i).getDNI(), Empleado.get(i).getNombre(),
                                Empleado.get(i).getApellido(), Empleado.get(i).getRol(), Empleado.get(i).getMail(), Empleado.get(i).getTelefono(),
                                Empleado.get(i).getContrasena() });
                    }

                	}
            
        } catch (Exception e) {
            System.out.println("Error al leer el archivo. " + e.getMessage());
            
			 try (main.LogronoAPP.logger) {
					main.LogronoAPP.logger.logError("Error realizando la operación.", e);
				}

        }
		    

	}

	/**
	 * Método utilizado en el view 'IniciarSesion' para comparar las credenciales introducidas por el usuario con las de la base de datos.
	 * @param btnIniciarSesion Parametro encargado de el boton para realizar el inicio de sesion.
	 * @param textFieldDNI Parametro encargado del text field en el que el usuario ingresará el DNI.
	 * @param passwordFieldContraseña Parametro encargado del password field en el que el usuario ingresará la contraseña
	 * @throws SQLException Devuelve el posible error de SQL.
	 */
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
				Menu ventana = new Menu();
				ventana.setVisible(true);
				enlista = true;
				
				try (main.LogronoAPP.logger) {

					main.LogronoAPP.logger.logSession("Inicio de sesión como administrador.");

				}

			} else
				for (int b = 0; b < con.cargarEmpleado().size(); b++) {

					if (con.cargarEmpleado().get(b).getDNI().equalsIgnoreCase(textFieldDNI.getText())
							&& con.cargarEmpleado().get(b).getContrasena()
									.equals(String.valueOf(passwordFieldContraseña.getPassword()))
							&& con.cargarEmpleado().get(b).getRol().equalsIgnoreCase("Usuario")) {

						JOptionPane.showMessageDialog(null, "¡Inicio de sesión correcto como Empleado!");
						MenuEmpleado ventana2 = new MenuEmpleado();
						ventana2.setVisible(true);
						enlista = true;

						try (main.LogronoAPP.logger) {

							main.LogronoAPP.logger.logSession("Inicio de sesión como empleado.");

						}
					}

				}
		}
		if (enlista == false) {
			JOptionPane.showMessageDialog(null, "Las credenciales introducidas no son correctas.");
			
			try (main.LogronoAPP.logger) {

				main.LogronoAPP.logger.logSession("Credenciales erroneas.");

			}
		}
	}

	/**
	 * Método utilizado en el view 'GestionarReservas' y 'ConsultarReservas' para cargar los datos desde la base de datos de estas.
	 * @param tablemodel Parametro de la tabla de las Reservas.
	 */
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
			 try (main.LogronoAPP.logger) {
					main.LogronoAPP.logger.logError("Error con la base de datos.", e);
				}
		}

	}

	/**
	 * Metodo para cargar los datos de los empleados en la tabla cuando el usuario acceda a el view 'ConsultarUsuariosEmpleado'.
	 * @param tablemodel Parametro de la tabla 'ConsultarUsuariosEmpleado'.
	 */
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
			 try (main.LogronoAPP.logger) {
					main.LogronoAPP.logger.logError("Error con la base de datos.", e);
				}
		}

	}

	/**
	 * Método utilizado para abrir el explorador de archivos de ficheros .xml (usado en GestionarReservas).
	 * @return La ruta de archivo elegida por el usuario.
	 */
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

	/**
	 * Método utilizado para abrir el explorador de archivos de ficheros .dat binarios (usado en ConsultarUsuarios).
	 * @return La ruta de archivo elegida por el usuario.
	 */
	public static String exploradorArchivosBinario() {
		String filepath = "";
		FileNameExtensionFilter filter = new FileNameExtensionFilter("DAT File", "dat");
		JFileChooser selector = new JFileChooser();

		selector.setFileFilter(filter);
		selector.setSelectedFile(new File("*.dat"));
		selector.setCurrentDirectory(new File("./src/backupUsuarios"));

		int result = selector.showOpenDialog(selector);
		if (result == JFileChooser.APPROVE_OPTION) {

			File selectedFile = selector.getSelectedFile();

			filepath = selectedFile.getAbsolutePath();
		}
		return filepath;
	}

	/**
	 * Método utilizado para eliminar reservas de la base de datos seleccionando el dato en la tabla de 'GestionarReservas' y presionando el botón indicado.
	 */
	public static void eliminarReserva() {
		Connect conexion = new Connect();
		Connection con = conexion.conexion();
		Statement st;

		int row = GestionReserva.table.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel) GestionReserva.table.getModel();
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
			 try (main.LogronoAPP.logger) {
					main.LogronoAPP.logger.logError("Error con la base de datos.", e);
				}
		}

		int[] rows = GestionReserva.table.getSelectedRows();
		for (int i = 0; i < rows.length; i++) {
			model.removeRow(rows[i] - i);
		}
	}

	/**
	 * Método utilizado para guardar los cambios realizados en la tabla de 'GestionarReservas' mediante el botón indicado.
	 */
//Editar Reservas
	public static void editarReserva() {
		Connect conexion = new Connect();
		Connection con = conexion.conexion();
		Statement st;
		DefaultTableModel model = (DefaultTableModel) GestionReserva.table.getModel();

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
			 try (main.LogronoAPP.logger) {
					main.LogronoAPP.logger.logError("Error con la base de datos.", ex);
				}
		}
	}

	/**
	 * Método utilizado para exportar los datos de la tabla 'sesiones' de la base de datos a un fichero XML mediante el botón indicado, preparandolo asi para importarlo a la página web.
	 * @throws ParserConfigurationException Devuelve error en caso de fallar al parsear.
	 * @throws TransformerException Devuelve error en caso de fallar en el transformer.
	 * @throws SQLException Devuelve el posible error de SQL.
	 */
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

	/**
	 * Método utilizado para importar reservas creadas mediante un fichero XML directamente a la base de datos mediante el botón indicado.
	 * @throws SQLException Devuelve el posible error de SQL.
	 */
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
				JOptionPane.showMessageDialog(GestionReserva.table,
						"Se ha importado el XML y se ha generado un archivo .txt.");
				try {
					FileWriter writer = new FileWriter(
							"C:\\Users\\1AW3-24\\git\\Logro-o_deportes_grupo2\\src\\archivosXML-TXT\\TXT\\consulta.txt");
					writer.write(consulta);
					writer.close();
				} catch (IOException e) {
					System.out.println("Error al generar el fichero .txt");
					e.printStackTrace();
					 try (main.LogronoAPP.logger) {
							main.LogronoAPP.logger.logError("Error con Input u Output", e);
						}
				}
			}
		}

		catch (Exception spe) {
			JOptionPane.showMessageDialog(GestionReserva.table,
					"Ha ocurrido un error al importar o se ha cancelado la operación.");
			 try (main.LogronoAPP.logger) {
					main.LogronoAPP.logger.logError("Error al realizar la operación.", spe);
				}
		}
	}
	
	/**
	 * Método para implementar ActionListener.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}