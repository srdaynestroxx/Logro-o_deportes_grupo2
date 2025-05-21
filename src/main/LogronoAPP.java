package main;

import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import logs_logroño.Logs_logroño;

public class LogronoAPP {

	public static void main(String[] args) throws SQLException, ParserConfigurationException, TransformerException {
		new view.InicioSesion().setVisible(true);

		try (Logs_logroño logger = new Logs_logroño()) {

			logger.logSession("Aplicación iniciada");

			// Ejemplo de operación

			System.out.println("Realizando una operación...");

			logger.logSession("Operación realizada con éxito");

			// Ejemplo de captura de excepción

			logger.logSession("Aplicación finalizada");

		}

	}
}
