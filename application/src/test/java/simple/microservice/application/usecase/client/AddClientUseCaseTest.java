package simple.microservice.application.usecase.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import simple.microservice.application.gateway.ProfessionalRepository;
import simple.microservice.application.usecase.UseCaseException;
import simple.microservice.application.usecase.client.AddClientUseCase;
import simple.microservice.application.usecase.client.dtos.InputAddClientUseCaseDto;
import simple.microservice.domain.entity.Client;
import simple.microservice.domain.shared.DomainException;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddClientUseCaseTest {

    @Mock
    ProfessionalRepository professionalRepository;

    @Test
    public void shouldBeExecute() throws DomainException, UseCaseException {
        AddClientUseCase addClientUseCase =  new AddClientUseCase(
                professionalRepository
        );

        addClientUseCase.execute(new InputAddClientUseCaseDto(
                1L,
                "fulano",
                "de tal",
                "teste@gmail.com",
                "96667246008",
                LocalDate.now().minusYears(20)
        ));

        verify(professionalRepository, times(1)).save(any(Client.class));
    }

    //TODO teste de erro quando cpf ou email já existe para o mesmo profissional
}
