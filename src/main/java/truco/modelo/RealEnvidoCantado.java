package truco.modelo;

public class RealEnvidoCantado implements EstadoPartida {

	@Override
	public boolean esValidoParaJugarCarta() {
		return false;
	}

	@Override
	public boolean esValidoParaCantarTruco() {
		return false;
	}

	@Override
	public boolean esValidoParaCantarReTruco() {
		return false;
	}

	@Override
	public boolean esValidoParaCantarValeCuatro() {
		return false;
	}

	@Override
	public boolean esValidoParaCantarEnvido() {
		return false;
	}

	@Override
	public boolean esValidoParaCantarRealEnvido() {
		return false;
	}

	@Override
	public boolean esValidoParaCantarFaltaEnvido() {
		return true;
	}

	@Override
	public boolean esValidoParaAceptar() {
		return true;
	}

	@Override
	public boolean esValidoParaRechazar() {
		return true;
	}

	@Override
	public boolean esValidoParaCantarFlor() {
		return true;
	}
}
