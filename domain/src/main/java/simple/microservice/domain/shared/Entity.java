package simple.microservice.domain.shared;

import jakarta.annotation.PostConstruct;
import lombok.Getter;

@Getter
public abstract class Entity {

    protected final Long id;
    protected final ValidationErrors validationErrors;

    public Entity(Long id) {
        this.id = id;
        this.validationErrors = new ValidationErrors();
    }

    public abstract void validate();

}
