package tp.p2.Command;

import java.util.Scanner;

import tp.p1.Game;
import tp.p3.exception.CommandParseException;

public class UpdateCommand extends Command {

	private String nmb;
	
	public UpdateCommand() {
		super("none","Update","Actualiza el juego en cada ciclo");
	}

	@Override
	public boolean execute(Game game, Scanner in) {
		game.update();
		game.addZombie();
		game.contadorCiclos();
		return false;
	}

	// parsear el none y cuando metes un enter
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if ((commandWords[0].toLowerCase().equals("n")||commandWords[0].toLowerCase().equals("none") || commandWords[0].toLowerCase().equals(""))) {
			if(commandWords.length > 1) {
				throw new CommandParseException(commandName + " command has no arguments");
			}else {
				UpdateCommand u = new UpdateCommand();
				this.nmb = commandWords[0];
				u.nmb = commandWords[0];
				return u;
			}
		}
		else {
			return null;
		}
	}
	
	

}
