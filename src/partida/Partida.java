package partida;

import jugador.Jugador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ContenedorDeBienvenida;
import view.ContenedorDelDuelo;
import view.handlers.EscOnKeyPressedHandler;

public class Partida {

	private Fase faseInicial;
	private Fase fasePreparacion;
	private Fase faseAtaqueYTrampas; 
	private Fase faseFinal;
	private Fase faseActual;
	private EstadoPartida estado;
	private boolean seJugoCartaMonstruo;
	Jugador jugadorEnTurno;
	String nombreJugadorEnTurno;
	Jugador jugadorYugi;
	Jugador jugadorKaiba;
	String ganador;
	int vidaDeJugadorEnTurno;
	
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
		
		
		this.jugadorYugi = new Jugador();
		this.jugadorKaiba = new Jugador();
		this.jugadorYugi.setName("Yugi");
		this.jugadorKaiba.setName("Kaiba");
		this.jugadorEnTurno = this.elegirQuienComienza(jugadorYugi, jugadorKaiba);
		
		this.jugadorYugi.enfrentarseA(jugadorKaiba);
		this.jugadorKaiba.enfrentarseA(jugadorYugi);
		
		this.vidaDeJugadorEnTurno = 8000;
		
		this.seJugoCartaMonstruo = false;
		
	}
	
//	public static void main(String[] args) {
//		Partida partida = new Partida();
//		partida.comienzaElDuelo();
//	}
	
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
		
			if (faseActual.esFasePreparacion()) {
				this.seJugoCartaMonstruo = false;
			}
			
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
	
	public boolean estaEnFaseDePreparacion() {
		return this.faseActual.esFasePreparacion();
	}
	
	
	public Jugador elegirQuienComienza(Jugador unJugador, Jugador otroJugador) {
		
		Jugador jugadorQueComienza = unJugador ;
		
		if(Math.random() < 0.5) {
			jugadorQueComienza = otroJugador;
		}
		
		return jugadorQueComienza;
	}
	
	public Jugador getJugadorYugi() {
		return this.jugadorYugi;
	}
	
	public Jugador getJugadorKaiba() {
		return this.jugadorKaiba;
	}
	
	public boolean estaYugiEnTurno() {
		return (this.jugadorEnTurno == this.jugadorYugi);
	}
	
	public void setSeJugoCartaMonstruo() {
		this.seJugoCartaMonstruo = true;
	}
	
	public boolean seJugoUnaCartaMonstruoEsteTurno() {
		return this.seJugoCartaMonstruo;
	}
	
	public void avanzarFase() {
		this.faseActual = faseActual.obtenerFaseSiguiente();
	}
	
	public void finalizarTurno() {
		this.jugadorEnTurno = this.jugadorEnTurno.obtenerJugadorEnemigo();
		this.faseActual = this.faseInicial;
	}
}
