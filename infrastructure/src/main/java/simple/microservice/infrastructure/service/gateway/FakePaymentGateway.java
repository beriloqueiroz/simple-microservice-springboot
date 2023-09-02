package simple.microservice.infrastructure.service.gateway;

import simple.microservice.application.gateway.PaymentGateway;
import simple.microservice.application.gateway.PaymentGatewayException;
import simple.microservice.domain.entity.Payment;
import simple.microservice.domain.entity.PaymentStatus;
import simple.microservice.domain.shared.DomainException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FakePaymentGateway implements PaymentGateway {

    public FakePaymentGateway() {
    }

    @Override
    public List<Payment> getClientPayments(String document, LocalDate startDate, LocalDate endDate) throws PaymentGatewayException, DomainException {
        if (document.isBlank()){
            throw new PaymentGatewayException("erro exemplo");        }

        return List.of(
                new Payment(1L, startDate, PaymentStatus.PAID , BigDecimal.ONE),
                new Payment(2L, startDate, PaymentStatus.PAID ,BigDecimal.TEN),
                new Payment(3L, startDate, PaymentStatus.PAID ,BigDecimal.valueOf(152))
        );
    }
}
