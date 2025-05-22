package logs;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase encargada de implementar logs.
 */
public class Logs implements AutoCloseable {
	private static final String ERROR_LOG_FILE = "logs/error.log";
	/**
	 * Writer para el documento encargado de almacenar los logs.
	 */
	private PrintWriter sessionLogger;

	// Constructor: crea un nuevo archivo de log por sesión
	/**
	 * Método para registrar el inicio de los logs y la hora a la que se ha realizado.
	 */
	public Logs() {
		try {
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			File logDir = new File("logs");
			if (!logDir.exists()) {
				logDir.mkdirs(); // Crear directorio si no existe
			}
			String sessionLogFile = "logs/log_" + timestamp + ".txt";
			sessionLogger = new PrintWriter(new FileWriter(sessionLogFile, true), true);
			logSession("=== Nueva sesión iniciada: " + timestamp + " ===");
		} catch (IOException e) {
			logError("Error al crear el archivo de log de sesión", e);
		}
	}

	// Método para registrar operaciones del usuario
	/**
	 * Logs para las acciones realizadas por el usuario.
	 * @param message String el cual definirá la accion realizada para el log.
	 */
	public void logSession(String message) {
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		sessionLogger.println("[" + timestamp + "] INFO: " + message);
	}

	// Método para registrar errores globales
	/**
	 * Logs para los errores que han sucedido en el programa.
	 * @param message String el cual definirá el error ocurrido para el log.
	 * @param e Error de excepción.
	 */
	public static void logError(String message, Exception e) {
		try (PrintWriter errorLogger = new PrintWriter(new FileWriter(ERROR_LOG_FILE, true), true)) {
			String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			errorLogger.println("[" + timestamp + "] ERROR: " + message);
			if (e != null) {
				e.printStackTrace(errorLogger);
			}
		} catch (IOException ioEx) {
			System.err.println("No se pudo escribir en el archivo de errores: " + ioEx.getMessage());
		}
	}

	// Cierra el logger de sesión
	public void close() {
		
	}
}