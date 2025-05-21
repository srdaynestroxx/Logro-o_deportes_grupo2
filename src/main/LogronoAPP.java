package main;
/**
 * @author Grupo2 - Logroño Deportes
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import logs.Logs;
import view.ConsultarUsuariosAdministrador;
import view.GestionReserva;

/**
 * Clase principal encargada de iniciar el programa.
 */
public class LogronoAPP {
	public final static Logs logger = new Logs();
	/**
	 * Constructor vacio para LogronoAPP.
	 */
	public LogronoAPP() {
	}
	
	/**
	 * Método encargado de hacer visible el inicio de sesión y asi iniciar el programa.
	 * @param args Parametro de los argumentos de java.
	 * @throws SQLException Devuelve el posible error de SQL.
	 * @throws ParserConfigurationException Devuelve error en caso de fallar al parsear.
	 * @throws TransformerException Devuelve error en caso de fallar en el transformer.
	 */
	public static void main(String[] args) throws SQLException, ParserConfigurationException, TransformerException {
		// Visualizar ventana de Inicio de Sesion.
		new view.InicioSesion().setVisible(true);
		try (logger) {

			logger.logSession("Aplicación iniciada");

		}
	}
}
