package tp.p2.Command;

import java.util.Scanner;

import tp.p1.Controller;
import tp.p1.Game;
import tp.p3.exception.CommandParseException;

public class PrintModeCommand extends Command{

	private String modo;
	protected Controller controller;
	
	public PrintModeCommand() {
		super("print mode", "change print mode [Release|Debug]. ", "eliges una de las dos maneras de pintar el tablero");
	}

	@Override
	public boolean execute(Game game, Scanner in) throws CommandParseException {
		if (modo.equalsIgnoreCase("d") || modo.equalsIgnoreCase("debug")) {
			return true;
			
		}else if (modo.equalsIgnoreCase("r") || modo.equalsIgnoreCase("release")) {
			return false;
			
		}
		return false;
		
	}

	@Override
	public Command parse(String[] commandWords)  throws CommandParseException {
		if (commandWords[0].equalsIgnoreCase("p") || commandWords[0].equalsIgnoreCase("print") ) {
			PrintModeCommand pm = new PrintModeCommand();
			if(commandWords[1].equalsIgnoreCase("debug") || commandWords[1].equalsIgnoreCase("d") || commandWords[1].equalsIgnoreCase("release") || commandWords[1].equalsIgnoreCase("r")) {
				pm.modo = commandWords[1];
			}else {
				throw new CommandParseException("Unknown print mode: " + commandWords[1]);
			}		
			return pm;
		}
		return null;
	}

}
