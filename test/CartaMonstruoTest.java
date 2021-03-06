import static org.junit.Assert.*;
import org.junit.Test;
import cartas.*;
import estadoCarta.EstadoCarta;
import estadoCarta.EstadoCartaColocadaBocaAbajo;
import estadoCarta.EstadoCartaColocadaBocaArriba;
import factories.CartaMonstruoFactory;
import jugador.Jugador;
import modos.Modo;
import modos.ModoAtaque;
import modos.ModoDefensa;

public class CartaMonstruoTest {
	
	@Test
	public void test01ColocarCartaMonstruoEnModoAtaqueEstaEnModoAtaque() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable cartaMonstruo = fabrica.crearHeroeElementalAvian();
		Modo modoAtaque = new ModoAtaque();
		cartaMonstruo.cambiarA(modoAtaque);
		assertTrue(cartaMonstruo.estaEnModoAtaque());
	}
	
	@Test
	public void test02ColocarCartaMonstruoBocaAbajoEnModoDefensaNoEstaEnModoAtaque() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable cartaMonstruo = fabrica.crearHeroeElementalAvian();
		Modo modoDefensa = new ModoDefensa();
		cartaMonstruo.cambiarA(modoDefensa);
		assertFalse(cartaMonstruo.estaEnModoAtaque());
	}
	
	@Test
	public void test03MonstruoAtacaAOtroEnModoAtaqueConMenosAtaqueYLoDestruye() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable cartaMonstruo1 = fabrica.crearHeroeElementalAvian();
		Atacable cartaMonstruo2 = fabrica.crearHeroeElementalAvian();
		Modo modoAtaque = new ModoAtaque();
		cartaMonstruo1.cambiarA(modoAtaque);
		cartaMonstruo2.cambiarA(modoAtaque);
		cartaMonstruo1.atacar(cartaMonstruo2);
		
		assertTrue(cartaMonstruo2.estaDestruida());
	}
	
	@Test
	public void test04MonstruoAtacaAOtroEnModoAtaqueConMenosAtaqueYNoSeDestruyeLaCartaQueAtaca() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable cartaMonstruo1 = fabrica.crearDragonBlancoDeOjosAzules();
		Atacable cartaMonstruo2 = fabrica.crearHeroeElementalAvian();
		Modo modoAtaque = new ModoAtaque();
		cartaMonstruo1.cambiarA(modoAtaque);
		cartaMonstruo2.cambiarA(modoAtaque);
		cartaMonstruo1.atacar(cartaMonstruo2);
		
		assertFalse(cartaMonstruo1.estaDestruida());
	
	}
	
	@Test
	public void test05MonstruoAtacaAOtroMonstruoBocaArribaEnModoDefensaConMenosDefensaYLoDestruye() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable cartaMonstruo1 =fabrica.crearDragonBlancoDeOjosAzules();
		Atacable cartaMonstruo2 = fabrica.crearHeroeElementalAvian();
		Modo modoAtaque = new ModoAtaque();
		Modo modoDefensa = new ModoDefensa();
		cartaMonstruo1.cambiarA(modoAtaque);
		cartaMonstruo2.cambiarA(modoDefensa);
		cartaMonstruo1.atacar(cartaMonstruo2);
		
		
		assertTrue(cartaMonstruo2.estaDestruida());		
	}
	
	@Test
	public void test06MonstruoAtacaAOtroMonstruoBocaArribaEnModoDefensaConMasDefensaYNoLoDestruye() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable cartaMonstruo1 =fabrica.crearHeroeElementalAvian();
		Atacable cartaMonstruo2 = fabrica.crearDragonBlancoDeOjosAzules();
		Modo modoAtaque = new ModoAtaque();
		Modo modoDefensa = new ModoDefensa();
		cartaMonstruo1.cambiarA(modoAtaque);
		cartaMonstruo2.cambiarA(modoDefensa);
		cartaMonstruo1.atacar(cartaMonstruo2);
		
		assertFalse(cartaMonstruo2.estaDestruida());				
	}
	
	@Test
	public void test07MonstruoAtacaAOtroMonstruoBocaArribaEnModoDefensaConMasDefensaYNoSeDestruyeLaQueAtaco() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable cartaMonstruo1 =fabrica.crearHeroeElementalAvian();
		Atacable cartaMonstruo2 = fabrica.crearDragonBlancoDeOjosAzules();
		Modo modoAtaque = new ModoAtaque();
		Modo modoDefensa = new ModoDefensa();
		cartaMonstruo1.cambiarA(modoAtaque);
		cartaMonstruo2.cambiarA(modoDefensa);
		cartaMonstruo1.atacar(cartaMonstruo2);
		
		assertFalse(cartaMonstruo1.estaDestruida());					
	}
	
	
	@Test
	public void test08MonstruoAtacaAOtroMonstruoBocaAbajoEnModoDefensaConMenosDefensaYLoDestruye() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable cartaMonstruo1 =fabrica.crearDragonBlancoDeOjosAzules();
		Atacable cartaMonstruo2 = fabrica.crearHeroeElementalAvian();
		Modo modoAtaque = new ModoAtaque();
		Modo modoDefensa = new ModoDefensa();
		cartaMonstruo1.cambiarA(modoAtaque);
		cartaMonstruo2.cambiarA(modoDefensa);
		
		EstadoCarta bocaAbajo = new EstadoCartaColocadaBocaAbajo();
		cartaMonstruo2.ponerEn(bocaAbajo);
		cartaMonstruo1.atacar(cartaMonstruo2);
		assertTrue(cartaMonstruo2.estaDestruida());		
	}
	
	@Test
	public void test09MonstruoAtacaAOtroMonstruoBocaAbajoEnModoDefensaConMasDefensaYNoLoDestruye() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable cartaMonstruo1 =fabrica.crearHeroeElementalAvian();
		Atacable cartaMonstruo2 = fabrica.crearDragonBlancoDeOjosAzules();
		Modo modoAtaque = new ModoAtaque();
		Modo modoDefensa = new ModoDefensa();
		EstadoCarta bocaAbajo = new EstadoCartaColocadaBocaAbajo();
		cartaMonstruo2.ponerEn(bocaAbajo);
		cartaMonstruo1.cambiarA(modoAtaque);
		cartaMonstruo2.cambiarA(modoDefensa);
		cartaMonstruo1.atacar(cartaMonstruo2);
		
		assertFalse(cartaMonstruo2.estaDestruida());				
	}
	
	@Test
	public void test10MonstruoAtacaAOtroMonstruoBocaAbajoEnModoDefensaConMasDefensaYNoSeDestruyeLaQueAtaco() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable cartaMonstruo1 = fabrica.crearHeroeElementalAvian();
		Atacable cartaMonstruo2 = fabrica.crearDragonBlancoDeOjosAzules();
		Modo modoAtaque = new ModoAtaque();
		Modo modoDefensa = new ModoDefensa();
		EstadoCarta bocaAbajo = new EstadoCartaColocadaBocaAbajo();
		cartaMonstruo1.cambiarA(modoAtaque);
		cartaMonstruo2.cambiarA(modoDefensa);
		cartaMonstruo2.ponerEn(bocaAbajo);
		cartaMonstruo1.atacar(cartaMonstruo2);
		
		assertFalse(cartaMonstruo1.estaDestruida());					
	}
	
	@Test
	public void test11MonstruoAtacaAOtroMonstruoBocaAbajoEnModoDefensaConMasDefensaYLoDaVuelta() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable cartaMonstruo1 = fabrica.crearHeroeElementalAvian();
		Atacable cartaMonstruo2 = fabrica.crearDragonBlancoDeOjosAzules();
		Modo modoAtaque = new ModoAtaque();
		Modo modoDefensa = new ModoDefensa();
		EstadoCarta bocaAbajo = new EstadoCartaColocadaBocaAbajo();
		cartaMonstruo1.cambiarA(modoAtaque);
		cartaMonstruo2.cambiarA(modoDefensa);
		cartaMonstruo2.ponerEn(bocaAbajo);
		cartaMonstruo1.atacar(cartaMonstruo2);
		
		assertTrue(cartaMonstruo2.estaColocadaBocaArriba());					
	}
	
	@Test
	public void test12MonstruoAtacaAOtroMonstruoEnModoAtaqueConMismoAtaqueYLoDestruye() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable cartaMonstruo1 = fabrica.crearHeroeElementalAvian();
		Atacable cartaMonstruo2 = fabrica.crearHeroeElementalAvian();
		Modo modoAtaque = new ModoAtaque();
		cartaMonstruo1.cambiarA(modoAtaque);
		cartaMonstruo2.cambiarA(modoAtaque);
		cartaMonstruo1.atacar(cartaMonstruo2);
		
		assertTrue(cartaMonstruo2.estaDestruida());
	}
	
	@Test
	public void test13MonstruoAtacaAOtroMonstruoEnModoAtaqueConMismoAtaqueYSeDestruye() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable cartaMonstruo1 = fabrica.crearHeroeElementalAvian();
		Atacable cartaMonstruo2 = fabrica.crearHeroeElementalAvian();
		Modo modoAtaque = new ModoAtaque();
		cartaMonstruo1.cambiarA(modoAtaque);
		cartaMonstruo2.cambiarA(modoAtaque);
		cartaMonstruo1.atacar(cartaMonstruo2);
		
		assertTrue(cartaMonstruo1.estaDestruida());
	}
	
	@Test
	public void test14MonstruoAtacaAOtroMonstruoEnModoDefensaConMismoAtaqueQueDefensaYNoSeDestruye() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable cartaMonstruo1 = fabrica.crearHeroeElementalAvian();
		Atacable cartaMonstruo2 = fabrica.crearHeroeElementalAvian();
		Modo modoAtaque = new ModoAtaque();
		Modo modoDefensa = new ModoDefensa();
		cartaMonstruo1.cambiarA(modoAtaque);
		cartaMonstruo2.cambiarA(modoDefensa);
		cartaMonstruo1.atacar(cartaMonstruo2);
		assertFalse(cartaMonstruo1.estaDestruida());
	}
	@Test
	public void test15MonstruoAtacaAOtroMonstruoEnModoAtaqueLaDestruyeYDejaElDanioCorrespondiente() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable cartaMonstruo1 = fabrica.crearDragonBlancoDeOjosAzules();
		Atacable cartaMonstruo2 = fabrica.crearHeroeElementalAvian();
		Modo modoAtaque = new ModoAtaque();
		cartaMonstruo1.cambiarA(modoAtaque);
		cartaMonstruo2.cambiarA(modoAtaque);
		cartaMonstruo1.atacar(cartaMonstruo2);
		
		assertEquals(2000 ,cartaMonstruo2.obtenerDanioAlHaberSidoDestruida());
	}
	
	@Test
	public void test16MonstruoAtacaDirectamenteAJugador() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Jugador jugador = new Jugador();
		Atacable cartaMonstruo = fabrica.crearConejoOscuro();
		
		cartaMonstruo.atacar(jugador);
		
		int vidaEsperada = 8000 - 1100;
		assertTrue(jugador.tieneVida(vidaEsperada));
	
		
	}
	
	@Test
	public void test17Jinzo7AtacaDirectamenteAJugador() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable jinzo7 = fabrica.crearJinzo7();
		Jugador jugador = new Jugador();
		Atacable cartaMonstruoEnemiga = fabrica.crearConejoOscuro();
		
		EstadoCarta bocaArriba = new EstadoCartaColocadaBocaArriba();
		Modo modoAtaque = new ModoAtaque();
		
		jugador.colocar(cartaMonstruoEnemiga, bocaArriba, modoAtaque);
		jinzo7.atacar(jugador);
		
		int vidaEsperada = 8000 - 500;
		assertTrue(jugador.tieneVida(vidaEsperada));
	
	}
	
	@Test
	public void test18Jinzo7AtacaAOtraCartaMonstruoNormalmente() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Atacable jinzo7 = fabrica.crearJinzo7();
		Jugador jugadorPropio = new Jugador();
		Jugador jugadorEnemigo = new Jugador();
		Atacable cartaMonstruoEnemiga = fabrica.crearConejoOscuro();
		
		jugadorPropio.enfrentarseA(jugadorEnemigo);
		jugadorEnemigo.enfrentarseA(jugadorPropio);
		
		EstadoCarta bocaArriba = new EstadoCartaColocadaBocaArriba();
		Modo modoAtaque = new ModoAtaque();
		
		jugadorPropio.colocar(jinzo7, bocaArriba, modoAtaque);
		jugadorEnemigo.colocar(cartaMonstruoEnemiga, bocaArriba, modoAtaque);
		jugadorPropio.atacar(jinzo7, cartaMonstruoEnemiga);
		
		int vidaEsperada = 8000 - (1100 - 500);
		assertTrue(jugadorPropio.tieneVida(vidaEsperada));
		
	}
	
	@Test
	public void test19MonstruoAtacaAInsectoComeHombresBocaAbajoYSeDestruye() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Jugador jugador1 = new Jugador();
		Jugador jugador2 = new Jugador();
	
		jugador1.enfrentarseA(jugador2);
		jugador2.enfrentarseA(jugador1);
	
		Atacable insectoComeHombres = fabrica.crearInsectoComeHombres();
		Atacable damaArpia = fabrica.crearDamaArpia();
	
		EstadoCarta bocaAbajo = new EstadoCartaColocadaBocaAbajo();
		EstadoCarta bocaArriba = new EstadoCartaColocadaBocaArriba();
	
		jugador1.colocar(insectoComeHombres, bocaAbajo, new ModoDefensa() );
		jugador2.colocar(damaArpia, bocaArriba, new ModoAtaque() );
	
		jugador2.atacar(damaArpia ,insectoComeHombres);
	
		assertTrue(damaArpia.estaDestruida()); 
	}
	
	@Test
	public void test20MonstruoAtacaAInsectoComeHombresBocaAbajoYElInsectoSobrevive() {
		CartaMonstruoFactory fabrica = new CartaMonstruoFactory();
		Jugador jugador1 = new Jugador();
		Jugador jugador2 = new Jugador();
	
		jugador1.enfrentarseA(jugador2);
		jugador2.enfrentarseA(jugador1);
	
		Atacable insectoComeHombres = fabrica.crearInsectoComeHombres();
		Atacable damaArpia = fabrica.crearDamaArpia();
	
		EstadoCarta bocaAbajo = new EstadoCartaColocadaBocaAbajo();
		EstadoCarta bocaArriba = new EstadoCartaColocadaBocaArriba();
	
		jugador1.colocar(insectoComeHombres, bocaAbajo, new ModoDefensa() );
		jugador2.colocar(damaArpia, bocaArriba, new ModoAtaque() );
	
		jugador2.atacar(damaArpia ,insectoComeHombres);
	
		assertFalse(insectoComeHombres.estaDestruida()); 
	}
	
}