package model;

public class Sesion {
	private int id;
	private String fecha;
	private int aforo;
	private int actividad;
	
	public Sesion() {}
	
	public Sesion(int id, String fecha, int aforo, int actividad) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.aforo = aforo;
		this.actividad = actividad;
	}
	
	@Override
	public String toString() {
		return "Sesion [id=" + id + ", fecha=" + fecha + ", aforo=" + aforo + ", actividad=" + actividad + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getAforo() {
		return aforo;
	}
	public void setAforo(int aforo) {
		this.aforo = aforo;
	}
	public int getActividad() {
		return actividad;
	}
	public void setActividad(int actividad) {
		this.actividad = actividad;
	}

}
