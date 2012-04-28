package info.bytecraft.exception;

public class WarpNotFoundException extends Exception {
	private static final long serialVersionUID = 9116100075747254298L;

	public WarpNotFoundException(String message){
		super(message);
	}

	public WarpNotFoundException(String message, Throwable ex){
		super(message, ex);
	}
}
