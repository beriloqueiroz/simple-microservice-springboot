package simple.microservice.domain.helpers.validation.error;


import simple.microservice.domain.shared.ValidationError;

public class CpfValidationError extends ValidationError {
	public CpfValidationError(String message) {
		super(message);
	}
}
