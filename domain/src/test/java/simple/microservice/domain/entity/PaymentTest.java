package simple.microservice.domain.entity;

import org.junit.Assert;
import org.junit.Test;
import simple.microservice.domain.shared.DomainException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentTest {

    @Test
    public void shouldBeCreatePaidPayment() throws DomainException {
        new Payment(1L, LocalDate.now(), PaymentStatus.PAID, BigDecimal.TEN);
    }

    @Test
    public void shouldBeErrorWhenTryCreatePaymentWithZeroValue() {
        try {
            new Payment(1L, LocalDate.now(), PaymentStatus.PAID, BigDecimal.ZERO);
            Assert.fail();
        } catch (DomainException e){

        }
    }

    @Test
    public void shouldBeCreateCancelledPayment() throws DomainException {
        new Payment(1L, LocalDate.now(), PaymentStatus.CANCELLED, BigDecimal.TEN);
    }
}
