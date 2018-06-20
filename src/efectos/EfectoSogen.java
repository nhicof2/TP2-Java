package efectos;

import campo.Campo;
import cartas.Puntos;

public class EfectoSogen implements Efecto {

	@Override
	public void aplicarEfecto(Campo campoPropio, Campo campoEnemigo) {
		Puntos puntosDeAtaqueExtra = new Puntos(200);
		Puntos puntosDeDefensaExtra = new Puntos(500);
		campoPropio.aumentarDefensaMonstruosPorEfectoCampo(puntosDeDefensaExtra);
		campoEnemigo.aumentarAtaqueMonstruosPorEfectoCampo(puntosDeAtaqueExtra);
	}
	
}