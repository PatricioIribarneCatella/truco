package algoritmosyprogramacion3.tp2.modelo;

public class SotaDeEspada extends Sota {

	public SotaDeEspada() {
		
		super();
		this.palo = new Espada();
		this.imagen = new Imagen("file:resources/imagenes/cartas/espada/diez-de-espada.jpg", 75, 150, false, true);
	}
}
