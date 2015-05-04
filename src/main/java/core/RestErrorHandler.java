package core;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestErrorHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> processValidationError(MethodArgumentNotValidException e) {
		BindingResult result = e.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		
		String[] messages = fieldErrors.stream()
				.map(error -> error.getDefaultMessage())
				.toArray(String[]::new);
		
		String formattedmMessages = String.join("\n", messages);
		
		CustomError error = new CustomError(4, formattedmMessages);
		return new ResponseEntity<CustomError>(error, HttpStatus.BAD_REQUEST);
	} 
}
