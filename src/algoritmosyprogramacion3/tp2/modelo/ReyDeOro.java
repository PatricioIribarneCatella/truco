package algoritmosyprogramacion3.tp2.modelo;

public class ReyDeOro extends Rey {

	public ReyDeOro() {
		
		super();
		this.palo = new Oro();
		this.imagen = new Imagen("file:resources/imagenes/cartas/oro/doce-de-oro.jpg", 75, 150, false, true);
	}
}
