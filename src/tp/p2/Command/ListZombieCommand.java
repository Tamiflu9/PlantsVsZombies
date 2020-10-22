package tp.p2.Command;

import java.util.Scanner;

import tp.p1.Game;
import tp.p2.Zombies.ZombieFactory;

public class ListZombieCommand extends NoParamsCommand {

	public ListZombieCommand() {
		super("zombieList","print the list of available zombies","Muestra la lista de zombies disponibles");
	}

	@Override
	public boolean execute(Game game, Scanner in) {
		System.out.println(ZombieFactory.listOfAvilableZombies());
		return false;
	}

}
