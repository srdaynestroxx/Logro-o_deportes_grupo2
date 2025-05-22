package model;
/**
 * @author Grupo2 - Logroño Deportes
 */
import java.io.Serializable;

/**
 * Clase abstracta preparada para ser heredada a las clases Administrador y Empleado.
 */
abstract class Persona implements Serializable {
	

	private String DNI;
	private String nombre;
	private String apellido;
	private String rol;
	private String mail;
	private int telefono;
	private String contrasena;
	
	/**
	 * Constructor vacio para Persona.
	 */
	public Persona() {}
	
	/**
	 * Constructor con parametros para Persona.
	 * @param DNI Parametro encargado del DNI.
	 * @param nombre Parametro encargado del nombre.
	 * @param apellido Parametro encargado del apellido.
	 * @param rol Parametro encargado del rol.
	 * @param mail Parametro encargado del mail.
	 * @param telefono Parametro encargado del telefono.
	 * @param contrasena Parametro encargado de la contraseña.
	 */
	public Persona(String DNI, String nombre, String apellido, String rol, String mail, int telefono,
			String contrasena) {
		super();
		this.DNI = DNI;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
		this.mail = mail;
		this.telefono = telefono;
		this.contrasena = contrasena;
	}
	
	/**
	 * Metodo toString de Persona.
	 */
	@Override
	public String toString() {
		return "Persona [DNI=" + DNI + ", nombre=" + nombre + ", apellido=" + apellido + ", rol=" + rol + ", mail="
				+ mail + ", telefono=" + telefono + ", contrasena=" + contrasena + "]";
	}
	
	/**
	 * Getter para DNI.
	 * @return Devuelve el DNI.
	 */
	public String getDNI() {
		return DNI;
	}
	/**
	 * Setter para DNI.
	 * @param dNI Parametro de DNI para Persona.
	 */
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	/**
	 * Getter para Nombre.
	 * @return Devuelve el nombre.
	 */

	public String getNombre() {
		return nombre;
	}
	/**
	 * Setter para Nombre.
	 * @param nombre Parametro de nombre para Persona.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Getter para Apellido.
	 * @return Devuelve el apellido.
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * Setter para Apellido.
	 * @param apellido Parametro de apellido para Persona.
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * Getter para Rol.
	 * @return Devuelve el rol.
	 */
	public String getRol() {
		return rol;
	}
	/**
	 * Setter para Rol.
	 * @param rol Parametro de rol para Persona.
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}
	/**
	 * Getter para Mail.
	 * @return Devuelve el mail.
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * Setter para Mail.
	 * @param mail Parametro de mail para Persona.
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * Getter para Teléfono.
	 * @return Devuelve el teléfono.
	 */
	public int getTelefono() {
		return telefono;
	}
	/**
	 * Setter para Teléfono.
	 * @param telefono Parametro de teléfono para Persona.
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	/**
	 * Getter para Contraseña.
	 * @return Devuelve la contraseña.
	 */
	public String getContrasena() {
		return contrasena;
	}
	/**
	 * Setter para Contraseña.
	 * @param contrasena Parametro de contraseña para Persona.
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
}
