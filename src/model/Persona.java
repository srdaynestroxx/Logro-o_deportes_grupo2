package model;

abstract class Persona {
	
	private String DNI;
	private String nombre;
	private String apellido;
	private String rol;
	private String mail;
	private int telefono;
	private String contrasena;
	
	
	public Persona() {}
	
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
	
	@Override
	public String toString() {
		return "Persona [DNI=" + DNI + ", nombre=" + nombre + ", apellido=" + apellido + ", rol=" + rol + ", mail="
				+ mail + ", telefono=" + telefono + ", contrasena=" + contrasena + "]";
	}
	
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
}
