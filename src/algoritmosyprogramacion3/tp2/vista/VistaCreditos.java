package algoritmosyprogramacion3.tp2.vista;

import algoritmosyprogramacion3.tp2.manejadores.BotonVolverEventHandler;
import algoritmosyprogramacion3.tp2.modelo.JuegoTruco;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class VistaCreditos implements Vista {

	private JuegoTruco modelo;
	private Stage stage;
	private Scene escena;
	private VBox contenedor;
	private Button botonVolver;
	private Vista vistaAnterior;
	
	public VistaCreditos(Vista vistaAnterior) {
		
		this.vistaAnterior = vistaAnterior;
		this.modelo = vistaAnterior.getModelo();
		this.stage = vistaAnterior.getStage();
		this.initialize();
	}

	private void initialize() {
		
		this.contenedor = new VBox();
		
		this.setContenedorPrincipal();
		
		this.setImagenDeFondo();
		
		this.setCaracteristicasAlContenedorPrincipal();
		
		this.escena = new Scene(this.contenedor, 733, 540);
	}
	
	private void setContenedorPrincipal() {
		
		this.contenedor.setAlignment(Pos.CENTER);
		this.contenedor.setSpacing(16);
		this.contenedor.setPadding(new Insets(25));
	}

	private void setImagenDeFondo() {
		
		Image imagen = new Image("file:resources/imagenes/fondos/fondo-verde.jpg", 733, 540, false, true);
		
		BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		
		this.contenedor.setBackground(new Background(imagenDeFondo));
	}
	
	private void setCaracteristicasAlContenedorPrincipal() {
		
		Label etiquetaNombreVersion = new Label("FonTruco - v1.0");
		etiquetaNombreVersion.setFont(Font.font("Tahoma", FontWeight.NORMAL, 22));
		etiquetaNombreVersion.setTextFill(Color.WHITE);
		
		Label etiquetaAutores = new Label("Autores");
		etiquetaAutores.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		etiquetaAutores.setTextFill(Color.WHITE);
		
		BackgroundFill fondoDeColorAutores = new BackgroundFill(Color.RED, new CornerRadii(3), new Insets(0.0,0.0,0.0,0.0));
		etiquetaAutores.setBackground(new Background(fondoDeColorAutores));
		
		
		Label etiquetaSantiago = new Label("Santiago Augusto Pinto");
		etiquetaSantiago.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		etiquetaSantiago.setTextFill(Color.WHITE);
		
		Label etiquetaPatricio = new Label("Patricio Iribarne Catella");
		etiquetaPatricio.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		etiquetaPatricio.setTextFill(Color.WHITE);
		
		this.botonVolver = new Button("Volver");
		this.botonVolver.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
		this.botonVolver.setTextFill(Color.BLACK);
		
		BackgroundFill fondoDeColorVolver = new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5), new Insets(0.0,0.0,0.0,0.0));
		this.botonVolver.setBackground(new Background(fondoDeColorVolver));
		
		this.botonVolver.setOnAction(new BotonVolverEventHandler(this, this.vistaAnterior));
		
		this.contenedor.getChildren().addAll(etiquetaNombreVersion, etiquetaAutores, etiquetaSantiago, etiquetaPatricio, this.botonVolver);
	}
	
	@Override
	public void mostrar() {
		
		this.stage.setTitle("FonTruco");
		this.stage.setScene(this.escena);
		this.stage.show();
	}

	public Vista getVistaAnterior() {
		return this.vistaAnterior;
	}

	@Override
	public Stage getStage() {
		return this.stage;
	}

	@Override
	public JuegoTruco getModelo() {
		return this.modelo;
	}

	@Override
	public void setModelo(JuegoTruco modelo) {
		this.modelo = modelo;
	}
	
	public Button getBotonVolver() {
		return this.botonVolver;
	}
}
