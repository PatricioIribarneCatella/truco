package truco.vista;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import truco.modelo.Carta;
import truco.modelo.JuegoTruco;
import truco.utilitarios.GraficadorCartas;
import truco.utilitarios.Situacion;

public abstract class VistaJuegoDeTruco implements Vista {

	protected JuegoTruco modelo;
	protected Stage stage;
	protected Scene escena;
	protected BorderPane contenedor;
	private Label etiquetaDatos;
	private GraficadorCartas graficadorCartas;
	protected ContenedorCartasJugadas contenedorCartasJugadas;
	protected ContenedorInformacionJugadoresYMazo contenedorInformacionJugadores;
	protected ContenedorAccionesCantos contenedorBotones;
	protected ContenedorCartas contenedorCartas;
	private ContenedorInformacionJugadorActual contenedorSuperior;
	protected Vista vistaAnterior;
	private Situacion situacionActual;

	
	public VistaJuegoDeTruco(Vista vistaAnterior) {
		
		this.vistaAnterior = vistaAnterior;
		this.modelo = vistaAnterior.getModelo();
		this.stage = vistaAnterior.getStage();
		this.graficadorCartas = new GraficadorCartas();
		this.etiquetaDatos = new Label();
		this.initialize();
	}
	
	private void initialize() {
		
		this.contenedor = new BorderPane();
		
		this.setContenedorPrincipal();
		
		this.setImagenDeFondo();
		
		this.setCaracteristicasAlContenedorPrincipal();
		
		this.escena = new Scene(this.contenedor, 1300, 700);
	}
	
	private void setContenedorPrincipal() {
		
		this.contenedor.setPadding(new Insets(25));
	}

	private void setImagenDeFondo() {
		
		Image imagen = new Image("imagenes/fondos/fondo-verde.jpg", 1300, 700, false, true);
		
		BackgroundImage imagenDeFondo = new BackgroundImage(imagen,
															BackgroundRepeat.NO_REPEAT,
															BackgroundRepeat.NO_REPEAT,
															BackgroundPosition.DEFAULT,
															BackgroundSize.DEFAULT);

		this.contenedor.setBackground(new Background(imagenDeFondo));
	}
	
	public void setMensajeInformacion(String mensaje) {
		
		this.etiquetaDatos.setText(mensaje);
		this.etiquetaDatos.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
		this.etiquetaDatos.setTextFill(Color.web("#FFFFFF"));
	}
	
	protected Image getImagenCarta(Carta carta) {
		
		Imagen imagen = this.graficadorCartas.getImagen(carta);
		return new Image(imagen.getUrl(), imagen.getWidth(), imagen.getHeigth(), imagen.getPreserveRatio(), imagen.getSmooth());
	}
	
	private void setCaracteristicasAlContenedorCentro() {
		
		this.contenedorCartasJugadas = new ContenedorCartasJugadas(this.modelo);
		
		this.contenedor.setCenter(this.contenedorCartasJugadas);
	}
	
	protected abstract void setCaracteristicasAlContenedorInformacionJugadores();
	
	private void setCaracteristicasAlContenedorDerecho() {
		
		this.setCaracteristicasAlContenedorInformacionJugadores();
		
		this.contenedor.setRight(this.contenedorInformacionJugadores);
	}
	
	protected abstract void setCaracteristicasAlContenedorBotonesDeCantos();
	
	private void setCaracteristicasAlContenedorIzquierdo() {
		
		this.setCaracteristicasAlContenedorBotonesDeCantos();
		
		this.contenedor.setLeft(this.contenedorBotones);
	}

	private void setCaracteristicasAlContenedorSuperior() {
				
		this.contenedorSuperior = new ContenedorInformacionJugadorActual(this, this.modelo);
		this.contenedor.setTop(contenedorSuperior);
	}
	
	protected abstract void setCaracteristicasAlContenedorCartas();
	
	private void setCaracteristicasAlContenedorInferior() {
		
		this.setCaracteristicasAlContenedorCartas();
		
		VBox contenedorInferior = new VBox(this.etiquetaDatos, this.contenedorCartas);
		contenedorInferior.setSpacing(10);
		contenedorInferior.setPadding(new Insets(10));
		contenedorInferior.setAlignment(Pos.CENTER);
		this.contenedor.setBottom(contenedorInferior);
	}

	private void setCaracteristicasAlContenedorPrincipal() {
		
		this.setCaracteristicasAlContenedorSuperior();
		this.setCaracteristicasAlContenedorInferior();
		this.setCaracteristicasAlContenedorCentro();
		this.setCaracteristicasAlContenedorDerecho();
		this.setCaracteristicasAlContenedorIzquierdo();
	}

	@Override
	public void mostrar() {
		
		this.etiquetaDatos.setText("");
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

	public void setTextoNombreJugador(String nombreJugador) {
		
		this.contenedorSuperior.setTextoNombreJugador(nombreJugador);
	}

	public void setTextoPuntosJugador(String puntos) {
		
		this.contenedorSuperior.setTextoPuntosJugador(puntos);
	}
	
	public void graficarCartas(String nombreJugador, List<Carta> cartas) {
		
		this.contenedorCartas.graficarCartas(nombreJugador, cartas);
	}
	
	public void graficarCartaJugada(Carta carta, String nombreJugador) {
		
		this.contenedorCartasJugadas.graficarCartaJugada(carta, nombreJugador);
	}

	public void graficarSituacionTruco() {
		
		this.contenedorBotones.graficarSituacionTruco();
	}

	public void graficarSituacionReTruco() {
		
		this.contenedorBotones.graficarSituacionReTruco();
	}

	public void graficarSituacionValeCuatro() {
		
		this.contenedorBotones.graficarSituacionValeCuatro();
	}

	public void graficarSituacionFlor() {
		
		this.contenedorBotones.graficarSituacionFlor();
	}

	public void graficarSituacionEnvido() {
		
		this.contenedorBotones.graficarSituacionEnvido();
	}

	public void graficarSituacionFaltaEnvido() {
		
		this.contenedorBotones.graficarSituacionFaltaEnvido();
	}

	public void graficarSituacionRealEnvido() {
		
		this.contenedorBotones.graficarSituacionRealEnvido();
	}

	public void graficarSituacionTrucoAceptado() {
		
		this.contenedorBotones.graficarSituacionTrucoAceptado();
	}

	public void graficarSituacionReTrucoAceptado() {
		
		this.contenedorBotones.graficarSituacionReTrucoAceptado();
	}

	public void graficarSituacionValeCuatroAceptado() {
		
		this.contenedorBotones.graficarSituacionValeCuatroAceptado();
	}

	public void graficarSituacionEnvidoAceptada() {
		
		this.contenedorBotones.graficarSituacionEnvidoAceptada();
		
		VentanaInformacionEnvidoAceptado ventana = new VentanaInformacionEnvidoAceptado(this, this.modelo);
		
		ventana.mostrar();
	}

	public void graficarSituacionFaltaEnvidoAceptada() {
		
        this.contenedorBotones.graficarSituacionEnvidoAceptada();
		
		VentanaInformacionFaltaEnvidoAceptado ventana = new VentanaInformacionFaltaEnvidoAceptado(this, this.modelo);
		
		ventana.mostrar();
	}

	public void graficarSituacionRealEnvidoAceptada() {
	
		this.contenedorBotones.graficarSituacionEnvidoAceptada();
			
		VentanaInformacionRealEnvidoAceptado ventana = new VentanaInformacionRealEnvidoAceptado(this, this.modelo);
			
		ventana.mostrar();
	}

	public void graficarSituacionFlorAceptada() {
		
		this.contenedorBotones.graficarSituacionFlor();
			
		VentanaInformacionFlorAceptada ventana = new VentanaInformacionFlorAceptada(this, this.modelo);
			
		ventana.mostrar();
	}
	
	public void graficarSituacionFlorRechazada() {
		
		this.contenedorBotones.graficarSituacionFlor();
		
		VentanaInformacionFlorRechazada ventana = new VentanaInformacionFlorRechazada(this, this.modelo);
			
		ventana.mostrar();
	}

	public void graficarSituacionVarianteEnvidoRechazada() {
		
		this.contenedorBotones.graficarSituacionEnvidoAceptada();
			
		VentanaInformacionEnvidoRechazado ventana = new VentanaInformacionEnvidoRechazado(this, this.modelo);
			
		ventana.mostrar();
	}

	public void graficarSituacionVarianteTrucoRechazada() {
		
		this.contenedorBotones.graficarSituacionTruco();
			
		VentanaInformacionTrucoRechazado ventana = new VentanaInformacionTrucoRechazado(this, this.modelo);
			
		ventana.mostrar();
	}
	
	public void setSituacionActual(Situacion situacion) {
		this.situacionActual = situacion;
	}

	public Situacion getSituacionActual() {
		
		return this.situacionActual;
	}

	public void limpiarBotones() {
		
		this.contenedorBotones.limpiarBotones();
	}

	public void graficarBotones(VBox botones) {
		
		this.contenedorBotones.graficarBotones(botones);
	}

	public void cambiarTurno(String nombreJugador, VBox botones) {
		
		this.contenedorCartas.cambiarTurno(nombreJugador, botones);
	}

	public void limpiarCartasJugadas() {
		
		this.contenedorCartasJugadas.limmpiarCartas();
	}
}
