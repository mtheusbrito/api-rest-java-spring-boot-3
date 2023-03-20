package med.voll.api.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionHandling {
	
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity notFoundException() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity badRequest(MethodArgumentNotValidException exception) {
		var errors = exception.getFieldErrors();
		
		return ResponseEntity.badRequest().body(errors.stream().map(ErrorDTO::new).toList());
		
	}
	
	
	private record ErrorDTO(String campo, String message) {
		
		
		public ErrorDTO(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
		
		
	}

}
