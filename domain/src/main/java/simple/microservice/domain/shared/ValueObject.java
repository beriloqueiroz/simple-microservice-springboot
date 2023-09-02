package simple.microservice.domain.shared;

public abstract class ValueObject {
    protected final ValidationErrors validationErrors;
    public abstract void validate();

    public ValueObject(){
        this.validationErrors = new ValidationErrors();
    }
}
