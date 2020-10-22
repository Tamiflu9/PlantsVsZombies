package tp.p2.Zombies;

import java.util.Random;

public class ZombieDeportista extends Zombie{
	
	public ZombieDeportista(int i, int j) {
		super(i, j, 2, 1, "zombieDeportista", "X", 1, 0);
		
	}
	
	public ZombieDeportista(int i, int j, Random seed) {
		super(i,j, 2, 1, "zombieDeportista", "X", 1, 0);	
		
	}

	public String toString() {
		return "X" + "["+ getResistencia() + "]";
	}
	
	public boolean mov() {
		return true;
	}
	
	@Override
	public void pinta() {
		toString();
	}
}
