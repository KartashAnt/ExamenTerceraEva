package grupoPrincipal;

import java.util.Random;

// Clase de los jugadores
public class Jugador implements Remontada {
	// Atributos
	private int dorsal;
	private String nombre;
	private int energiaMaxima;
	private int energiaMinima;

	// Constructor con todos los atributos
	public Jugador(int dorsal, String nombre, int energiaMaxima, int energiaMinima) {
		super();
		this.dorsal = dorsal;
		this.nombre = nombre;
		this.energiaMaxima = energiaMaxima;
		this.energiaMinima = energiaMinima;
	}

	// Generamos la energia en función de energia maxíma y minima de un jugador
	public int obtenerEnergia(Random r) {
		return r.nextInt(energiaMaxima - energiaMinima) + energiaMinima;
	}

	// Metodo para aumentar los limites de la energia de un jugador
	@Override
	public void aumentarEnergia(int extraEnergia) {
		this.energiaMaxima += extraEnergia;
		this.energiaMinima += extraEnergia;
	}

	// Mostramos el dorsal y el nombre del jugador
	@Override
	public String toString() {
		return dorsal + " " + nombre;
	}

	// Getters y Setters Basicos
	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEnergiaMaxima() {
		return energiaMaxima;
	}

	public void setEnergiaMaxima(int energiaMaxima) {
		this.energiaMaxima = energiaMaxima;
	}

	public int getEnergiaMinima() {
		return energiaMinima;
	}

	public void setEnergiaMinima(int energiaMinima) {
		this.energiaMinima = energiaMinima;
	}

}
