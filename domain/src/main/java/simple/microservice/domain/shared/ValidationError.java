package simple.microservice.domain.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public abstract class ValidationError {
	private String message;
}
