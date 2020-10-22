package tp.p1;

import java.util.InputMismatchException;
import java.util.Scanner;

import tp.p3.exception.CommandParseException;

public class PlantsVsZombies {

	public static void main (String[] args) throws InputMismatchException, NumberFormatException, CommandParseException{		
		
		String nivel = " ";
		int seed = 0;
		
		try {
			if(args.length == 2) {
				nivel = args[0];
				Level.parse(nivel);
				try {
					seed = Integer.parseInt(args[1]);
				}catch(RuntimeException e) {
					System.err.println("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed: "+args[1]+"]: the seed must be a number");
					System.exit(0);
				}		
			}
			else {
				throw new CommandParseException("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]"); 
			}
		}catch(RuntimeException | CommandParseException e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}
		
		
		Game game = new Game(nivel, seed);
		
		Scanner sc = new Scanner(System.in);

		Controller controlador = new Controller(game, sc);
		controlador.run();
	}
}