package view.handlers;

import cartas.Colocable;
import estadoCarta.EstadoCarta;
import estadoCarta.EstadoCartaColocadaBocaArriba;
import exceptions.NoHayLugarVacioException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import jugador.Jugador;
import partida.Partida;
import view.CajaConsola;
import view.ContenedorDelDuelo;

public class BotonBocaArribaHandler implements EventHandler<ActionEvent> {

	private Colocable carta;
	private Partida duelo;
	private Jugador jugador;
	private ContenedorDelDuelo cajaDuelo;
	
	public BotonBocaArribaHandler(Colocable carta, Partida duelo, Jugador jugador, ContenedorDelDuelo cajaDuelo) {
		this.carta = carta;
		this.duelo = duelo;
		this.jugador = jugador;
		this.cajaDuelo = cajaDuelo;
		
	}
	
	@Override
    public void handle(ActionEvent event) {
    	EstadoCarta estadoCarta = new EstadoCartaColocadaBocaArriba();
		try {
			jugador.colocar(carta, estadoCarta);
			jugador.eliminarCartaDeLaMano(carta.obtenerNombre());
			CajaConsola.agregarMensaje("Se coloco la carta " + this.carta.obtenerNombre() + " boca arriba y se activo su efecto");
			if (duelo.estaYugiEnTurno()) {
				cajaDuelo.actualizarVistaYugiEnTurno(jugador, jugador.obtenerJugadorEnemigo());
			} else {
				cajaDuelo.actualizarVistaKaibaEnTurno(jugador, jugador.obtenerJugadorEnemigo());
			}
		} catch (NoHayLugarVacioException e) {
			
		}
    }
	
}
