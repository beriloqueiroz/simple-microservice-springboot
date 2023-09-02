package simple.microservice.application.usecase.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import simple.microservice.application.gateway.ProfessionalRepository;
import simple.microservice.application.usecase.UseCaseException;
import simple.microservice.application.usecase.client.FindClientUseCase;
import simple.microservice.application.usecase.client.dtos.InputFindClientUseCaseDto;
import simple.microservice.application.usecase.client.dtos.OutputFindClientUseCaseDto;
import simple.microservice.domain.entity.Client;
import simple.microservice.domain.shared.DomainException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FindClientUseCaseTest {

    @Mock
    ProfessionalRepository professionalRepository;

    @Test
    public void shouldBeExecute() throws DomainException, UseCaseException {
        FindClientUseCase findClientUseCase =  new FindClientUseCase(
                professionalRepository
        );

        when(professionalRepository.findClient(eq(1L), eq(1L))).thenReturn(
                Optional.of(new Client(
                        1L,
                        "fulano",
                        "de tal",
                        "teste@gmail.com,",
                        "96667246008",
                        false,
                        LocalDate.now().minusYears(30),
                        1L
                ))
        );

        OutputFindClientUseCaseDto output = findClientUseCase.execute(new InputFindClientUseCaseDto(1L,1L));

        assertTrue(1L==output.id());
        assertEquals("96667246008", output.cpf());

        verify(professionalRepository, times(1)).findClient(1L, 1L);
    }
}
