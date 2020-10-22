package tp.p2.Plants;

public class Peashooter extends Plant {
	
	public Peashooter(int i,int j) { 
		super(i, j, 3, 1, "peashooter", "P", 1, 50);
	}

	public String toString() {
		return "P" + "["+ getResistencia() + "]";
	}
	
	@Override
	public void pinta() {
		toString();
	}
}
