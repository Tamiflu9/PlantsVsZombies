package tp.p2.Plants;

public class Nuez extends Plant{ // sirve de barrera

	public Nuez(int i, int j) {
		super(i, j, 10, 0, "nuez", "N", 0, 50);
	}

	public String toString() {
		return "N" + "["+ getResistencia() + "]";
	}
	
	@Override
	public void pinta() {
		toString();
	}
	
}
