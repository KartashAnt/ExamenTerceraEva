package grupoPrincipal;

// Clase de un gol
public class Gol {
	// Atributos
	private int minuto;
	private String puntuacion;
	private String nombreJugador;
	
	// Constructor con todos los atributos
	public Gol(int minuto, String puntuacion, String nombreJugador) {
		super();
		this.minuto = minuto;
		this.puntuacion = puntuacion;
		this.nombreJugador = nombreJugador;
	}
	
	// Mostramos el momento del gol
	@Override
	public String toString() {
		return "minuto " + minuto + " : " + puntuacion + " : " + nombreJugador;
	}

	// Getters y Setters basicos
	public int getMinuto() {
		return minuto;
	}
	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
	public String getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
	}
	public String getNombreJugador() {
		return nombreJugador;
	}
	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}
	
}
