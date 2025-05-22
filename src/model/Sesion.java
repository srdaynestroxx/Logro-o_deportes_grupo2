package model;
/**
 * @author Grupo2 - Logroño Deportes
 */
/**
 * Clase para almacenar los datos de las sesiones.
 */
public class Sesion {
	private int id;
	private String fecha;
	private int aforo;
	private int actividad;
	
	/**
	 * Constructor vacio para sesion.
	 */
	public Sesion() {}
	
	/**
	 * Constructor con parametros para sesion.
	 * @param id Parametro para el id de sesion.
	 * @param fecha Parametro para la fecha de la sesion.
	 * @param aforo Parametro para el aforo de la sesion.
	 * @param actividad Parametro para la actividad de la sesion (piscina).
	 */
	public Sesion(int id, String fecha, int aforo, int actividad) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.aforo = aforo;
		this.actividad = actividad;
	}
	
	/**
	 * Método toString para sesion.
	 */
	@Override
	public String toString() {
		return "Sesion [id=" + id + ", fecha=" + fecha + ", aforo=" + aforo + ", actividad=" + actividad + "]";
	}
	
	/**
	 * Getter para el id de sesion.
	 * @return Devuelve el id de la sesion.
	 */
	public int getId() {
		return id;
	}
	/**
	 * Setter para el id de sesion.
	 * @param id Parametro para el id de sesion.
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Getter para la fecha de la sesion.
	 * @return Devuelve la fecha de la sesion.
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * Setter para la fecha de la sesion.
	 * @param fecha Parametro para la fecha de la sesion.
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * Getter para el aforo de la sesion.
	 * @return Devuelve el aforo de la sesion.
	 */
	public int getAforo() {
		return aforo;
	}
	/**
	 * Setter para el aforo.
	 * @param aforo Parametro para el aforo de la sesion.
	 */
	public void setAforo(int aforo) {
		this.aforo = aforo;
	}
	/**
	 * Getter para la actividad de la sesion.
	 * @return Devuelve la actividad de la sesion.
	 */
	public int getActividad() {
		return actividad;
	}
	
	/**
	 * Setter para actividad.
	 * @param actividad Parametro para la actividad de la sesion.
	 */
	public void setActividad(int actividad) {
		this.actividad = actividad;
	}

}
