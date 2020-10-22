package tp.p2.Plants;

public class Sunflower extends Plant {
	
	public Sunflower(int i, int j) {
		super(i, j, 1, 0, "sunflower", "S", 2, 20);
	}		
	
	public String toString() {
		return "S" + "["+ getResistencia() + "]";
	}

	@Override
	public void pinta() {
		toString();
	}




}
