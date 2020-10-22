package tp.p2.Command;

import java.util.Scanner;

import tp.p1.Game;

public class ExitCommand extends NoParamsCommand {

	public ExitCommand() {
		super("exit","terminate the program","Finaliza el juego");
	}

	@Override
	public boolean execute(Game game,  Scanner in) {
		System.err.println("****** Game over!: User exit ******");
		System.exit(0);
		return false;
	}
}
