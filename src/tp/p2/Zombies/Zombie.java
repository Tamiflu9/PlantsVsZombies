package tp.p2.Zombies;

import tp.p2.GameObject;

public abstract class Zombie extends GameObject{
	public Zombie(int posX, int posY, int resistencia, int daño, String nombre, String simbolo, int velocidad, int coste) {
		super(posX, posY, resistencia, daño, nombre, simbolo, velocidad, coste);
	}

	public abstract void pinta();
	
}
