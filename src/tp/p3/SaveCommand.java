package tp.p3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import tp.p1.Game;
import tp.p2.Command.Command;
import tp.p3.exception.FileContentsException;

public class SaveCommand extends Command{

	private String nombreArchivo;
	
	public SaveCommand( ) {
		super("save", "Save file", "Guarda una partida en un .dat");
	}

	@Override
	public boolean execute(Game game, Scanner in) throws FileContentsException, FileNotFoundException{
		String name;
		name = confirmedFileNameStringForWrite (nombreArchivo, in);
		try {
			game.store(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Game successfully saved in file < " + nombreArchivo +" > Use the load command to reload it.");
		return false;
	}
	
	public Command parse(String[] commandWords) throws FileContentsException {		
		if(commandWords[0].equalsIgnoreCase("save") || commandWords[0].equalsIgnoreCase("s")){
			SaveCommand s = new SaveCommand();
			if(commandWords.length == 2){
				s.nombreArchivo = commandWords[1];
				return s;
			}
			else if (commandWords.length==1){
				throw new FileContentsException("Save must be followed by a filename.");
			}
			else {
				throw new FileContentsException("Invalid filename: the filename contains spaces.");
			}
		}
		return null;
	}
	
	private String confirmedFileNameStringForWrite(String filenameString, Scanner in) { 
		String loadName = filenameString;
		boolean filename_confirmed = false;
		while (!filename_confirmed) { 
			if (MyStringUtils.isValidFilename(loadName)) { 
				File file = new File(loadName); 
				if (!file.exists()) {
					filename_confirmed = true; 
				}
				else { 
					loadName = filenameString; 
					filename_confirmed = true;
				} 
			} 
			else {
				System.out.println("Invalid filename: the filename contains invalid characters"); 
			} 
		} 
		return loadName; 
	}
	
}
