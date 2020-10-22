package tp.p2.Plants;

public class Petacereza extends Plant {  // explota 2 ciclos despues de haber sido plantada y hace daño a los zombies de su alrededor

	
	public Petacereza(int i, int j) {
		super(i, j, 2, 10, "petacereza", "C", 2, 50);
	}	

	
	public String toString() {
		return "C" + "["+ getResistencia() + "]";
	}
	
	@Override
	public void pinta() {
		toString();
	}

}
