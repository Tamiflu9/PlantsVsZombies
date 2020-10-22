package tp.p2.Command;

import java.util.Scanner;

import tp.p1.Game;

public class AddZombieCommand extends NoParamsCommand {
	
	public AddZombieCommand() {
		super("addZombie", "Add zombie x y", "Añade un zombie aleatoriamente: Zombie <x> <y>");
	}

	@Override
	public boolean execute(Game game, Scanner in) {
		game.addZombie();
		return false;
	}

}
