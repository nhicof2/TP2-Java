package view;

import java.io.File;

import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jugador.*;
import partida.Partida;
import viewSupportFiles.PathArchivos;

public class ContenedorDelDuelo extends BorderPane implements PathArchivos{

		private MenuDelDuelo barraMenu;
		File direccionMusica;
		private CajaInformacion cajaDerecha;
		private CajaCampo cajaCentro;
		private CajaJugadores cajaIzquierda;
		private CajaConsola consola;
		Partida duelo;
		
	    public ContenedorDelDuelo(Stage stage , Partida duelo) {
	    	super();
	    	this.duelo = duelo;
	    	
	    	this.cajaDerecha = new CajaInformacion();
			this.setRight(cajaDerecha);
	    	
			////////////
			this.direccionMusica = new File("src/viewSupportFiles/Hollow%20Knight%20OST%20-%20False%20Knight.wav");
	        
	        String direccionArreglada = this.direccionMusica.toURI().toString();
	        
	        direccionArreglada = direccionArreglada.replaceAll("2520", "20");
	        
			//////////
			
			
			this.barraMenu = new MenuDelDuelo(stage,direccionArreglada);
            this.setTop(barraMenu);
            Jugador jugadorEnTurno;
            if (duelo.estaYugiEnTurno()) {
            	jugadorEnTurno = duelo.getJugadorYugi();
            } else {
            	jugadorEnTurno = duelo.getJugadorKaiba();
            }
            
           
            this.cajaIzquierda = new CajaJugadores(this.cajaDerecha, duelo, jugadorEnTurno, this,stage);
	    	this.setLeft(cajaIzquierda);
            
	    	this.cajaCentro = new CajaCampo(cajaDerecha, duelo, this);
	    	this.setCenter(cajaCentro);
	    	
	    	this.consola = new CajaConsola();
	    	this.setBottom(consola);
	    	CajaConsola.agregarMensaje("Es el turno de "+jugadorEnTurno.obtenerNombre());
	    	
	    }
	    
	    public MenuDelDuelo getBarraDeMenu() {
			return this.barraMenu;
		}
	    
	    public MenuItem getPantallaCompletaItem() {
	    	return this.barraMenu.getPantallaCompletaItem();
	    }
	    
	    public void actualizarVistaYugiEnTurno(Jugador yugi, Jugador kaiba) {
	    	this.cajaCentro.actualizarVistaYugiEnTurno(yugi, kaiba);
	    	this.cajaIzquierda.actualizarVida(yugi, kaiba);
	    }
	    
	    public void actualizarVistaKaibaEnTurno(Jugador kaiba, Jugador yugi) {
	    	this.cajaCentro.actualizarVistaKaibaEnTurno(kaiba, yugi);
	    	this.cajaIzquierda.actualizarVida(yugi, kaiba);
	    }
	    
	    public void actualizarCajas() {
	    	this.cajaCentro.actualizarCaja();
	    	this.cajaIzquierda.actualizarVida(this.duelo.getJugadorYugi(), this.duelo.getJugadorKaiba());
	    }

		public void loggearMensaje(String string) {
			CajaConsola.agregarMensaje(string);		
		}
	    
}
