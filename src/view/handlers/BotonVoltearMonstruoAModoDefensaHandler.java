package view.handlers;

import cartas.Atacable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import jugador.Jugador;
import partida.Partida;
import view.CajaConsola;
import view.ContenedorDelDuelo;

public class BotonVoltearMonstruoAModoDefensaHandler implements EventHandler<ActionEvent> {

	private Atacable carta;
	private Partida duelo;
	private Jugador jugador;
	private ContenedorDelDuelo cajaDuelo;
	
	public BotonVoltearMonstruoAModoDefensaHandler(Atacable carta, Partida duelo, Jugador jugador, ContenedorDelDuelo cajaDuelo) {
		this.carta = carta;
		this.duelo = duelo;
		this.jugador = jugador;
		this.cajaDuelo = cajaDuelo;
		
	}
	
	@Override
    public void handle(ActionEvent event) {
		this.jugador.voltearCarta(this.carta);
		this.carta.setSeCambioElEstadoEsteTurno(true);
		CajaConsola.agregarMensaje("Se volteo la carta " + this.carta.obtenerNombre() + " y se coloco en modo defensa");
		if (duelo.estaYugiEnTurno()) {
    		cajaDuelo.actualizarVistaYugiEnTurno(jugador, jugador.obtenerJugadorEnemigo());
    	} else {
    		cajaDuelo.actualizarVistaKaibaEnTurno(jugador, jugador.obtenerJugadorEnemigo());
    	}
    }
}
