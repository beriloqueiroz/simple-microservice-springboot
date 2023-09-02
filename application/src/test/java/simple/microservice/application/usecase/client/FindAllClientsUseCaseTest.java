package simple.microservice.application.usecase.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import simple.microservice.application.gateway.ProfessionalRepository;
import simple.microservice.application.usecase.UseCaseException;
import simple.microservice.application.usecase.client.FindAllClientsUseCase;
import simple.microservice.application.usecase.client.dtos.InputFindAllClientsUseCaseDto;
import simple.microservice.application.usecase.client.dtos.InputFindClientUseCaseDto;
import simple.microservice.application.usecase.client.dtos.OutputFindAllClientUseCaseDto;
import simple.microservice.application.usecase.client.dtos.OutputFindClientUseCaseDto;
import simple.microservice.domain.entity.Client;
import simple.microservice.domain.shared.DomainException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FindAllClientsUseCaseTest {

    @Mock
    ProfessionalRepository professionalRepository;

    @Test
    public void shouldBeExecute() throws DomainException, UseCaseException {
        FindAllClientsUseCase findAllClientsUseCase =  new FindAllClientsUseCase(
                professionalRepository
        );

        Client client1 = new Client(
                1L,
                "fulano 1",
                "de tal",
                "teste1@gmail.com,",
                "96667246008",
                false,
                LocalDate.now().minusYears(30),
                1L
        );
        Client client2 = new Client(
                2L,
                "fulano 2",
                "de tal",
                "teste2@gmail.com,",
                "54654457003",
                false,
                LocalDate.now().minusYears(30),
                1L
        );

        Page<Client> clientsP = new PageImpl<>(List.of(client1,client2));

        when(professionalRepository.findAllClients(eq(1L), eq(Pageable.ofSize(10)))).thenReturn(
                clientsP
        );

        OutputFindAllClientUseCaseDto output = findAllClientsUseCase.execute(new InputFindAllClientsUseCaseDto(1L,10, 0));

        assertTrue(1L==output.result().getContent().get(0).id());
        assertEquals("96667246008", output.result().getContent().get(0).cpf());
        assertEquals("54654457003", output.result().getContent().get(1).cpf());

        verify(professionalRepository, times(1)).findAllClients(eq(1L), eq(Pageable.ofSize(10)));
    }
}
