package truco.modelo;

public class Cinco extends Carta {

	public Cinco() {
		
		super();
		this.initialize();
	}
	
	public Cinco(Palo palo) {
		
		super();
		this.initialize();
		this.palo = palo;
	}
	
	private void initialize() {
		
		this.valorEnvido = 5;
		this.valorFlor = 5;
		this.valorComoString = "5";
	}
	
	@Override
	public Carta jugarContra(Carta carta) {
		return carta.jugarContra(this);
	}
	
	@Override
	public Carta jugarContra(Cuatro cuatro) {
		return this;
	}
	
	@Override
	public Carta jugarContra(Seis seis) {
		return seis;
	}
	
	@Override
	public Carta jugarContra(SieteFalso sieteFalso) {
		return sieteFalso;
	}
	
	@Override
	public Carta jugarContra(Sota sota) {
		return sota;
	}
	
	@Override
	public Carta jugarContra(Caballo caballo) {
		return caballo;
	}
	
	@Override
	public Carta jugarContra(Rey rey) {
		return rey;
	}
	
	@Override
	public Carta jugarContra(UnoFalso uno) {
		return uno;
	}
	
	@Override
	public Carta jugarContra(Dos dos) {
		return dos;
	}
	
	@Override
	public Carta jugarContra(Tres tres) {
		return tres;
	}
	
	@Override
	public Carta jugarContra(UnoDeEspada unoDeEspada) {
		return unoDeEspada;
	}
	
	@Override
	public Carta jugarContra(UnoDeBasto unoDeBasto) {
		return unoDeBasto;
	}
	
	@Override
	public Carta jugarContra(SieteDeEspada sieteDeEspada) {
		return sieteDeEspada;
	}
	
	@Override
	public Carta jugarContra(SieteDeOro sieteDeOro) {
		return sieteDeOro;
	}
	
	@Override
	public Carta jugarContra(Cinco cinco) {
		return this;
	}
}
