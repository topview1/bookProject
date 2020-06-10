package web.exception;

public class FindProductByIdException extends Exception {
	public FindProductByIdException() {
		super();
	}

	public FindProductByIdException(String message) {
		super(message);
	}
}
