package model;
/**
 * @author Grupo2 - Logroño Deportes
 */
/**
 * Clase para almacenar los datos de las reservas.
 */
public class Reserva {
	
	private String dniPersona;
	private int sesionCodigo;
	
	/**
	 * Constructor vacio para reserva.
	 */
	public Reserva() {}
	
	/**
	 * Constructor con parametros para reserva.
	 * @param dniPersona Parametro para el DNI de la reserva.
	 * @param sesionCodigo Parametro para el codigo de sesion.
	 */
	public Reserva(String dniPersona, int sesionCodigo) {
		super();
		this.dniPersona = dniPersona;
		this.sesionCodigo = sesionCodigo;
	}
	
	/**
	 * Método toString de Reserva.
	 */
	@Override
	public String toString() {
		return "Reserva [dniPersona=" + dniPersona + ", sesionCodigo=" + sesionCodigo + "]";
	}
	
	/**
	 * Getter para dniPersona.
	 * @return Devuelve el DNI de la persona realizando la reserva.
	 */
	public String getDniPersona() {
		return dniPersona;
	}
	/**
	 * Setter para dniPersona
	 * @param dniPersona Parametro para el DNI de la persona haciendo la reserva.
	 */
	public void setDniPersona(String dniPersona) {
		this.dniPersona = dniPersona;
	}
	
	/**
	 * Getter para sesionCodigo.
	 * @return Devuelve el codigo de la sesion de la reserva.
	 */
	public int getSesionCodigo() {
		return sesionCodigo;
	}
	/**
	 * Setter para sesionCodigo
	 * @param sesionCodigo Parametro para el codigo de sesion de la reserva.
	 */
	public void setSesionCodigo(int sesionCodigo) {
		this.sesionCodigo = sesionCodigo;
	}
}
