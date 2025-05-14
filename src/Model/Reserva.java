package Model;

public class Reserva {
	
	private String dniPersona;
	private int sesionCodigo;
	
	public Reserva() {}
	
	public Reserva(String dniPersona, int sesionCodigo) {
		super();
		this.dniPersona = dniPersona;
		this.sesionCodigo = sesionCodigo;
	}
	
	@Override
	public String toString() {
		return "Reserva [dniPersona=" + dniPersona + ", sesionCodigo=" + sesionCodigo + "]";
	}
	
	public String getDniPersona() {
		return dniPersona;
	}
	public void setDniPersona(String dniPersona) {
		this.dniPersona = dniPersona;
	}
	public int getSesionCodigo() {
		return sesionCodigo;
	}
	public void setSesionCodigo(int sesionCodigo) {
		this.sesionCodigo = sesionCodigo;
	}
}