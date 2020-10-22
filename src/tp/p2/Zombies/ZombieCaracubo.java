package tp.p2.Zombies;

import java.util.Random;

public class ZombieCaracubo extends Zombie {
	private int contadorFR;
	private int frec = 4;
	public ZombieCaracubo(int i, int j) {
		super(i, j, 8, 1, "zombieCaracubo", "W", 4, 0);	
		contadorFR = frec;
	}
	
	public ZombieCaracubo(Random seed, int i, int j) {
		super(i, j, 8, 1, "zombieCaracubo", "W", 4, 0);	
		contadorFR = frec;
	}

	public String toString() {
		return "W" + "["+ getResistencia() + "]";
	}
	
	public boolean mov() {
		contadorFR--;
		if(contadorFR == 0) {
			contadorFR = frec;
			return true;
		}else {
			return false;
		}
	}
	
	public int getCont() {
		return this.contadorFR;
	}
	
	@Override
	public void pinta() {
		toString();
	}
}
