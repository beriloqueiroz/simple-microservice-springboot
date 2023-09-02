package simple.microservice.domain.helpers.validation.error;


import simple.microservice.domain.shared.ValidationError;

public class GenericValidationError extends ValidationError {
	public GenericValidationError(String message) {
		super(message);
	}
}
