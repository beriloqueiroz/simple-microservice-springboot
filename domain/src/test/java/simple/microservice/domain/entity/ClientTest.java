package simple.microservice.domain.entity;

import org.junit.Assert;
import org.junit.Test;
import simple.microservice.domain.shared.DomainException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientTest {

    @Test
    public void shouldToCreateValidPatient(){
        try {
            new Client(
                    1L,
                    "fulano",
                    "de tal",
                    "teste@gmail.com,",
                    "96667246008",
                    false,
                    LocalDate.now().minusYears(30),
                    1L
            );
        } catch (DomainException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void shouldErrorToCreatePatientWithInvalidCpf(){
        try {
            new Client(
                    1L,
                    "fulano",
                    "de tal",
                    "teste@gmail.com,",
                    "001",
                    false,
                    LocalDate.now().minusYears(30),
                    1L
            );
            Assert.fail();
        } catch (DomainException e) {
        }
    }

    @Test
    public void shouldErrorToCreatePatientWithoutProfessionalId(){
        try {
            new Client(
                    1L,
                    "fulano",
                    "de tal",
                    "teste@gmail.com,",
                    "001",
                    false,
                    LocalDate.now().minusYears(30),
                    null
            );
            Assert.fail();
        } catch (DomainException e) {
        }
    }

    @Test
    public void shouldBeErrorWhenEnableWithoutAddress(){
        try {
            Client client =new Client(
                    1L,
                    "fulano",
                    "de tal",
                    "teste@gmail.com,",
                    "001",
                    false,
                    LocalDate.now().minusYears(30),
                    1L
            );
            client.enable();
            Assert.fail();
        } catch (DomainException e) {
        }
    }

    @Test
    public void shouldBePopulatePayments() throws DomainException {
        LocalDate startDate = LocalDate.now();
        Client client = new Client(
                1L,
                "fulano",
                "de tal",
                "teste@gmail.com,",
                "96667246008",
                false,
                LocalDate.now().minusYears(30),
                1L
        );
        client.populatePayments(List.of(
                        new Payment(1L, startDate.plusDays(1), PaymentStatus.PAID, BigDecimal.valueOf(15)),
                        new Payment(2L, startDate.plusDays(2), PaymentStatus.PAID, BigDecimal.valueOf(2)),
                        new Payment(3L, startDate.plusDays(31), PaymentStatus.PAID, BigDecimal.valueOf(10)),
                        new Payment(3L, startDate.plusDays(2), PaymentStatus.CANCELLED, BigDecimal.valueOf(21))
                )
        );
        assertEquals(client.getPayments().size(), 4);
        assertTrue(client.getPayments().get(0).getId()==1L);
    }

    @Test
    public void shouldBeGetTotalPaymentPaidByPeriod() throws DomainException {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(30);
        Client client = new Client(
                1L,
                "fulano",
                "de tal",
                "teste@gmail.com,",
                "96667246008",
                false,
                LocalDate.now().minusYears(30),
                1L
        );
        client.populatePayments(List.of(
                new Payment(1L, startDate.plusDays(1), PaymentStatus.PAID, BigDecimal.valueOf(15)),
                new Payment(2L, startDate.plusDays(2), PaymentStatus.PAID, BigDecimal.valueOf(2)),
                new Payment(3L, startDate.plusDays(31), PaymentStatus.PAID, BigDecimal.valueOf(10)),
                new Payment(3L, startDate.plusDays(2), PaymentStatus.CANCELLED, BigDecimal.valueOf(21))
                )
        );
        BigDecimal  total = client.totalPaidByPeriod(startDate, endDate);

        assertEquals(BigDecimal.valueOf(17), total);
    }
}
