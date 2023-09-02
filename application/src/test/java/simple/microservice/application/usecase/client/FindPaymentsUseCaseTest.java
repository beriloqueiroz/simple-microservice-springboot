package simple.microservice.application.usecase.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import simple.microservice.application.gateway.PaymentGateway;
import simple.microservice.application.gateway.PaymentGatewayException;
import simple.microservice.application.gateway.ProfessionalRepository;
import simple.microservice.application.usecase.UseCaseException;
import simple.microservice.application.usecase.client.dtos.InputFindPaymentUseCaseDto;
import simple.microservice.application.usecase.client.dtos.OutputFindPaymentsUseCaseDto;
import simple.microservice.domain.entity.Client;
import simple.microservice.domain.entity.Payment;
import simple.microservice.domain.entity.PaymentStatus;
import simple.microservice.domain.shared.DomainException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FindPaymentsUseCaseTest {

    @Mock
    ProfessionalRepository professionalRepository;
    @Mock
    PaymentGateway paymentGateway;

    @Test
    public void shouldBeExecute() throws DomainException, UseCaseException, PaymentGatewayException {
        FindPaymentsUseCase findPaymentsUseCase =  new FindPaymentsUseCase(
                professionalRepository, paymentGateway
        );

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

        LocalDate start = LocalDate.now().minusYears(1);
        LocalDate now = LocalDate.now();

        client.populatePayments(List.of(
                new Payment(1L, start, PaymentStatus.PAID ,BigDecimal.ONE),
                new Payment(2L, start, PaymentStatus.PAID ,BigDecimal.TEN),
                new Payment(3L, start, PaymentStatus.PAID ,BigDecimal.valueOf(152))
        ));

        when(professionalRepository.findClient(eq(1L), eq(1L))).thenReturn(
                Optional.of(client)
        );

        when(paymentGateway.getClientPayments(eq("96667246008"),eq(start), eq(now))).thenReturn(client.getPayments());

        OutputFindPaymentsUseCaseDto output = findPaymentsUseCase.execute(new InputFindPaymentUseCaseDto(1L,1L, start, now));

        assertTrue(1L==output.professionalId());
        assertTrue(3==output.payments().size());
        assertTrue(output.payments().get(0).value().equals(BigDecimal.valueOf(1)));
        assertTrue(output.payments().get(1).value().equals(BigDecimal.valueOf(10)));
        assertTrue(output.payments().get(2).value().equals(BigDecimal.valueOf(152)));
        assertTrue(output.totalPaid().equals(BigDecimal.valueOf(163)));
        assertTrue(1L==output.clientId());


        verify(professionalRepository, times(1)).findClient(1L, 1L);
        verify(paymentGateway, times(1)).getClientPayments("96667246008", start, now);
    }

    //TODO teste de erro quando cliente não existe.

}
