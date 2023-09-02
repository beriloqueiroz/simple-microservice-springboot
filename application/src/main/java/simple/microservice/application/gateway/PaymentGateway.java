package simple.microservice.application.gateway;

import simple.microservice.domain.entity.Payment;
import simple.microservice.domain.shared.DomainException;

import java.time.LocalDate;
import java.util.List;

public interface PaymentGateway {
    List<Payment> getClientPayments(String document, LocalDate startDate, LocalDate endDate) throws PaymentGatewayException, DomainException;
}
