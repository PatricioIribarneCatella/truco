package algoritmosyprogramacion3.tp2.modelo;

public class EnMesa implements EstadoDeCarta {

	@Override
	public boolean esValidoParaSerJugada() {
		return false;
	}

	@Override
	public EstadoDeCarta proximoEstado() {
		return new EnMazo();
	}
}
