package tp.p2.Command;

import java.io.IOException;

import tp.p3.LoadCommand;
import tp.p3.SaveCommand;
import tp.p3.exception.CommandParseException;
import tp.p3.exception.FileContentsException;

public class CommandParser {
	
	private static Command[] availableCommands = { 
			new AddCommand(), new HelpCommand(), 
			new ResetCommand(), new ExitCommand(),
			new ListCommand(), new PrintModeCommand(), 
			new UpdateCommand(), new SaveCommand(),
			new LoadCommand()};

	public static Command parseCommand(String[] commandWords) throws CommandParseException, IOException, FileContentsException {
		Command comando = null;
		for(Command c : availableCommands) {
			comando = c.parse(commandWords);
			if(comando != null) {
				return comando;
			}
		}
		throw new CommandParseException("Unknown command. Use ’help’ to see the available commands");
	}

	public static String commandHelp() {
		StringBuilder s = new StringBuilder();
		s.append("The available commands are: ").append(System.lineSeparator());

		for(Command c : availableCommands) {
			s.append(" ").append(c.helpText()).append(System.lineSeparator());
		}
		return s.toString();
	}

}
