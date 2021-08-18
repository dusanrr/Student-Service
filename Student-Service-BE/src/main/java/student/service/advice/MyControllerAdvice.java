package student.service.advice;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler({EntityExistsException.class, EntityNotFoundException.class, RuntimeException.class})
	public ResponseEntity<String> handleEntityExistsException(Exception exception)
	{
		exception.printStackTrace();
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	/*@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException)
	{
		return new ResponseEntity<String>(entityNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException runtimeException)
	{
		return new ResponseEntity<String>(runtimeException.getMessage(), HttpStatus.BAD_REQUEST);
	}*/
}
