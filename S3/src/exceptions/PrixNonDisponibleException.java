package exceptions;

public class PrixNonDisponibleException extends Exception {

	private static final long serialVersionUID = 1L;

	public PrixNonDisponibleException() {
	}

	public PrixNonDisponibleException(String message) {
		super(message);
	}

}
