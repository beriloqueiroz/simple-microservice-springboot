package simple.microservice.domain.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationErrors {
	private final List<ValidationError> errors = new ArrayList<>();

	public boolean isValid() {
		return errors.size() == 0;
	}

	public void addError(ValidationError error) {
		errors.add(error);
	}

	public List<ValidationError> getErrors() {
		return errors;
	}

	public boolean containsError(Class errorClass) {
		return errors.stream().anyMatch(error -> error.getClass().equals(errorClass));
	}

	public ValidationError getError(Class errorClass) {
		return errors.stream()
				.filter(error -> error.getClass().equals(errorClass))
				.findAny().orElse(null);
	}

	public String getAllErrorMessages() {
		return errors.stream().map(ValidationError::getMessage).collect(Collectors.joining(","));
	}
}
