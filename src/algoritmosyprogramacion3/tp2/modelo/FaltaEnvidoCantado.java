package algoritmosyprogramacion3.tp2.modelo;

public class FaltaEnvidoCantado implements EstadoPartida {

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
		return false;
	}

	@Override
	public boolean esValidoParaAceptar() {
		return true;
	}

	@Override
	public boolean esValidoParaRechazar() {
		return true;
	}
}
