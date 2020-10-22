package tp.p2.Plants;

import tp.p2.GameObject;

public abstract class Plant extends GameObject {
	
	public Plant(int posX, int posY, int resistencia, int daño, String nombre, String simbolo, int frecuencia, int coste) {
		super(posX, posY, resistencia, daño, nombre,simbolo, frecuencia, coste);
	}

	public abstract void pinta();

}
