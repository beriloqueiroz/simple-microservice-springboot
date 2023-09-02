package simple.microservice.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import simple.microservice.domain.helpers.validation.error.CpfValidationError;
import simple.microservice.domain.helpers.validation.error.CpfValidator;
import simple.microservice.domain.helpers.validation.error.DateValidationError;
import simple.microservice.domain.helpers.validation.error.GenericValidationError;
import simple.microservice.domain.shared.DomainException;
import simple.microservice.domain.shared.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Getter
@Slf4j
public class Client extends Entity {

    private final int MIN_AGE = 5;

    public Client(
            Long id,
            String firstName,
            String surname,
            String email,
            String cpf,
            boolean isEnable,
            LocalDate birthdate,
            Long professionalId) throws DomainException {
        super(id);
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.cpf = cpf;
        this.isEnable = isEnable;
        this.birthdate = birthdate;
        this.professionalId = professionalId;
        this.validate();
        if (!this.validationErrors.isValid()){
            throw new DomainException(validationErrors.getAllErrorMessages());
        }
    }

    private final Long professionalId;

    @Setter
    private String firstName;

    @Setter
    private String surname;

    @Setter
    private String email;

    private final String cpf;

    private boolean isEnable;

    @Setter
    private LocalDate birthdate;

    private List<Payment> payments;

    @Override
    public void validate() {
        CpfValidator cpfValidator = new CpfValidator();
        if(cpf.isBlank() || !cpfValidator.isValid(cpf, null)) {
            this.validationErrors.addError(new CpfValidationError("Cpf é obrigatório"));
        }

        if (birthdate != null) {
            int age = Period.between(birthdate, LocalDate.now()).getYears();
            if (age < MIN_AGE) {
                String errMsg = String.format("A idade mínima  é %d", MIN_AGE);
                this.validationErrors.addError(new DateValidationError(errMsg));
            }
        }

        if (birthdate == null) {
            this.validationErrors.addError(new DateValidationError("Data de nascimento é obrigatória"));
        }

        if (professionalId == null) {
            this.validationErrors.addError(new GenericValidationError("Não tem profissional associado"));
        }
    }

    public void enable() throws DomainException {
        this.isEnable = true;
    }

    public void disable() {
        this.isEnable = false;
    }

    public BigDecimal totalPaidByPeriod(LocalDate startDate, LocalDate endDate) {
        return this.payments.stream()
                .filter(payment ->
                        payment.getStatus().equals(PaymentStatus.PAID)
                                && payment.getDate().isAfter(startDate)
                                && payment.getDate().isBefore(endDate))
                .map(payment -> payment.getValue())
                .reduce(BigDecimal.ZERO,(value, acc) -> value.add(acc));
    }

    public BigDecimal totalPaid() {
        return this.payments.stream()
                .filter(payment ->
                        payment.getStatus().equals(PaymentStatus.PAID))
                .map(payment -> payment.getValue())
                .reduce(BigDecimal.ZERO,(value, acc) -> value.add(acc));
    }

    public void populatePayments(List<Payment> payments){
        this.payments = payments;
    }
}
