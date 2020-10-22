package tp.p1;

import java.io.IOException;
import java.util.Scanner;

import tp.p2.Command.Command;
import tp.p2.Command.CommandParser;
import tp.p2.Printer.DebugPrinter;
import tp.p2.Printer.GamePrinter;
import tp.p2.Printer.ReleasePrinter;
import tp.p3.exception.CommandExecuteException;
import tp.p3.exception.CommandParseException;
import tp.p3.exception.FileContentsException;


public class Controller {
	private Game game; 
	private Scanner in; 
	private GamePrinter gamePrinter;
	private boolean noPrint = false;
	private boolean q;

	public Controller (Game game, Scanner in) {
		super();
		this.game = game;
		this.in = in;
		this.q=false;
	}

	public void setNoPrintGameState() {

	}

	public void printGame() {
		if(!noPrint && !q) {  
			gamePrinter = new ReleasePrinter(this.game);
			gamePrinter.encodeGame(this.game);
			gamePrinter.printGame();
		}
		else if(!noPrint && q) {  
			gamePrinter = new DebugPrinter(this.game);
			gamePrinter.encodeGame(this.game);
			gamePrinter.printGame();
		}
	}

	public boolean getQ() {
		return this.q;
	}
	public void setQ(boolean a) {
		this.q=a;
	}

	public void run() {
		printGame();
		while (!game.isFinished()){ 
			if(game.isFinished()) {
				//printGame();
				//System.exit(0);
			}
			else {
				System.out.print("Command > ");

				try {
					String[] words = in.nextLine().trim().split ("\\s+"); 
					Command command = CommandParser.parseCommand(words); 
					if (command != null) { 
						try {
							if (command.execute(game, in)) {
								if (!command.getCommandName().equalsIgnoreCase("list") && !command.getCommandName().equalsIgnoreCase("l")  && !command.getCommandName().equalsIgnoreCase("help") && !command.getCommandName().equals("h")) {
									q = true;
									printGame();	
								}
							}
							else {
								if (!command.getCommandName().equalsIgnoreCase("list") && !command.getCommandName().equalsIgnoreCase("l")  && !command.getCommandName().equalsIgnoreCase("help") && !command.getCommandName().equals("h")) {
									q = false;
									printGame();
								}
							}
						} catch (FileContentsException e) {
							e.printStackTrace();
						} catch (CommandExecuteException e) {
							System.err.println(e.getMessage());
						}
					} 
				} catch (IOException e) {
					e.printStackTrace();
					
				} catch(CommandParseException e) {
					System.err.println(e.getMessage());
					
				} catch(NumberFormatException e) {
					System.err.println("Invalid argument for add command, number expected: [A]dd <plant> <x> <y>");
					
				} catch(FileContentsException e) {
					System.err.println(e.getMessage());
					
				}
			}
		}


	}




	/*
	 * 
	 *	*public void run() throws IOException {
	 *		printGame();
	 *		boolean exit = false;
	 *		
	 *		while (!game.isFinished() && !exit) { 
	 *			
	 *			noPrint = false;
	 *			System.out.print("Command > ");
	 *
	 *			try {
	 *				String[] words = in.nextLine().toLowerCase().trim().split(" ");
	 *				Command command = CommandParser.parseCommand(words);
	 *				if (command != null) { 
	 *					command.execute(game, in); 
	 *					if (!command.getCommandName().equalsIgnoreCase("list") && !command.getCommandName().equalsIgnoreCase("l")  && !command.getCommandName().equalsIgnoreCase("help") && !command.getCommandName().equals("h")) {
	 *						//game.update();
	 *						game.addZombie();
	 *						game.contadorCiclos();
	 *						printGame();
	 *					}	
	 *				}
	 *			}catch(CommandParseException e) {
	 *				System.out.println(e);
	 *			}catch(CommandExecuteException e){
	 *				System.out.println(e);
	 *			}
	 *		}
	 *	}
	 */

}
