package truco.vista;

import javafx.scene.Node;

import truco.manejadores.BotonFlorEventHandler;

public class BotonFlor extends BotonJuegoTruco {

	public BotonFlor() {
		this.setText("Flor");
	}

	public BotonFlor(String text) {
		super(text);
	}

	public BotonFlor(String text, Node graphic) {
		super(text, graphic);
	}

	@Override
	public void setVistaAlEventHandler(VistaJuegoDeTruco vista) {
		this.setOnAction(new BotonFlorEventHandler(vista));
	}
}
