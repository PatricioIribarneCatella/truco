package truco.modelo;

import java.util.LinkedList;
import java.util.List;

public class ManejadorEnvidos {
	
	private List<Jugable> jugadores;
	private List<Canto> envidosAcumulados;
	private Jugable ganador;
	
	public ManejadorEnvidos(){
		
		this.envidosAcumulados = new LinkedList<Canto>();
	}

	
	public void setJugadoresEnfrentados(List<Jugable> jugadoresEnfrentados){
		
		this.jugadores = jugadoresEnfrentados;
	}
	
	
	public void concatenarCanto(Canto unCanto){
		
		this.envidosAcumulados.add(unCanto);
	}
	
	public Jugable resolverEnvido(){
		
		LinkedList<Jugable> jugadoresConPuntajeMaximo = new LinkedList<Jugable>();
        int puntajeGanador  = this.getPuntajeGanador();
		
		for(Jugable unJugador:this.jugadores){
			
		   int puntajeJugador = Integer.parseInt(unJugador.declararPuntosEnvido());
		   if(puntajeJugador == puntajeGanador){
			   
			   jugadoresConPuntajeMaximo.add(unJugador);
		   }
		}
		
		this.ganador = jugadoresConPuntajeMaximo.getFirst();// el primero de la lista es el que esta en el equipo con el jugador mano 
        return this.ganador; 		
	}
	
	
	public void envidoNoQuerido(Equipo equipoQueRechaza){
		
	   for(Jugable unJugador: this.jugadores){
		   
		   if(unJugador.getEquipo() != equipoQueRechaza){
			   
			   this.ganador =  unJugador;
		   }
	   }
	}
	
	public Jugable getGanador(){
		
		return this.ganador;
	}
	/*private LinkedList<Jugable> getJugadoresConPuntajeMasAlto(){
		
		LinkedList<Jugable> jugadoresConPuntajeMaximo = new LinkedList<Jugable>();
        int puntajeGanador  = this.getPuntajeGanador();
		
		for(Jugable unJugador:this.jugadores){
			
		   int puntajeJugador = Integer.parseInt(unJugador.declararPuntosEnvido());
		   if(puntajeJugador == puntajeGanador){
			   
			   jugadoresConPuntajeMaximo.add(unJugador);
		   }
		}
		
		return jugadoresConPuntajeMaximo;
	}*/
	
	
   public int getPuntajeGanador(){
	   
	    int envidoDelJugador;
		int envidoMasAlto = -1; // -1 para que el primer jugador de la lista supere este numero siempre
		for(Jugable unJugador:this.jugadores){
			
			envidoDelJugador = Integer.parseInt(unJugador.declararPuntosEnvido());
			if(envidoDelJugador > envidoMasAlto){
				
				envidoMasAlto =  envidoDelJugador;
			}
		}
		return envidoMasAlto;
   }
   public int calcularPuntajeAcumulado(){
		
		int puntajeAcumulado = 0; 
		if(!this.envidosAcumulados.isEmpty()){
			
			for(Canto unCanto:this.envidosAcumulados){
				
				puntajeAcumulado += unCanto.getPuntosGanados(this.ganador.getEquipo());
			}
		}
		return puntajeAcumulado;
	}
	
	public int calcularPuntajeAcumuladoPorRechazo(){
		
		int puntajeAcumulado = 0; 
		if(!this.envidosAcumulados.isEmpty()){
			
			for(Canto unCanto:this.envidosAcumulados){
				
				puntajeAcumulado += unCanto.getPuntosPorRechazo();
			}
		}
		return puntajeAcumulado;
	}
	
	
	public void nuevaRonda(){
		
		this.ganador = null;
		this.envidosAcumulados.clear();
	}




}
