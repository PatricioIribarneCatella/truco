package truco.manejadores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import truco.modelo.JuegoTruco;
import truco.vista.VistaJuegoDeTruco;
import truco.excepciones.TurnoParaTomarDecisionEquivocadoException;

public class BotonAceptarTrucoEventHandler implements EventHandler<ActionEvent> {

	private VistaJuegoDeTruco vista;

	public BotonAceptarTrucoEventHandler(VistaJuegoDeTruco vista) {
		this.vista = vista;
	}

	@Override
	public void handle(ActionEvent event) {
		
		JuegoTruco modelo = this.vista.getModelo();
		
		try {
			modelo.aceptarTrucoPorJugador(modelo.getNombreJugadorConDecisionTruco());
			this.vista.setMensajeInformacion("Truco aceptado");
			this.vista.graficarSituacionTrucoAceptado();
			
		} catch (TurnoParaTomarDecisionEquivocadoException ex) {
			
			this.vista.setMensajeInformacion("Ya ha aceptado el truco");
		}
	}
}
