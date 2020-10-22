package tp.p2.Command;

import tp.p3.exception.CommandParseException;

public abstract class NoParamsCommand extends Command{

	public NoParamsCommand(String commandName, String commandInfo, String helpInfo) {
		super(commandName, commandInfo, helpInfo);
	}

	@Override
	public Command parse (String [] commandWords) throws CommandParseException {
		if(commandWords[0].equalsIgnoreCase(this.commandName)) {
			if(commandWords.length > 1) {
				throw new CommandParseException(commandName + " command has no arguments");
			}else {
				return this;
			}
		}
		return null;
	}
}
