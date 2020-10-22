package tp.p3.exception;

public class CommandExecuteException extends Exception {
	

	private static final long serialVersionUID = 1L;

	public CommandExecuteException(){
		super (" Error en Execute ");
	}
	
	public CommandExecuteException(String msg) {
		super (msg);
	}

}
