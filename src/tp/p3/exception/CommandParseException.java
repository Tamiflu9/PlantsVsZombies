package tp.p3.exception;

public class CommandParseException extends Exception {
	

	private static final long serialVersionUID = 1L;

	public CommandParseException(){
		super ("Unknown command. Use ’help’ to see the available commands");
	}
	
	public CommandParseException(String msg) {
		super (msg);
	}

	

}
