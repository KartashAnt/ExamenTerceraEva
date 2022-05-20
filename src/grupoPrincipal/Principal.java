package grupoPrincipal;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
// Clase Principal
public class Principal {
	// Jugadores en campo y en banquillo
	static Jugador[] titulares = new Jugador[11];
	static Jugador[] banquillo = new Jugador[2];
	// Historial de los goles
	static ArrayList<Gol> goles = new ArrayList<>();
	// Puntuaciones de los equipos
	static int puntosLiverpool = 0;
	static int puntosReal = 0;
	// Hora del inicio de partido
	static final int HORA_INICIO = 21;
	// Energia acumulada de liverpool usada para comparación
	static final int PUNTUACION_LIVERPOOL = 925;
	// Escanner
	static Scanner sc = new Scanner(System.in);

	// Main en cual inicializamos la programa y pasamos al menu principal
	public static void main(String[] args) {
		inicializar();
		menuPrincipal();
	}

	// Inicializar el equipo
	public static void inicializar() {
		// Caracteristicas de los jugadores
		int[] dorsales = { 1, 2, 3, 12, 8, 10, 14, 15, 25, 9, 21, 6, 20 };
		String[] nombres = { "Courtois", "Carvajal", "Militao", "Marcelo", "Kroos", "Modric", "Casemiro", "Valverde",
				"Camavinga", "Benzema", "Rodrygo", "Nacho", "Vinicius" };
		int[] eMaximos = { 84, 93, 92, 90, 98, 97, 96, 95, 94, 90, 88, 91, 86 };
		int[] eMinimos = { 75, 69, 70, 72, 64, 65, 66, 67, 68, 80, 81, 71, 82 };
		// Creo jugadores
		for (int i = 0; i < titulares.length; i++) {
			titulares[i] = new Jugador(dorsales[i], nombres[i], eMaximos[i], eMinimos[i]);
		}
		for (int i = titulares.length; i < titulares.length + banquillo.length; i++) {
			banquillo[i - titulares.length] = new Jugador(dorsales[i], nombres[i], eMaximos[i], eMinimos[i]);
		}
	}

	// Menu Principal
	public static void menuPrincipal() {
		// Repeticion
		while (true) {
			// Pinto el menu
			pintarMenu();
			// Entada de los datos
			int entrada = entero();
			// Opciones
			switch (entrada) {
			// Elegir titularres
			case 1:
				elegirTitular();
				break;
			// Partido hasta minuto 85
			case 2:
				partidoHasta85();
				break;
			// Usamos el interfaz de remontada
			case 3:
				remontada();
				break;
			// Finalizar el partido
			case 4:
				finalizar();
				break;
			// Resumen del partido
			case 5:
				resumen();
				break;
			// Opción invalida
			default:
				System.out.println("Opci\u00F3n invalida");
				break;
			}
		}
	}

	// Metodo que pinta al menu
	public static void pintarMenu() {
		// Muestro puntos
		System.out.println("Liverpool " + puntosLiverpool + " - Real Madrid " + puntosReal);
		// Propio menu
		System.out.println(	"1.- Elegir equipo titular\n" + 
							"2.- Partido hasta minuto 85\n" + 
							"3.- Ejecucción interfaz remontada\n" + 
							"4.- Finalizar partido\n" + 
							"5.- Resumen");
		// Pedimos una opción
		System.out.print("Elige una opción: ");

	}
	// Metodo para cambiar jugadores
	public static void elegirTitular() {
		while (true) {
			// Muestro los jugadores
			mostrarJugadores();
			// Busco jugador que pasa al banquillo
			Jugador pasaBanquillo = null;
			// busco un jugador real
			while (pasaBanquillo == null) {
				// Pregunto su dorsal
				System.out.println("\nDorsal de jugador titular que pasa al banquillo(-1 para volver al menu principal)");
				int entrada = entero();
				// Si es -1 volvemos al menu principal
				if (entrada == -1) {
					return;
				}
				// Si es 1 entonces no podemos cambiar el portero y pasamos al siguienta iteración de mi bucle
				if (entrada == 1) {
					System.out.println("Courtois no puede ser llevado al banquillo");
					continue;
				}
				// Busco el jugador por su dorsal
				pasaBanquillo = buscarJugadorPorDorsal(entrada, Arrays.asList(titulares));
			}
			// Muestro el nombre del jugador
			System.out.println(pasaBanquillo.getNombre());
			// Busco jugador que sale al campo
			Jugador pasaCampo = null;
			while (pasaCampo == null) {
				// Uso el dorsal para busqueda
				System.out.println("\nDorsal de jugador que sale al campo");
				int entrada = entero();
				pasaCampo = buscarJugadorPorDorsal(entrada, Arrays.asList(banquillo));
			}
			// Muestro el nombre del jugador
			System.out.println(pasaCampo.getNombre());
			// Cambio los jugadores
			cambiarJugadores(pasaCampo, pasaBanquillo);
		}

	}

	// Metodo para mostrar los jugadores titulares y los del banquillo
	public static void mostrarJugadores() {
		// Imprimo total de los jugadores titulares y a continuación cada uno de ellos
		System.out.println(titulares.length + " jugadores titulares\n");
		imprimirJugadores(titulares);
		// Imprimo total de los jugadores en el banquillo y a continuación cada uno de ellos
		System.out.println("\n" + banquillo.length + " jugadores en el banquillo\n");
		imprimirJugadores(banquillo);
	}

	// Metodo para imprimir todos los jugadores de un array
	public static void imprimirJugadores(Jugador[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
	
	// Metodo para buscar a un jugador por su dorsal en una lista
	public static Jugador buscarJugadorPorDorsal(int dorsal, List<Jugador> jugadores) {
		return jugadores.stream().filter(jugador -> jugador.getDorsal() == dorsal).findFirst().orElse(null);
	}
	
	// Metodo para realizar cambio de los jugadores
	public static void cambiarJugadores(Jugador pasaCampo, Jugador pasaBanquillo) {
		for (int i = 0; i < titulares.length; i++) {
			if (titulares[i] == pasaBanquillo) {
				titulares[i] = pasaCampo;
				break;
			}
		}
		for (int i = 0; i < banquillo.length; i++) {
			if (banquillo[i] == pasaCampo) {
				banquillo[i] = pasaBanquillo;
				break;
			}
		}
	}
	
	// Metodo para simular el partido hasta el minuto 85
	public static void partidoHasta85() {
		// Anulamos los puntos y los goles
		puntosLiverpool = 0;
		puntosReal = 0;
		goles.removeAll(goles);
		// Random que vamos a usar
		Random r = new Random();
		// Numero de goles(entre 2 y tres)
		int numeroGoles = r.nextInt(2) + 2;
		// Tres tramos temporales dados estan separados por 4 numeros
		int[] minutos = { 0, 45, 76, 86 };
		// Contador de los goles
		int cont = 1;
		// Contamos exactamente el numero de los goles
		while (cont <= numeroGoles) {
			// Randomizamos el minuto ebn función de los tramos indicados
			int minuto = r.nextInt(minutos[cont] - minutos[cont - 1]) + minutos[cont - 1];
			// Generamos gol
			asignarGol(minuto, r);
			// Pasamos
			enter();
			cont++;
		}

	}

	// Metodo que implementa la remontada
	public static void remontada() {
		// Energia extra sumada a cada uno de los jugadores del campo
		int energiaExtra = 0;
		//Pedimos la entrada hasta que no sea menor de 5 ni mayor de 10
		do {
			// Pedimos la entrada
			System.out.println("Extra de energ\u00EDa a aplicar(5-10): ");
			energiaExtra = entero();
		} while (energiaExtra < 5 || energiaExtra > 10);
		// Aumento energia a cada uno de los jugadores
		for (int i = 0; i < titulares.length; i++) {
			titulares[i].aumentarEnergia(energiaExtra);
		}
		// Mostramos a cuantos jugadores hemos aumentado la energia
		System.out.println("Energia aumentada a los " + titulares.length + " jugadores");
	}

	// Metodo para finalizar el partido
	public static void finalizar() {
		// Vamos desde minuto 85
		int minuto = 85;
		// Random que usamos
		Random r = new Random();
		// Generamos numero de goles(entre 3 y 4)
		int numeroGoles = r.nextInt(2) + 3;
		// Hasta que nos quedan goles por generar
		while (numeroGoles > 0) {
			// Sumamos 2 a los minutos
			minuto += 2;
			// Generamos gol
			asignarGol(minuto, r);
			// Pasamos
			enter();
			numeroGoles--;
		}
		// Indicamos el final del partido
		System.out.println("FIN DEL PARTIDO!! : LIVERPOOL " + puntosLiverpool + " - REAL MADRID " + puntosReal);
		enter();
	}
	
	// Metodo que muestra resumen de partido (historial de los goles)
	public static void resumen() {
		for (Gol gol : goles) {
			System.out.println(gol);
		}
		enter();
	}

	//El proximo metodo se usa en partidoHasta85 y finalizar
	
	// Metodo para generar gol
	public static void asignarGol(int minuto, Random r) {
		// Puntos generados y nombre del marcador
		int energiaMayor = 0;
		String marcador = "";
		// Sumatorio de energia
		int energiaTotal = 0;
		// Generamos energia
		for (int i = 0; i < titulares.length; i++) {
			// Obtenemos la energia de un jugador y la añadimos al sumatorio
			int energia = titulares[i].obtenerEnergia(r);
			energiaTotal += energia;
			// Si es mayor que energia del pasado marcador potencial se convierte marcador
			if (energia > energiaMayor) {
				energiaMayor = energia;
				marcador = titulares[i].getNombre();
			}
		}
		// Si energia de RM es mayor o igual que la de Liver se la marca un gol
		if (energiaTotal >= PUNTUACION_LIVERPOOL) {
			// Tiramos el mensaje de madird
			mensajeDeMarcado("Madrid", marcador, minuto);
			// Aumentamos puntuación de RM
			puntosReal++;
			// Recordamos el gol
			goles.add(new Gol(minuto, puntosLiverpool + " - " + puntosReal, marcador));
		} 
		// Marca Liver
		else {
			// Selector randomizado de marcador de Liver
			int selector = r.nextInt(4);
			// Seleccionamos el nombre de marcador en función de valor del selector
			switch (selector) {
			case 2:
				marcador = "Jota";
				break;
			case 3:
				marcador = "Mane";
				break;
			default:
				marcador = "Salah";
				break;
			}
			// Tiramos el mensaje de liver
			mensajeDeMarcado("Liverpool", marcador, minuto);
			// Aumentamos puntos al Liverpool
			puntosLiverpool++;
			// Recordamos el gol
			goles.add(new Gol(minuto, puntosLiverpool + " - " + puntosReal, marcador));
		}
	}
	
	// Mostrar mensaje del marcado
	public static void mensajeDeMarcado(String equipo, String jugador, int minuto) {
		// Si se marco ante de la minuto 60
		if (minuto < 60) {
			System.out.println("Gol del " + equipo + "!!! Gol de " + jugador + "!!!\n" + "minuto " + minuto + ": "
					+ LocalTime.of(HORA_INICIO, minuto));
		}
		// Si se marca a partir de minuto 60
		else {
			System.out.println("Gol del " + equipo + "!!! Gol de " + jugador + "!!!\n" + "minuto " + minuto + ": "
					+ LocalTime.of(HORA_INICIO + 1, minuto - 60));
		}
	
	}

	// Metodo para introducir a un entero
	public static int entero() {
		// Introducimos una cadena hasta que sea un numero
		int numero = 0;
		boolean repetir = true;
		while (repetir) {
			try {
				numero = Integer.parseInt(sc.nextLine());
				repetir = false;
			} catch (NumberFormatException e) {
				System.out.print("Entrada invalida. Vuelva a repetir:");
			}
		}
		return numero;
	}

	// Metodo para pedir pulsar enter
	public static void enter() {
		System.out.println("Pulsa enter");
		sc.nextLine();
	}
}
