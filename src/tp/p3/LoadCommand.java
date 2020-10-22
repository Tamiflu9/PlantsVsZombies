package tp.p3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import tp.p1.Game;
import tp.p2.Command.Command;
import tp.p3.exception.CommandExecuteException;
import tp.p3.exception.FileContentsException;

public class LoadCommand extends Command{

	private String nombreArchivo;

	public LoadCommand( ) {
		super("load", "load file", "Carga una partida de un txt");
	}

	@Override
	public boolean execute(Game game, Scanner in) throws FileContentsException, FileNotFoundException, CommandExecuteException {
		try {
			game.load(nombreArchivo);
		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Game successfully loaded from file < "+ nombreArchivo +" >");
		return false;
	}
	
	@Override
	public Command parse(String[] commandWords) throws FileContentsException {
		if(commandWords[0].equalsIgnoreCase("load") || commandWords[0].equalsIgnoreCase("l")){
			LoadCommand l = new LoadCommand();
			if(commandWords.length == 2){
				l.nombreArchivo = commandWords[1];
				return l;
			}
			else if (commandWords.length==1){
				throw new FileContentsException("Load must be followed by a filename.");
			}
			else {
				throw new FileContentsException("Invalid filename: the filename contains spaces.");
			}
		}
		return null ;
		
		
	}
}
