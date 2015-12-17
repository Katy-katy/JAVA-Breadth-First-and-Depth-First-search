import java.lang.Exception;
public class StackFullException extends Exception {
	
	public StackFullException(String stackIsFull){
		super(stackIsFull);	
	}
}
