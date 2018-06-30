package view;

import java.util.Iterator;
import java.util.LinkedList;

import cartas.Activable;
import cartas.Atacable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import jugador.Jugador;
import partida.Partida;
import viewSupportFiles.PathArchivos;

public class CajaCampo extends VBox implements PathArchivos{

	CajaInformacion cajaInformacion;
	
	EspacioCartasCampo campoJ1;
	EspacioCartasCampo campoJ2;
	ManoJugador manoYugi;
	ManoJugador manoKaiba;
	Partida duelo;
	
	public CajaCampo(CajaInformacion cajaInformacion,Partida duelo) {
		
		this.duelo = duelo;
		this.cajaInformacion = cajaInformacion;
		
		this.manoYugi = new ManoJugador(duelo,duelo.getJugadorYugi(), this);
		this.manoYugi.setAlignment(Pos.BOTTOM_CENTER);
		
		this.manoKaiba = new ManoJugador(duelo,duelo.getJugadorKaiba(), this);
		this.manoKaiba.setAlignment(Pos.TOP_CENTER);
		
		this.campoJ1 = new EspacioCartasCampo(cajaInformacion, duelo, duelo.getJugadorYugi(), this);
		this.campoJ2 = new EspacioCartasCampo(180,cajaInformacion, duelo, duelo.getJugadorKaiba(), this);
		
		this.setSpacing(15);
		this.setAlignment(Pos.CENTER);
		VBox.setVgrow(manoKaiba, Priority.ALWAYS);
		VBox.setVgrow(manoYugi, Priority.ALWAYS);
		
		this.getChildren().addAll(manoKaiba,campoJ2,campoJ1,manoYugi);
		
		this.setStyle("-fx-background-color: BLACK");
		
		if (duelo.estaYugiEnTurno()) {
			manoYugi.pintarCartasEnManoJugador(cajaInformacion);
			manoKaiba.darVueltaCartasEnManoJugador(cajaInformacion);
		} else {
			manoKaiba.pintarCartasEnManoJugador(cajaInformacion);
			manoYugi.darVueltaCartasEnManoJugador(cajaInformacion);
		}
	}
	
	
	public void enviarCartasAlCementerio() {
		
		this.campoJ1.actualizarCementerio();
		this.campoJ2.actualizarCementerio();
	}
	
	public void actualizarVistaYugiEnTurno(Jugador yugi, Jugador kaiba) {
		
		this.actualizarCartasEnCampoCentral(yugi, kaiba);
		
		this.manoYugi.pintarCartasEnManoJugador(cajaInformacion);
		this.manoKaiba.darVueltaCartasEnManoJugador(this.cajaInformacion);
		this.campoJ1.actualizarCantidadDeCartasEnMazo();
		this.enviarCartasAlCementerio();
		
	}
	
	public void actualizarVistaKaibaEnTurno(Jugador kaiba, Jugador yugi) {
		
		this.actualizarCartasEnCampoCentral(yugi, kaiba);
		
		this.manoYugi.darVueltaCartasEnManoJugador(this.cajaInformacion);
		this.manoKaiba.pintarCartasEnManoJugador(cajaInformacion);
		this.campoJ2.actualizarCantidadDeCartasEnMazo();
		this.enviarCartasAlCementerio();
		
		
	}

	public void actualizarCartasEnCampoCentral(Jugador yugi, Jugador kaiba) {
		
		this.campoJ1.limpiarCampo();
		this.campoJ2.limpiarCampo();
		this.pintarCartasEnZonaMonstruos(yugi.obtenerMonstruosColocados(), campoJ1);
		this.pintarCartasEnZonaMonstruos(kaiba.obtenerMonstruosColocados(), campoJ2);
		
		this.pintarCartasEnZonaMagicasYTrampas(yugi.obtenerMagicasYTrampasColocadas(), campoJ1);
		this.pintarCartasEnZonaMagicasYTrampas(kaiba.obtenerMagicasYTrampasColocadas(), campoJ2);
		
		this.pintarCartaZonaCampo(yugi.obtenerCartaCampoColocada(), campoJ1);
		this.pintarCartaZonaCampo(kaiba.obtenerCartaCampoColocada(), campoJ2);
		
	}
	
	public void pintarCartasEnZonaMonstruos(LinkedList<Atacable> monstruosYugi, EspacioCartasCampo campo) {
		
		Iterator<Atacable> posicionesIterador = monstruosYugi.iterator();
		int posicionActual = 0;
		Atacable cartaActual;
		Image imagen;
		while(posicionesIterador.hasNext()) {
		
			posicionActual++;
			cartaActual = posicionesIterador.next();
			imagen = new Image(pathDePackCartas + cartaActual.getNombreDeLaImagen());
			if (cartaActual.estaColocadaBocaAbajo()) {
				campo.getEspacioCartaMonstruo(posicionActual).pintarCartaEnModoDefensaBocaAbajo(imagen, cartaActual);
			} else if (cartaActual.estaEnModoAtaque()) {
				campo.getEspacioCartaMonstruo(posicionActual).pintarCartaEnModoAtaque(imagen, cartaActual);
			} else {
				campo.getEspacioCartaMonstruo(posicionActual).pintarCartaEnModoDefensaBocaArriba(imagen, cartaActual);
			}
		
		}
	}
	
	public void pintarCartasEnZonaMagicasYTrampas(LinkedList<Activable> activablesYugi, EspacioCartasCampo campo) {
		
		Iterator<Activable> posicionesIterador = activablesYugi.iterator();
		int posicionActual = 0;
		Activable cartaActual;
		Image imagen;
		while(posicionesIterador.hasNext()) {
		
			posicionActual++;
			cartaActual = posicionesIterador.next();
			imagen = new Image(pathDePackCartas + cartaActual.getNombreDeLaImagen());
			if (cartaActual.estaColocadaBocaAbajo()) {
				campo.getEspacioCartaMagica(posicionActual).pintarCartaBocaAbajo(imagen, cartaActual);
			} else {
				campo.getEspacioCartaMagica(posicionActual).pintarCartaBocaArriba(imagen, cartaActual);
			}
		
		}
	}
		
	public void pintarCartaZonaCampo(LinkedList<Activable> activableYugi, EspacioCartasCampo campo) {
		Iterator<Activable> posicionesIterador = activableYugi.iterator();
		Activable cartaActual;
		Image imagen;
		while(posicionesIterador.hasNext()) {
			cartaActual = posicionesIterador.next();
			imagen = new Image(pathDePackCartas + cartaActual.getNombreDeLaImagen());
			campo.getEspacioCartaCampo().pintarCartaBocaArriba(imagen);
		}
	}
	
	public void actualizarCaja() {
		this.manoYugi.pintarCartasEnManoJugador(cajaInformacion);
		this.manoKaiba.pintarCartasEnManoJugador(cajaInformacion);
		this.actualizarCartasEnCampoCentral(duelo.getJugadorYugi(), duelo.getJugadorKaiba());
		this.enviarCartasAlCementerio();
	}
	

}
