package tp.p2.Command;

import java.util.Scanner;

import tp.p1.Game;

public class HelpCommand extends NoParamsCommand {

	public HelpCommand() {
		super("help","print this help message","Mustra como usar cada comando");
	}

	@Override
	public boolean execute(Game game, Scanner in) {
		StringBuilder h = new StringBuilder();
		String m = System.lineSeparator();
		h.append("Add <planta> <x> <y> : Adds a plant in position x, y. ").append(m);
		h.append("List: Prints the list of available plants.");
		h.append("Reset: Starts a new game. ").append(m);
		h.append("Help: Prints this help message").append(m);
		h.append("Exit: Terminates the program. ").append(m);
		h.append("[none]: Skips cycle.").append(m);
		h.toString();
		System.out.println(h);
		return false;
	}

}
