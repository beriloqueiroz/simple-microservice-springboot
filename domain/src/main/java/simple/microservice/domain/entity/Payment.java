package simple.microservice.domain.entity;

import lombok.Getter;
import simple.microservice.domain.helpers.validation.error.GenericValidationError;
import simple.microservice.domain.shared.DomainException;
import simple.microservice.domain.shared.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class Payment extends Entity {

    private final PaymentStatus status;
    private final LocalDate date;
    private final BigDecimal value;

    public Payment(Long id, LocalDate date, PaymentStatus status, BigDecimal value) throws DomainException {
        super(id);
        this.date=date;
        this.status=status;
        this.value=value;
        this.validate();
        if (!this.validationErrors.isValid()){
            throw new DomainException(validationErrors.getAllErrorMessages());
        }
    }

    @Override
    public void validate() {
        if (value.equals(BigDecimal.ZERO)){
            this.validationErrors.addError(new GenericValidationError("O valor não pode ser zero"));
        }
    }
}
