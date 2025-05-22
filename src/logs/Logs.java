package logs;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logs implements AutoCloseable {
	private static final String ERROR_LOG_FILE = "logs/error.log";
	private PrintWriter sessionLogger;

	// Constructor: crea un nuevo archivo de log por sesión
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
	public void logSession(String message) {
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		sessionLogger.println("[" + timestamp + "] INFO: " + message);
	}

	// Método para registrar errores globales
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