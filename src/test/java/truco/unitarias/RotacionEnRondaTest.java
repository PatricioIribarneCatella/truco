package truco.unitarias;

import  org.junit.Assert;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import truco.modelo.Jugable;
import truco.modelo.Jugador;
import truco.modelo.RotacionStrategy;
import truco.modelo.StrategyRotacionEnRonda;

public class RotacionEnRondaTest {

	private Jugable jugador1;
	private Jugable jugador2;
	private Jugable jugador3;
	private Jugable jugador4;
	private Jugable jugador5;
	private Jugable jugador6;
	private LinkedList<Jugable> jugadores;
	private RotacionStrategy rotacionEnRonda;
	
	
	@Before
	public void setUp(){
		
		jugador1= new Jugador("1");
		jugador2 = new Jugador("2");
		jugador3= new Jugador("3");
		jugador4 = new Jugador("4");
		jugador5 = new Jugador("5");
		jugador6 = new Jugador("6");
		
		jugadores= new LinkedList<Jugable>();
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		jugadores.add(jugador3);
		jugadores.add(jugador4);
		jugadores.add(jugador5);
		jugadores.add(jugador6);
		rotacionEnRonda = new StrategyRotacionEnRonda(jugadores);
	}
	

	@Test
	public void testRotacionDeTurnosEnRonda(){
		
		Jugable jugadorConTurno =  jugador1;
		
		jugadorConTurno = this.rotacionEnRonda.getJugadorConTurno();
		Assert.assertTrue(jugadorConTurno == jugador1);
		
		jugadorConTurno = this.rotacionEnRonda.getJugadorConTurno();
		Assert.assertTrue(jugadorConTurno == jugador2);
		
		jugadorConTurno = this.rotacionEnRonda.getJugadorConTurno();
		Assert.assertTrue(jugadorConTurno == jugador3);
		
		jugadorConTurno = this.rotacionEnRonda.getJugadorConTurno();
		Assert.assertTrue(jugadorConTurno == jugador4);
		
		jugadorConTurno = this.rotacionEnRonda.getJugadorConTurno();
		Assert.assertTrue(jugadorConTurno == jugador5);
		
		jugadorConTurno = this.rotacionEnRonda.getJugadorConTurno();
		Assert.assertTrue(jugadorConTurno == jugador6);
		
		jugadorConTurno = this.rotacionEnRonda.getJugadorConTurno();
		Assert.assertTrue(jugadorConTurno == jugador1);
	}
	
	@Test
	public void testRotacionDeManoEnRonda(){
		
		Jugable jugadorMano = jugador1;
		
		for(int i = 0 ; i<6;i++){
			jugadorMano = this.rotacionEnRonda.getSiguienteJugadorMano();
		}
		
		Assert.assertTrue(jugadorMano == jugador1);
	}
	
}
