package student.service.exception;

public class ExistEntityException extends MyApplicationException 
{
	private static final long serialVersionUID = -6071822477276144800L;
	
	private final Object entity;

	public ExistEntityException(Object entity, String message) 
	{
		super(message);
		this.entity = entity;
	}

	public Object getEntity() 
	{
		return entity;
	}

}
