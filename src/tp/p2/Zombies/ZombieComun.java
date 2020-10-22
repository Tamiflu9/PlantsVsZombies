package tp.p2.Zombies;

import java.util.Random;

public class ZombieComun extends Zombie {
	
	private int contadorFR;
	private int frec = 2;
	public ZombieComun(int i, int j) {
		super(i, j, 5, 1, "zombieComun", "Z", 2, 0);
		contadorFR = frec;
	}

	public ZombieComun(int i, int j, Random seed) {
		super(i, j, 5, 1, "zombieComun", "Z", 2, 0);	
		contadorFR = frec;
	}
	
	public String toString() {
		String e = "Z [" + getResistencia() +"]";
		return e;
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

	@Override
	public void pinta() {
		toString();
	}
	
}
