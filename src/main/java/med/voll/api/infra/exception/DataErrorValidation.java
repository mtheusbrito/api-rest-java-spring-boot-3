package med.voll.api.infra.exception;

import org.springframework.validation.FieldError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DataErrorValidation {

	
	private String field;
	private String message;
	
	
	public DataErrorValidation(FieldError error) {
		this(error.getField(), error.getDefaultMessage());
	}
	
	
	
}
