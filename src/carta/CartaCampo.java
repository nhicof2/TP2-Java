package carta;

public class CartaCampo implements Utilizable{

	public void destruirCarta() {
		
	}

	public Boolean estaDestruida() {
		//return this.estado.estaEnCementerio();
		return false;
	}

	@Override
	public int obtenerDanioAlHaberSidoDestruida() {

		return 0;
	}


}
