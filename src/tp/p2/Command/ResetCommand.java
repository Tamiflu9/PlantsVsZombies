package tp.p2.Command;

import java.util.Scanner;

import tp.p1.Game;

public class ResetCommand extends NoParamsCommand {

	public ResetCommand() {
		super("reset","resets game","Reinicia el juego");
	}

	@Override
	public boolean execute(Game game, Scanner in) {
		game.reset();
		return false;
	}

}
