package algoritmosyprogramacion3.tp2.pruebasIntegracion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import algoritmosyprogramacion3.tp2.excepciones.AccionInvalidaException;
import algoritmosyprogramacion3.tp2.excepciones.TurnoParaTomarDecisionEquivocadoException;
import algoritmosyprogramacion3.tp2.modelo.Caballo;
import algoritmosyprogramacion3.tp2.modelo.Carta;
import algoritmosyprogramacion3.tp2.modelo.Cinco;
import algoritmosyprogramacion3.tp2.modelo.Cuatro;
import algoritmosyprogramacion3.tp2.modelo.Dos;
import algoritmosyprogramacion3.tp2.modelo.JuegoTruco;
import algoritmosyprogramacion3.tp2.modelo.Palo;
import algoritmosyprogramacion3.tp2.modelo.Seis;
import algoritmosyprogramacion3.tp2.modelo.SieteDeBasto;
import algoritmosyprogramacion3.tp2.modelo.SieteDeEspada;
import algoritmosyprogramacion3.tp2.modelo.Sota;
import algoritmosyprogramacion3.tp2.modelo.Tres;
import algoritmosyprogramacion3.tp2.modelo.UnoDeBasto;
import algoritmosyprogramacion3.tp2.modelo.UnoDeCopa;
import algoritmosyprogramacion3.tp2.modelo.UnoDeEspada;
import algoritmosyprogramacion3.tp2.modelo.UnoDeOro;

public class PartidaDeCuatroConFlorTest {

	private JuegoTruco juego;
	private List<String> jugadoresEquipo1;
	private List<String> jugadoresEquipo2;
		
	private void repartirCartas(List<Carta> cartas) {
		juego.repartirCartas(cartas);
	}
	
	@Before
	public void setUp() {
		
		this.jugadoresEquipo1 = new LinkedList<String>();
		this.jugadoresEquipo2 = new LinkedList<String>();
		this.jugadoresEquipo1.add("Juan");
		this.jugadoresEquipo1.add("Patricio");
		this.jugadoresEquipo2.add("Pedro");
		this.jugadoresEquipo2.add("Santiago");
		
		juego = new JuegoTruco();
		juego.nuevaMesaDeCuatroConFlor("mesa1",jugadoresEquipo1,jugadoresEquipo2);
	}
	
	@Test 
	public void testCantarFlorDeberiaSerPosible() {
		
		this.repartirCartas(Arrays.asList(new UnoDeBasto(), new Dos(Palo.BASTO), new Tres(Palo.BASTO),new Cuatro(Palo.BASTO), new Cinco(Palo.BASTO), new Seis(Palo.BASTO),new SieteDeBasto(), new Sota(Palo.BASTO), new Caballo(Palo.BASTO),new UnoDeOro(), new Dos(Palo.ORO), new Tres(Palo.ORO)));
		juego.cantarFlorPorJugador("Juan");
	}
	
	@Test (expected = TurnoParaTomarDecisionEquivocadoException.class)
	public void testMismoJugadorCantaTrucoYAceptaNoDeberiaSerPosible() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));
		Assert.assertTrue(juego.cantarTrucoPorJugador("Juan"));
		juego.aceptarTrucoPorJugador("Juan");
	}
	
	@Test (expected = TurnoParaTomarDecisionEquivocadoException.class)
	public void testMismoJugadorCantaEnvidoYAceptaNoDeberiaSerPosible() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));
		Assert.assertTrue(juego.cantarEnvidoPorJugador("Juan"));
		juego.aceptarVarianteEnvidoPorJugador("Juan");
	}
	
	@Test (expected = AccionInvalidaException.class)
	public void testNoDeberiaSerPosibleCantarReTrucoCuandoEmpiezaLaPartida() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		juego.cantarReTrucoPorJugador("Juan");
	}
	
	@Test (expected = AccionInvalidaException.class)
	public void testNoDeberiaSerPosibleCantarValeCuatroCuandoEmpiezaLaPartida() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		juego.cantarValeCuatroPorJugador("Juan");
	}
	
	@Test (expected = TurnoParaTomarDecisionEquivocadoException.class)
	public void testMismoJugadorCantaTrucoYReTruco() {
	
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		Assert.assertTrue(juego.cantarTrucoPorJugador("Juan"));
		juego.cantarReTrucoPorJugador("Juan");
	}
	
	@Test
	public void testSeCantaTrucoYNoSeLoQuiere() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		
		Assert.assertTrue(juego.cantarTrucoPorJugador("Juan"));
		Assert.assertTrue(juego.rechazarVarianteTrucoPorJugador("Pedro"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("1"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("1"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("0"));
	}
	
	@Test
	public void testSeCantaEnvidoNoSeLoQuiereSeCantaTrucoNoSeLoQuiere() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		
		Assert.assertTrue(juego.cantarEnvidoPorJugador("Juan"));
		Assert.assertTrue(juego.rechazarVarianteEnvidoPorJugador("Pedro"));
		
		Assert.assertTrue(juego.cantarTrucoPorJugador("Juan"));
		Assert.assertTrue(juego.rechazarVarianteTrucoPorJugador("Pedro"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("2"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("2"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("0"));
	}
	
	@Test
	public void testSeCantaTrucoYSeLoQuiere() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		
		Assert.assertTrue(juego.cantarTrucoPorJugador("Juan"));
		Assert.assertTrue(juego.aceptarTrucoPorJugador("Pedro"));
		
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Patricio"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Santiago"));
		
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Patricio"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Santiago")); // parda pero el equipo 1 hizo primera 
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("2"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("2"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("0"));
	}
	
	@Test
	public void testSeCantaTrucoSeCantaReTrucoSeLoQuiere() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		
		Assert.assertTrue(juego.cantarTrucoPorJugador("Juan"));
		Assert.assertTrue(juego.aceptarTrucoPorJugador("Pedro"));
		
		
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Patricio"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Santiago"));//empatan la primera
		
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Juan"));
		Assert.assertTrue(juego.cantarReTrucoPorJugador("Pedro"));
		Assert.assertTrue(juego.aceptarTrucoPorJugador("Patricio"));
		

		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Patricio"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Santiago"));//gana el equipo 1
		
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("3"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("3"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("0"));
	}
	
	@Test
	public void testSeCantaTrucoSeCantaReTrucoSeCantaValeCuatroSeLoQuiere() {

		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Pedro"));
		
		Assert.assertTrue(juego.cantarTrucoPorJugador("Patricio"));
		Assert.assertTrue(juego.cantarReTrucoPorJugador("Santiago"));
		Assert.assertTrue(juego.cantarValeCuatroPorJugador("Juan"));
		Assert.assertTrue(juego.aceptarValeCuatroPorJugador("Pedro"));
		
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Patricio"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Santiago"));//equipo 2 hace primera
		
		
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Patricio"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Santiago")); //equipo 2 gana ronda
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("4"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("4"));
	}
	
	@Test
	public void testSeCantaEnvidoSeLoAcepta() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Juan"));
		
		Assert.assertTrue(juego.cantarEnvidoPorJugador("Pedro"));
		Assert.assertTrue(juego.aceptarVarianteEnvidoPorJugador("Patricio"));
		
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("2"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("2"));
		
		Assert.assertTrue(juego.mostrarPuntosEnvido("Juan").equals("21"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Pedro").equals("28"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Patricio").equals("7"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Santiago").equals("24"));
	}
	
	@Test
	public void testSeCantaEnvidoDosVecesSeguidasSeLoAcepta() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		
		Assert.assertTrue(juego.cantarEnvidoPorJugador("Juan"));
		Assert.assertTrue(juego.cantarEnvidoPorJugador("Pedro"));
		Assert.assertTrue(juego.aceptarVarianteEnvidoPorJugador("Patricio"));
		
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("4"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("4"));
		
		Assert.assertTrue(juego.mostrarPuntosEnvido("Juan").equals("21"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Pedro").equals("28"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Patricio").equals("7"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Santiago").equals("24"));	
	}
	
	@Test
	public void testSeCantaEnvidoTresVecesSeguidasSeLoAcepta() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		
		Assert.assertTrue(juego.cantarEnvidoPorJugador("Juan"));
		Assert.assertTrue(juego.cantarEnvidoPorJugador("Pedro"));
		Assert.assertTrue(juego.cantarEnvidoPorJugador("Patricio"));
		Assert.assertTrue(juego.aceptarVarianteEnvidoPorJugador("Santiago"));
	
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("6"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("6"));
		
		Assert.assertTrue(juego.mostrarPuntosEnvido("Juan").equals("21"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Pedro").equals("28"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Patricio").equals("7"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Santiago").equals("24"));	
		
	}
	
	@Test
	public void testSeCantaRealEnvidoSeLoAcepta() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		
		Assert.assertTrue(juego.cantarRealEnvidoPorJugador("Juan"));
		Assert.assertTrue(juego.aceptarVarianteEnvidoPorJugador("Pedro"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("3"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("3"));
		
		Assert.assertTrue(juego.mostrarPuntosEnvido("Juan").equals("21"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Pedro").equals("28"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Patricio").equals("7"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Santiago").equals("24"));
	}
	
	@Test
	public void testSeCantaEnvidoTresVecesSeguidasYRealEnvidoSeLoAcepta() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		
		Assert.assertTrue(juego.cantarEnvidoPorJugador("Juan"));
		Assert.assertTrue(juego.cantarEnvidoPorJugador("Pedro"));
		Assert.assertTrue(juego.cantarEnvidoPorJugador("Patricio"));
		Assert.assertTrue(juego.cantarRealEnvidoPorJugador("Santiago"));
		Assert.assertTrue(juego.aceptarVarianteEnvidoPorJugador("Juan"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("9"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("9"));
		
		Assert.assertTrue(juego.mostrarPuntosEnvido("Juan").equals("21"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Pedro").equals("28"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Patricio").equals("7"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Santiago").equals("24"));
	}
	
	@Test
	public void testSeCantaFaltaEnvidoSeLoAcepta() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		
		Assert.assertTrue(juego.cantarFaltaEnvidoPorJugador("Juan"));
		Assert.assertTrue(juego.aceptarVarianteEnvidoPorJugador("Pedro"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("30"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("30"));
		
		Assert.assertTrue(juego.mostrarPuntosEnvido("Juan").equals("21"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Pedro").equals("28"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Patricio").equals("7"));
		Assert.assertTrue(juego.mostrarPuntosEnvido("Santiago").equals("24"));
	}
	
	@Test
	public void testSeCantaTrucoSeCantaFlorYNoSeAcepta() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		
		// Juan
		Assert.assertTrue(juego.cantarTrucoPorJugador("Juan"));
		Assert.assertTrue(juego.rechazarVarianteTrucoPorJugador("Pedro"));
		
		// Pedro
		juego.repartirCartas(Arrays.asList(new UnoDeBasto(), new Dos(Palo.BASTO), new Tres(Palo.BASTO),new Cuatro(Palo.BASTO), new Cinco(Palo.BASTO), new Seis(Palo.BASTO),new SieteDeBasto(), new Sota(Palo.BASTO), new Caballo(Palo.BASTO), new UnoDeOro(), new Dos(Palo.BASTO), new Tres(Palo.ORO)));
		Assert.assertTrue(juego.cantarFlorPorJugador("Pedro"));
		Assert.assertTrue(juego.rechazarFlorPorJugador("Patricio"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("1"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("3"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("1"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("3"));
	}
	
	@Test
	public void testSeCantaTrucoSeCantaFlorYSeAcepta() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		
		// Juan
		Assert.assertTrue(juego.cantarTrucoPorJugador("Juan"));
		Assert.assertTrue(juego.rechazarVarianteTrucoPorJugador("Pedro"));
		
		// Pedro
		juego.repartirCartas(Arrays.asList(new UnoDeBasto(), new Dos(Palo.BASTO), new Tres(Palo.BASTO),new Cuatro(Palo.BASTO), new Cinco(Palo.BASTO), new Seis(Palo.BASTO),new SieteDeBasto(), new Sota(Palo.BASTO), new Caballo(Palo.BASTO), new UnoDeOro(), new Dos(Palo.BASTO), new Tres(Palo.ORO)));
		Assert.assertTrue(juego.cantarFlorPorJugador("Pedro"));
		Assert.assertTrue(juego.aceptarFlorPorJugador("Patricio"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("7"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("7"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("0"));
	}
	
	@Test
	public void testPartidaCompleta() {
		
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		
		// Juan
		Assert.assertTrue(juego.cantarEnvidoPorJugador("Juan"));
		Assert.assertTrue(juego.rechazarVarianteEnvidoPorJugador("Pedro"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("1"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("1"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("0"));
		
		Assert.assertTrue(juego.cantarTrucoPorJugador("Juan"));
		Assert.assertTrue(juego.rechazarVarianteTrucoPorJugador("Pedro"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("2"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("0"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("2"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("0"));
		
		
		// Pedro
		juego.repartirCartas(Arrays.asList(new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA), new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA)));

		
		Assert.assertTrue(juego.cantarTrucoPorJugador("Pedro"));
		Assert.assertTrue(juego.rechazarVarianteTrucoPorJugador("Patricio"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("2"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("1"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("2"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("1"));
	
		// Patricio 
		juego.repartirCartas(Arrays.asList(new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA),new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA),new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto()));
		
		Assert.assertTrue(juego.cantarTrucoPorJugador("Patricio"));
		Assert.assertTrue(juego.cantarReTrucoPorJugador("Santiago"));
		Assert.assertTrue(juego.cantarValeCuatroPorJugador("Juan"));
		Assert.assertTrue(juego.aceptarValeCuatroPorJugador("Pedro"));
	
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Patricio"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Santiago"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Pedro"));
		
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Patricio"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Santiago"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Pedro"));
		
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Patricio"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Santiago"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Pedro")); // Empatan el equipo 2 hizo primera
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("2"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("5"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("2"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("5"));
		
		// Santiago
		juego.repartirCartas(Arrays.asList(new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA),new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA),new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(),new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA)));
		
		Assert.assertTrue(juego.cantarTrucoPorJugador("Santiago"));
		Assert.assertTrue(juego.aceptarTrucoPorJugador("Juan"));
		
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Santiago"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Patricio")); // equipo 2 hace primera
		

		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Santiago"));
		Assert.assertTrue(juego.cantarReTrucoPorJugador("Juan"));
		Assert.assertTrue(juego.aceptarReTrucoPorJugador("Pedro"));
		
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Patricio")); // equipo 1 hace segunda
		
		Assert.assertTrue(juego.cantarValeCuatroPorJugador("Pedro"));
		Assert.assertTrue(juego.aceptarValeCuatroPorJugador("Patricio"));
		
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Santiago"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Patricio")); // gana equipo 2
		
		
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("2"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("9"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("2"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("9"));
		
		// Juan
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));				
		Assert.assertTrue(juego.cantarEnvidoPorJugador("Juan"));
		Assert.assertTrue(juego.rechazarVarianteEnvidoPorJugador("Pedro"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("3"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("9"));
		
		Assert.assertTrue(juego.cantarTrucoPorJugador("Juan"));
		Assert.assertTrue(juego.rechazarVarianteTrucoPorJugador("Pedro"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("4"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("9"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("4"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("9"));
		
		// Pedro
		juego.repartirCartas(Arrays.asList(new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA), new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA)));
			
		Assert.assertTrue(juego.cantarTrucoPorJugador("Pedro"));
		Assert.assertTrue(juego.aceptarTrucoPorJugador("Patricio"));
		
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Patricio"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Santiago"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Juan"));// equipo 2 hace primera
		
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.cantarReTrucoPorJugador("Patricio"));
		Assert.assertTrue(juego.aceptarReTrucoPorJugador("Santiago"));
		Assert.assertTrue(juego.cantarValeCuatroPorJugador("Santiago"));
		Assert.assertTrue(juego.aceptarValeCuatroPorJugador("Juan"));
		
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Patricio"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Santiago"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Juan")); //equipo 2 hace segunda
		
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("4"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("13"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("4"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("13"));
		
		// Particio
		juego.repartirCartas(Arrays.asList(new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA),new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA),new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto()));
			
		Assert.assertTrue(juego.cantarEnvidoPorJugador("Patricio"));
		Assert.assertTrue(juego.rechazarVarianteEnvidoPorJugador("Santiago"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("5"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("5"));
		
		Assert.assertTrue(juego.cantarTrucoPorJugador("Patricio"));
		Assert.assertTrue(juego.rechazarVarianteTrucoPorJugador("Santiago"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("6"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("13"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("6"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("13"));
		
		// Santiago
		juego.repartirCartas(Arrays.asList(new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA),new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA),new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(),new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA)));
		
		Assert.assertTrue(juego.cantarTrucoPorJugador("Santiago"));
		Assert.assertTrue(juego.aceptarTrucoPorJugador("Juan"));
		
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Santiago"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Patricio")); // equipo 2 hace primera
		

		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Santiago"));
		Assert.assertTrue(juego.cantarReTrucoPorJugador("Juan"));
		Assert.assertTrue(juego.aceptarReTrucoPorJugador("Pedro"));
		
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Patricio")); // equipo 1 hace segunda
		
		Assert.assertTrue(juego.cantarValeCuatroPorJugador("Pedro"));
		Assert.assertTrue(juego.aceptarValeCuatroPorJugador("Patricio"));
		
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Santiago"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Patricio")); // gana equipo 2
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("6"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("17"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("6"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("17"));
		
		// Juan
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		
		Assert.assertTrue(juego.cantarTrucoPorJugador("Juan"));
		Assert.assertTrue(juego.cantarReTrucoPorJugador("Pedro"));
		Assert.assertTrue(juego.cantarValeCuatroPorJugador("Patricio"));
		Assert.assertTrue(juego.aceptarValeCuatroPorJugador("Santiago"));
		
		
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Patricio"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Santiago"));//equipo 1 hace primera
		
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Patricio"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Santiago"));//equipo 2 hace segunda
		
		
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Patricio"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Santiago")); // Empatan pero equipo 1 hizo primera
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("10"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("17"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("10"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("17"));
		
		// Pedro
		juego.repartirCartas(Arrays.asList(new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA), new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA)));
		
		Assert.assertTrue(juego.cantarEnvidoPorJugador("Pedro"));
		Assert.assertTrue(juego.rechazarVarianteEnvidoPorJugador("Patricio"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("18"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("18"));
		
		Assert.assertTrue(juego.cantarTrucoPorJugador("Pedro"));
		Assert.assertTrue(juego.rechazarVarianteTrucoPorJugador("Patricio"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("19"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("19"));
		
		// Patricio
		juego.repartirCartas(Arrays.asList(new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA),new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA),new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto()));
		
		Assert.assertTrue(juego.cantarEnvidoPorJugador("Patricio"));
		Assert.assertTrue(juego.rechazarVarianteEnvidoPorJugador("Santiago"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("11"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Patricio").equals("11"));
		
		Assert.assertTrue(juego.cantarTrucoPorJugador("Patricio"));
		Assert.assertTrue(juego.cantarReTrucoPorJugador("Santiago"));
		Assert.assertTrue(juego.cantarValeCuatroPorJugador("Juan"));
		Assert.assertTrue(juego.aceptarValeCuatroPorJugador("Pedro"));
		
		
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Patricio"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Santiago"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Pedro")); // equipo 2 hace primera
		
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Patricio"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Santiago"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Pedro")); // equipo 2 hace segunda
		
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("23"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("23"));
		
		// Santiago
		juego.repartirCartas(Arrays.asList(new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA),new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA),new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(),new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA)));
		
		Assert.assertTrue(juego.cantarTrucoPorJugador("Santiago"));
		Assert.assertTrue(juego.aceptarTrucoPorJugador("Juan"));
		
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Santiago"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Patricio")); // equipo 2 hace primera
		

		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Santiago"));
		Assert.assertTrue(juego.cantarReTrucoPorJugador("Juan"));
		Assert.assertTrue(juego.aceptarReTrucoPorJugador("Pedro"));
		
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarTercerCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Patricio")); // equipo 1 hace segunda
		
		Assert.assertTrue(juego.cantarValeCuatroPorJugador("Pedro"));
		Assert.assertTrue(juego.aceptarValeCuatroPorJugador("Patricio"));
		
		Assert.assertTrue(juego.jugarPrimerCartaDeJugador("Santiago"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Juan"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Pedro"));
		Assert.assertTrue(juego.jugarSegundaCartaDeJugador("Patricio")); // gana equipo 2
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Santiago").equals("27"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("27"));
		
		// Juan
		this.repartirCartas(Arrays.asList(new UnoDeEspada(), new Dos(Palo.BASTO), new Caballo(Palo.ESPADA), new UnoDeBasto(), new Dos(Palo.ORO), new SieteDeBasto(), new SieteDeEspada(), new UnoDeOro(), new Seis(Palo.COPA), new Tres(Palo.COPA), new UnoDeCopa(), new Cinco(Palo.ESPADA)));		
		Assert.assertTrue(juego.cantarFaltaEnvidoPorJugador("Juan"));
		Assert.assertTrue(juego.aceptarVarianteEnvidoPorJugador("Pedro"));
		
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Juan").equals("11"));
		Assert.assertTrue(juego.mostrarPuntosDeJugador("Pedro").equals("30"));
	}
}
