package simple.microservice.domain.helpers.validation.error;


import simple.microservice.domain.shared.ValidationError;

public class DateValidationError extends ValidationError {
	public DateValidationError(String message) {
		super(message);
	}
}
