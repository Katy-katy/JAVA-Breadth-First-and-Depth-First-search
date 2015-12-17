import java.lang.Exception;
public class StackEmptyException extends Exception {
	
	public StackEmptyException(String stakIsEmpty){
		super(stakIsEmpty);
	}

}
