package tp.p2.Command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import tp.p1.Game;
import tp.p3.exception.CommandExecuteException;
import tp.p3.exception.CommandParseException;
import tp.p3.exception.FileContentsException;

public abstract class Command {
	
	private String helpText;
	private String commandText;
	protected final String commandName;
	
	public Command(String commandText, String commandInfo, String helpInfo){
		this.commandText = commandInfo;
		helpText = helpInfo;
		String[] commandInfoWords = commandText.split(" ");
		commandName = commandInfoWords[0];
	}
	
	/*
	Some commands may generate an error in the execute or parse methods.
	In the absence of exceptions , they must the tell the controller not to print the board
	*/
	// invoca a un metodo de game

	public abstract boolean execute(Game game, Scanner in) throws CommandExecuteException, FileContentsException, FileNotFoundException, CommandParseException;
	
	public abstract Command parse(String[] commandWords) throws CommandParseException, IOException, FileContentsException;
	
	public String helpText(){
		return " " + commandText + ": " + helpText;
	}

	public String getCommandName() {
		return commandName;
	}
	
	



}
