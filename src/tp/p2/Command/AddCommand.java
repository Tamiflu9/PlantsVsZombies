package tp.p2.Command;

import java.util.Scanner;

import tp.p1.Game;
import tp.p1.Level;
import tp.p2.Plants.Plant;
import tp.p2.Plants.PlantFactory;
import tp.p2.Plants.TipoPlantas;
import tp.p3.exception.CommandExecuteException;
import tp.p3.exception.CommandParseException;

public class AddCommand extends Command {

	private TipoPlantas tipoP;
	private String plantName;
	private int x;
	private int y;
	
	public AddCommand() {
		super("add","Add plant x y","Añade una planta: Plant <x> <y>");
	}

	@Override
	public boolean execute(Game game, Scanner in) throws CommandExecuteException {
		Plant plant = PlantFactory.getPlant(this.plantName, x, y);
		if(plant != null) {
			game.addPlant(plant, this.x, this.y);
			game.update();
			game.addZombie();
			game.contadorCiclos();
		}
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException, NumberFormatException{
		if ((commandWords[0].toLowerCase().equals("a")||commandWords[0].toLowerCase().equals("add"))) {
			AddCommand add = new AddCommand();
			
			//for (TipoPlantas planta : TipoPlantas.values()) {
				//if (planta.name().equalsIgnoreCase(commandWords[1])) {
					//add.plantName = commandWords[1];
				//}
			//}
			
			if(commandWords[1].equalsIgnoreCase("sunflower") || commandWords[1].equalsIgnoreCase("s") || commandWords[1].equalsIgnoreCase("peashooter") || commandWords[1].equalsIgnoreCase("p") || commandWords[1].equalsIgnoreCase("nuez") || commandWords[1].equalsIgnoreCase("n") || commandWords[1].equalsIgnoreCase("petacereza") || commandWords[1].equalsIgnoreCase("c")) {
				add.plantName = commandWords[1];
			}else {
				throw new CommandParseException("Unknown plant name: " + commandWords[1]);
			}
			if(commandWords.length == 4) {
				if(Integer.parseInt(commandWords[2]) < 4 && Integer.parseInt(commandWords[3]) < 8) {
					add.x = Integer.parseInt(commandWords[2]);
					add.y = Integer.parseInt(commandWords[3]);
				}else {
					throw new CommandParseException("Failed to add " +commandWords[1]+": ("+commandWords[2]+", "+commandWords[3]+") is an invalid position");				
				}
			}else {
				throw new CommandParseException("Incorrect number of arguments for add command: [A]dd <plant> <x> <y>");
			}
			add.x = Integer.parseInt(commandWords[2]);
			add.y = Integer.parseInt(commandWords[3]);
			return add;
		}
		else {
			return null;
		}
	}

}
