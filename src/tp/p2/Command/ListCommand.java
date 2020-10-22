package tp.p2.Command;

import java.util.Scanner;

import tp.p1.Game;
import tp.p2.Plants.PlantFactory;

public class ListCommand extends NoParamsCommand {

	public ListCommand() {
		super("list","print the list of available plants","Muestra la lista de plantas disponibles");
	}

	@Override
	public boolean execute(Game game, Scanner in){
		System.out.println(PlantFactory.listOfAvilablePlants());
		return false;		
	}
}
