package tp.p1;

import java.util.Random;

import tp.p3.exception.CommandParseException;

public class ZombieManager {

	private int remZombies;
	private Level nivel;
	
	public ZombieManager(String level) throws CommandParseException {
		//this.remZombies = 0;
		actualizarNivel(level);
		remZombies = nivel.getNumeroZombis();
	}
	
	private void actualizarNivel(String level) {
		switch (level) {
		case "EASY":
			nivel = Level.EASY;
			break;
		case "HARD":
			nivel = Level.HARD;
			break;
		case "INSANE":
			nivel = Level.INSANE;
			break;
		}	
	}
	
	public int reset() {
		return nivel.getNumeroZombis();
	}
	
	public boolean isZombieAdded() { 
		Random aleatorio = new Random(System.currentTimeMillis());
		double result = aleatorio.nextDouble(); 
		
		if(remZombies > 0) {
			if(result < nivel.getFrecuencia()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean tipoZombie() {
		return false;
	}
	
	public void restar() {
		remZombies--;
	}
	
	public int getRemZombies() {
		return remZombies;
	}

	public void setRemZombies(int remZombies) {
		this.remZombies = remZombies;
	}

}
