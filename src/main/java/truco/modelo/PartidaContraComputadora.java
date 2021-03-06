package truco.modelo;

import java.util.LinkedList;
import java.util.List;

public abstract class PartidaContraComputadora extends Partida {

	private ComputadoraAI computadora;
	private String nombreJugadorEnContra;
	
	public PartidaContraComputadora(String nombrePartida, String nombreJugador) {
		
		super(nombrePartida);
		this.nombreJugadorEnContra = nombreJugador;
		this.initialize(nombreJugador);
	}
	
	private void initialize(String nombreJugador) {
		
		Mesa mesaDeDos;
		
		List<Jugable> jugadores = new LinkedList<Jugable>();
		
		Equipo equipoJugador = new Equipo();
		Jugable nuevoJugador = new Jugador(nombreJugador);
		equipoJugador.agregarIntegrante(nuevoJugador);
		jugadores.add(nuevoJugador);
		
		Equipo equipoComputadora = new Equipo();
		this.computadora = new ComputadoraAI();
		equipoComputadora.agregarIntegrante(this.computadora);
		jugadores.add(this.computadora);
		
		mesaDeDos = new Mesa(jugadores);
		this.moderador = new Moderador(mesaDeDos);
		this.moderador.setPartida(this);
		this.moderador.setRotacionStrategy(new StrategyRotacionEnRonda(jugadores));
		
		nuevoJugador.setModerador(moderador);
		nuevoJugador.setMesa(moderador.getMesa());
		
		this.computadora.setModerador(moderador);
		this.computadora.setMesa(moderador.getMesa());
		
		for (Jugable jugador : jugadores) {
			this.jugadores.put(jugador.getNombre(), jugador);
		}
	}

	public void cantarEnvido(String jugadorQueCanta) {
		
		 super.cantarEnvido(jugadorQueCanta);
	     this.computadora.darRespuestaAEnvido();
	}
	
	public void cantarRealEnvido(String jugadorQueCanta) {
		
		 super.cantarRealEnvido(jugadorQueCanta);
	     this.computadora.darRespuestaARealEnvido();
	}
	
	public void cantarFaltaEnvido(String jugadorQueCanta) {
		
		 super.cantarFaltaEnvido(jugadorQueCanta);
	     this.computadora.darRespuestaAFaltaEnvido();
	}
	
	public void cantarTruco(String jugadorQueCanta) {
		
		 super.cantarTruco(jugadorQueCanta);
	     this.computadora.darRespuestaATruco();
	     super.aceptarTruco(this.computadora.getNombre());// la computadora siempre acepta
	}
	
	public void jugarCartaDeJugador(String nombreJugador, Carta carta) {
		
		super.jugarCartaDeJugador(nombreJugador, carta);
		this.computadora.darRespuestaATurno();
	}
	
	public void jugarPrimerCartaJugador(String unJugador){
		
		super.jugarPrimerCartaJugador(unJugador);
		this.computadora.darRespuestaATurno();
	}
	
	public void jugarSegundaCartaJugador(String unJugador){
	
    	super.jugarSegundaCartaJugador(unJugador);
    	this.computadora.darRespuestaATurno();
	}

	public void jugarTercerCartaJugador(String unJugador){

         super.jugarTercerCartaJugador(unJugador);
         this.computadora.darRespuestaATurno();
	}

	public ComputadoraAI getComputadora() {
		return this.computadora;
	}

	public String getNombreJugador() {
		return this.nombreJugadorEnContra;
	}
	
	@Override 
	public String getNombreJugadorConTurno() {
		return getNombreJugador();
	}
	
	@Override
	protected void verificarEstrategiaDeRotacion() {
		// No verifica ninguna estrategia ya que la ronda es la única que hay
	}

	@Override
	public boolean esContraComputadora() {
		return true;
	}
	
	@Override
	public void repartirCartas() {
		
		super.repartirCartas();
		
		if (this.computadora.equals(this.moderador.getJugadorQueTieneTurno())) {
			this.computadora.darRespuestaATurno();
		}
	}
}
