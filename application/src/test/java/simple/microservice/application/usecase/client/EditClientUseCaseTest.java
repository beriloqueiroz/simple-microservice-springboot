package simple.microservice.application.usecase.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import simple.microservice.application.gateway.ProfessionalRepository;
import simple.microservice.application.usecase.UseCaseException;
import simple.microservice.application.usecase.client.dtos.InputAddClientUseCaseDto;
import simple.microservice.application.usecase.client.dtos.InputEditClientUseCaseDto;
import simple.microservice.domain.entity.Client;
import simple.microservice.domain.shared.DomainException;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EditClientUseCaseTest {

    @Mock
    ProfessionalRepository professionalRepository;

    @Test
    public void shouldBeExecute() throws DomainException, UseCaseException {
        EditClientUseCase editClientUseCase =  new EditClientUseCase(
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

        editClientUseCase.execute(new InputEditClientUseCaseDto(
                1L,
                "fulano",
                "de tal",
                "teste@gmail.com",
                1L,
                LocalDate.now().minusYears(20)
        ));

        verify(professionalRepository, times(1)).save(any(Client.class));
    }

    //TODO teste para quando cliente não existe deve ocorrer erro
}
