package view;

import cartas.Colocable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import jugador.Jugador;
import partida.Partida;
import view.handlers.MouseArribaDeImagenHandler;
import view.handlers.MouseSalirArribaDeImagenHandler;
import viewSupportFiles.PathArchivos;

public class EspacioCartaMonstruo extends StackPane implements PathArchivos{
	
	
	private ImageView imagenCarta;
	private ImageView cardBack;
	private	CajaInformacion cajaInformacion;
	private	Jugador jugadorDuenio;
	private Colocable carta;
	private Partida partida;
	private ContenedorDelDuelo cajaDuelo;
	
		public EspacioCartaMonstruo(CajaInformacion cajaInformacion, Partida duelo, Jugador jugadorDuenio, ContenedorDelDuelo cajaDueloRecibida) {
			
			this.cajaInformacion = cajaInformacion;
			this.jugadorDuenio = jugadorDuenio;
			this.partida = duelo;
			this.cajaDuelo = cajaDueloRecibida;
			
			Rectangle rectanguloAtaque = new Rectangle();
			rectanguloAtaque.setWidth(60);
			rectanguloAtaque.setHeight(100);
			rectanguloAtaque.setStroke(Color.WHITE);
			
			Rectangle rectanguloDefensa = new Rectangle();
			rectanguloDefensa.setWidth(100);
			rectanguloDefensa.setHeight(60);
			rectanguloDefensa.setStroke(Color.WHITE);
			
			Text textoZona = new Text("Monstruos");
			textoZona.setStroke(Color.WHITE);
			
			this.imagenCarta = null;
			this.cardBack = new ImageView(new Image(pathDePackCartas+"cardBackAlgo.png"));
			
			this.getChildren().addAll(rectanguloAtaque,rectanguloDefensa,textoZona);
			this.setAlignment(Pos.CENTER);
			
		}
		
		public void pintarCartaEnModoAtaque(Image imagen, Colocable cartaRecibida) {
			this.getChildren().remove(imagenCarta);
			this.carta = cartaRecibida;
			
			this.imagenCarta = new ImageView(imagen);
			imagenCarta.setFitWidth(60);
			imagenCarta.setFitHeight(100);
			
			MouseArribaDeImagenHandler ponerEnZoom = new MouseArribaDeImagenHandler(imagenCarta,cajaInformacion);
			imagenCarta.setOnMouseEntered(ponerEnZoom);
			 
			MouseSalirArribaDeImagenHandler sacarDeZoom = new MouseSalirArribaDeImagenHandler(cajaInformacion);
			imagenCarta.setOnMouseExited(sacarDeZoom);
			
			if (this.partida.jugadorEstaEnTurno(this.jugadorDuenio)) {
				this.carta.clickEnZona(this.partida, this.jugadorDuenio, this.cajaDuelo, this.imagenCarta);
			}
			
			this.getChildren().add(imagenCarta);
		}
		
		public void pintarCartaEnModoDefensaBocaArriba(Image imagen, Colocable cartaRecibida) {
			this.getChildren().remove(imagenCarta);
			this.carta = cartaRecibida;
			
			this.imagenCarta = new ImageView(imagen);
			this.imagenCarta.setFitWidth(60);
			this.imagenCarta.setFitHeight(100);
			this.imagenCarta.setRotate(90);
			
			MouseArribaDeImagenHandler ponerEnZoom = new MouseArribaDeImagenHandler(imagenCarta,cajaInformacion);
			this.imagenCarta.setOnMouseEntered(ponerEnZoom);
			 
			MouseSalirArribaDeImagenHandler sacarDeZoom = new MouseSalirArribaDeImagenHandler(cajaInformacion);
			this.imagenCarta.setOnMouseExited(sacarDeZoom);
			
			if (this.partida.jugadorEstaEnTurno(this.jugadorDuenio)) {
				this.carta.clickEnZona(this.partida, this.jugadorDuenio, this.cajaDuelo, this.imagenCarta);
			}
			
			this.getChildren().add(imagenCarta);
		}
		
		public void pintarCartaEnModoDefensaBocaAbajo(Image imagen, Colocable cartaRecibida) {
			this.getChildren().remove(imagenCarta);
			this.carta = cartaRecibida;
			
			this.cardBack.setFitWidth(60);
			this.cardBack.setFitHeight(100);
			this.cardBack.setRotate(90);
			this.imagenCarta = new ImageView(imagen);
			
			
			if(this.partida.jugadorEstaEnTurno(this.jugadorDuenio)) {
				MouseArribaDeImagenHandler ponerEnZoom = new MouseArribaDeImagenHandler(imagenCarta,cajaInformacion);
				this.cardBack.setOnMouseEntered(ponerEnZoom);
				
			}else{
				MouseArribaDeImagenHandler ponerEnZoom = new MouseArribaDeImagenHandler(cardBack , cajaInformacion);
				this.cardBack.setOnMouseEntered(ponerEnZoom);
			}
			
			MouseSalirArribaDeImagenHandler sacarDeZoom = new MouseSalirArribaDeImagenHandler(cajaInformacion);
			this.cardBack.setOnMouseExited(sacarDeZoom);
			this.getChildren().add(this.cardBack);
			
			if (this.partida.jugadorEstaEnTurno(this.jugadorDuenio)) {
				this.carta.clickEnZona(this.partida, this.jugadorDuenio, this.cajaDuelo, this.cardBack);
			}
			
		}
		
		public void limpiar() {
			if(this.getChildren().contains(this.imagenCarta)) {
			this.getChildren().remove(this.imagenCarta);
			this.imagenCarta = null;
			}
			else this.getChildren().remove(this.cardBack);
			
			this.carta = null;
		}
		
		public void enviarAl(EspacioCementerio cementerio) {
			cementerio.recibirCarta(imagenCarta);
			this.getChildren().remove(imagenCarta);
			this.imagenCarta = null;
		}
			
}
