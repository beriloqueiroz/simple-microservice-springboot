package simple.microservice.infrastructure.service.gateway;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import simple.microservice.application.gateway.PaymentGateway;
import simple.microservice.application.gateway.PaymentGatewayException;
import simple.microservice.domain.entity.Payment;
import simple.microservice.domain.entity.PaymentStatus;
import simple.microservice.domain.shared.DomainException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class FakePaymentGatewayWithResilience implements PaymentGateway {

    @Autowired @Qualifier("basic-payment-gateway")
    private PaymentGateway gateway;

    private List<Payment> getClientPaymentsFallback(Exception e) throws PaymentGatewayException {
        genericFallback(e);
        return null;
    }

    private void genericFallback(Exception e) throws PaymentGatewayException {
        throw new PaymentGatewayException("Resilience CircuitBreak: "+e.getMessage());
    }

    @Override
    @Retry(name = "default")
    @CircuitBreaker(name="default", fallbackMethod = "getClientPaymentsFallback")
    public List<Payment> getClientPayments(String document, LocalDate startDate, LocalDate endDate) throws PaymentGatewayException, DomainException {
        if (document.isEmpty()) throw new PaymentGatewayException("erro fake");
        return List.of(new Payment(1L, LocalDate.now(), PaymentStatus.PAID, BigDecimal.TEN));
    }
}
