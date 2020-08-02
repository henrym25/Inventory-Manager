package inventoryman;

public class UserFormatException extends Exception { //Exception for when input does not follow specified format
	public UserFormatException(String msg) {
		super(msg);
	}
}
