package partida;

import jugador.Jugador;

public class Partida {

	private Fase faseInicial;
	private Fase fasePreparacion;
	private Fase faseAtaqueYTrampas; 
	private Fase faseFinal;
	private Fase faseActual;
	private EstadoPartida estado;
	
	public Partida() {
		this.faseInicial = new FaseInicial();
		this.fasePreparacion = new FasePreparacion();
		this.faseAtaqueYTrampas = new FaseAtaqueYTrampas();
		this.faseFinal = new FaseFinal();
		
		this.faseInicial.setFaseSiguiente(this.fasePreparacion);
		this.fasePreparacion.setFaseSiguiente(this.faseAtaqueYTrampas);
		this.faseAtaqueYTrampas.setFaseSiguiente(this.faseFinal);
		this.faseFinal.setFaseSiguiente(this.faseInicial);
		
		this.faseActual = this.faseInicial;
		
		this.estado = new EstadoPartidaEnJuego();
	}
	
	public static void main(String[] args) {
		Partida partida = new Partida();
		partida.comienzaElDuelo();
	}
	
	public void comienzaElDuelo() {
		Jugador jugadorEnTurno = null;
		String nombreJugadorEnTurno;
		Jugador jugadorYugi = new Jugador();
		Jugador jugadorKaiba = new Jugador();
		jugadorYugi.setName("Yugi");
		jugadorKaiba.setName("Kaiba");
		String ganador;
		
		jugadorEnTurno = this.elegirQuienComienza(jugadorYugi, jugadorKaiba);
		
		jugadorYugi.enfrentarseA(jugadorKaiba);
		jugadorKaiba.enfrentarseA(jugadorYugi);
		
		int vidaJugadorEnTurno;
		
		while (this.estado.continuaLaPartida()) {
		
			
			jugadorEnTurno = this.faseActual.obtenerJugadorEnTurno(jugadorEnTurno);
			nombreJugadorEnTurno = jugadorEnTurno.obtenerNombre();
			
			this.estado = this.faseActual.ejecutarFase(jugadorEnTurno, this.estado);
			vidaJugadorEnTurno = jugadorEnTurno.obtenerVida();
			System.out.println("Al jugador " + nombreJugadorEnTurno + " le quedan " + vidaJugadorEnTurno + " puntos de vida");
			System.out.println("Al jugador " + jugadorEnTurno.obtenerJugadorEnemigo().obtenerNombre() + " le quedan " +jugadorEnTurno.obtenerJugadorEnemigo().obtenerVida() + " puntos de vida" );
			
			this.faseActual = this.faseActual.obtenerFaseSiguiente();
		
		}
		
		if (jugadorKaiba.estaDerrotado()) {
			ganador = jugadorYugi.obtenerNombre();
		} else {
			ganador = jugadorKaiba.obtenerNombre();
		}
		
		if (jugadorYugi.estaDerrotado()) {
			System.out.println("No gano ninguno, son malisimos los dos.");
		} else {
			System.out.println("Gano el jugador " + ganador + ", el otro es malismo.");
		}
		
		
	}
	
	public Jugador elegirQuienComienza(Jugador unJugador, Jugador otroJugador) {
		
		Jugador jugadorQueComienza = unJugador ;
		
		if(Math.random() < 0.5) {
			jugadorQueComienza = otroJugador;
		}
		
		return jugadorQueComienza;
	}
	
}
