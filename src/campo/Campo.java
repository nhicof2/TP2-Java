package campo;

import java.util.LinkedList;

import cartas.*;
import estadoCarta.EstadoCarta;
import exceptions.NoHayLugarVacioException;
import exceptions.NoHayMonstruoParaSacrificarException;
import jugador.Jugador;

public class Campo {

	private ZonaMonstruos monstruos;
	private ZonaMagicasYTrampas magicasYTrampas ;
	private ZonaCampo espacioCampo;
	private Cementerio cementerio;
	private Mazo mazoDelJugador;
	private Jugador jugador;
	
	public Campo() {
		//Todas las zonas se inicializan vacias y el mazo se inicializa con 40 cartas
		//ordenadas aleatoriamente
		this.monstruos = new ZonaMonstruos();
		this.magicasYTrampas = new ZonaMagicasYTrampas();
		this.espacioCampo = new ZonaCampo();
		this.cementerio = new Cementerio();
		this.mazoDelJugador = new Mazo();
		this.jugador = new Jugador(this);
		this.jugador.setCampoEnemigo(this);
	}
	
	public Campo(Jugador jugadorDuenio) {
		//Todas las zonas se inicializan vacias y el mazo se inicializa con 40 cartas
		//ordenadas aleatoriamente
		this.monstruos = new ZonaMonstruos();
		this.magicasYTrampas = new ZonaMagicasYTrampas();
		this.espacioCampo = new ZonaCampo();
		this.cementerio = new Cementerio();
		this.mazoDelJugador = new Mazo();
		this.jugador = jugadorDuenio;
	}

	public void aumentarAtaqueMonstruosPorEfectoCampo(Puntos puntosAtaque) {
		this.monstruos.aumentarAtaqueMonstruoPorEfectoCampo(puntosAtaque);
	}
	
	public void aumentarDefensaMonstruosPorEfectoCampo(Puntos puntosDefensa) {
		this.monstruos.aumentarDefensaMonstruoPorEfectoCampo(puntosDefensa);
	}
	
	public void colocarCarta(Colocable cartaAColocar,EstadoCarta estadoAColocar) throws NoHayLugarVacioException {
		cartaAColocar.colocarse(this.jugador, this.monstruos, this.magicasYTrampas, this.espacioCampo, this.cementerio, estadoAColocar);
	}
	
	public int obtenerCantidadDeCartasEnZonaMonstruos() {
		return monstruos.obtenerCantidadDeCartas();
	}
	
	public int obtenerCantidadDeCartasEnZonaMagicasYTrampas() {
		return magicasYTrampas.obtenerCantidadDeCartas();
	}
	
	public int obtenerCantidadDeCartasEnZonaCampo() {
		return espacioCampo.obtenerCantidadDeCartas();
	}
	
	public int obtenerCantidadDeCartasEnMazo() {
		return mazoDelJugador.obtenerCantidaDeCartas();
	}
	
	public int obtenerCantidadDeCartasEnCementerio() {
		return cementerio.obtenerCantidadDeCartas();
	}
	
	public boolean tieneCartas() {

		return ((this.obtenerCantidadDeCartasEnZonaMonstruos() + this.obtenerCantidadDeCartasEnZonaMagicasYTrampas() + this.obtenerCantidadDeCartasEnZonaCampo()) > 0);

	}
	
	public int obtenerCantidadDeCartasEnJuego() {
		
		return (this.obtenerCantidadDeCartasEnZonaMonstruos() + this.obtenerCantidadDeCartasEnZonaMagicasYTrampas() + this.obtenerCantidadDeCartasEnZonaCampo());
	}
	
	
	public Colocable levantarCartaDelMazo() {
		return this.mazoDelJugador.levantarCarta();
	}
	
	public void enviarCartasDestruidasAlCementerio() {
		int danio = this.monstruos.obtenerDanioRecibido();
		LinkedList<Destruible> cartasAEnterrar = new LinkedList<Destruible>();
		cartasAEnterrar.addAll(this.monstruos.recolectarCartasDestruidas());
		cartasAEnterrar.addAll(this.magicasYTrampas.recolectarCartasDestruidas());
		cartasAEnterrar.addAll(this.espacioCampo.recolectarCartasDestruidas());
		this.cementerio.agregarCartas(cartasAEnterrar);
		
		this.jugador.recibirAtaque(danio);
		
	}
	
	public void vaciarZonaMonstruos() {
		this.monstruos.vaciar();
		this.enviarCartasDestruidasAlCementerio();
	}
	
	public void eliminarUltimaCartaMonstruoColocada() {
		this.monstruos.eliminarUltimaCartaMonstruoColocada();
		this.enviarCartasDestruidasAlCementerio();
	}

	public void activarEfectoDeCampo(Campo campoEnemigo) {
		this.espacioCampo.activarEfectoDeCampo(this,campoEnemigo);
	}
	
	//Para PotOfGreed	
	public void agregarAManoCartaDelMazo() {
		this.jugador.tomarCartaDelMazo();
	}
	
	public Atacable obtenerCartaMonstruoConMenorAtaque() throws NoHayMonstruoParaSacrificarException {
		Atacable monstruoConMenorAtaque = this.monstruos.obtenerMonstruoConMenorAtaque();
		return monstruoConMenorAtaque;
	}

	public Activable obtenerCartaTrampa() {
		
		return magicasYTrampas.obtenerCartaTrampa();
		
	}

	public void hacerDanioAlJugador(Puntos puntosDeDanio) {
		
		jugador.recibirAtaque( puntosDeDanio.obtenerPuntosActuales() );
		
	}
	
	public Jugador obtenerDuenio() {
		return this.jugador;
	}
	
	public void vaciarZonaCampo() {
		this.espacioCampo.destruirCarta();
		this.enviarCartasDestruidasAlCementerio();
	}

	public LinkedList<String> obtenerNombresDeCartasMagicas() {
		
		LinkedList<String> magicasActivables = this.magicasYTrampas.obtenerNombresDeCartasMagicas();
		
		return magicasActivables;
	}
	
	public Atacable obtenerCartaDeZonaMonstruo(String nombreCartaMonstruoAtacante) {
		
		return monstruos.obtenerCarta( nombreCartaMonstruoAtacante );
	}

	public Activable obtenerCartaDeZonaMagicasYTrampas(String nombreCartaMonstruoAtacante) {
		
		return magicasYTrampas.obtenerCarta( nombreCartaMonstruoAtacante );
	}

	public LinkedList<String> obtenerNombresDeCartasAtacables() {
	
		LinkedList<String> nombresDeCartas = this.monstruos.obtenerNombresDeCartasAtacables();
		
		return nombresDeCartas;
	}
	
	public LinkedList<String> obtenerNombresDeCartasAtacablesEnModoAtaque() {
		
		LinkedList<String> nombresDeCartas = this.monstruos.obtenerNombresDeCartasAtacablesEnModoAtaque();
		
		return nombresDeCartas;
	}

	public LinkedList<Atacable> obtenerCartasEnZonaMonstruo() {
		
		LinkedList<Atacable>monstruosColocados = monstruos.obtenerMonstruosColocados();
		
		return monstruosColocados;
	}

	public LinkedList<Activable> obtenerMagicasYTrampasColocadas() {
		
		return this.magicasYTrampas.obtenerCartasColocadas();
	}

	public LinkedList<Activable> obtenerCartaCampoColocada() {
		
		return this.espacioCampo.obtenerCartaColocada();
	}

	public String obtenerNombreDeLaImagenDeLaUltimaCartaDelCementerio() {
		return this.cementerio.obtenerNombreDeLaImagenDeLaUltimaCarta();
	}
	
	public void reiniciarAtaquesMonstruosColocados() {

		this.monstruos.reiniciarAtaquesMonstruos();
	
	}

	public void reiniciarSeCambioElEstadoEsteTurnoMonstruosColocados() {
		this.monstruos.reiniciarSeCambioElEstadoEsteTurno();
	}
	
	
}