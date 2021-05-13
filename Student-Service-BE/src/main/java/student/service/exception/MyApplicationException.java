package student.service.exception;

public class MyApplicationException extends Exception 
{
	private static final long serialVersionUID = -4929181341187624090L;

	public MyApplicationException(String message) 
	{
		super(message);
	}
}
