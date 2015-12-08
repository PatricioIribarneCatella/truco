package algoritmosyprogramacion3.tp2.vista;

import java.util.Set;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class VistaJuegoDeTrucoPorTurnos extends VistaJuegoDeTruco {

	public VistaJuegoDeTrucoPorTurnos(Vista vistaAnterior) {
		super(vistaAnterior);
	}

	@Override
	protected void setBotonTerminarTurno() {
		
		Button botonTerminarTurno = new Button("Terminar turno");
		botonTerminarTurno.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
		botonTerminarTurno.setTextFill(Color.WHITE);
		
		BackgroundFill fondoDeColorBotonTerminarTurno = new BackgroundFill(Color.RED, new CornerRadii(5), new Insets(0.0,0.0,0.0,0.0));
		botonTerminarTurno.setBackground(new Background(fondoDeColorBotonTerminarTurno));
		
		botonTerminarTurno.setOnMouseEntered(e -> {
			
			botonTerminarTurno.setScaleX(1.2);
			botonTerminarTurno.setScaleY(1.2);
		});
		
		botonTerminarTurno.setOnMouseExited(e -> {
			
			botonTerminarTurno.setScaleX(1);
			botonTerminarTurno.setScaleY(1);
		});
		
		this.contenedorBotones.getChildren().add(botonTerminarTurno);
	}

	@Override
	protected void setCaracteristicasAlContenedorInformacionJugadores() {
		
		Set<String> nombres = this.modelo.getNombresDeJugadores();
		
		for (String nombre : nombres) {
			
			Button botonNombreJugador = new Button("Información Jugador: " + nombre);
			
			botonNombreJugador.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
			botonNombreJugador.setTextFill(Color.WHITE);
			
			BackgroundFill fondoDeColorBotonInformacion = new BackgroundFill(Color.BROWN, new CornerRadii(5), new Insets(0.0,0.0,0.0,0.0));
			botonNombreJugador.setBackground(new Background(fondoDeColorBotonInformacion));
			
			botonNombreJugador.setOnMouseEntered(e -> {
				
				botonNombreJugador.setScaleX(1.2);
				botonNombreJugador.setScaleY(1.2);
			});
			
			botonNombreJugador.setOnMouseExited(e -> {
				
				botonNombreJugador.setScaleX(1);
				botonNombreJugador.setScaleY(1);
			});
			
			botonNombreJugador.setOnAction(e -> {
				
				VistaInformacionJugador vista = new VistaInformacionJugador(this.modelo, nombre);
				try {
					vista.start(new Stage());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			
			this.contenedorInformacionJugadores.getChildren().add(botonNombreJugador);
		}
	}
}
