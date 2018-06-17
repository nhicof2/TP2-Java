package modos;

import cartas.Puntos;

public class ModoAtaque extends Modo {

	@Override
	public ModoAtaque colocarEnModoAtaque() {
		return this;
	}
	
	@Override
	public void asignarPuntosAtaque(Puntos puntosAtaque);
	
	@Override
	public void asignarPuntosDefensa(Puntos puntosDefensa);

	//Agregar algo que "Pemita atacar"
	//Esto deberia ser un atributo exclusivo de las cartas Monstruo y actuar solo cuando estan
	//invocadas
	
}
