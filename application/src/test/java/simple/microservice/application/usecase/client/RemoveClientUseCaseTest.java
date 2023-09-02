package simple.microservice.application.usecase.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import simple.microservice.application.gateway.ProfessionalRepository;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RemoveClientUseCaseTest {

    @Mock
    ProfessionalRepository professionalRepository;

    @Test
    public void shouldBeExecute() {
        //TODO incluir mock do find
        RemoveClientUseCase removeClientUseCase = new RemoveClientUseCase(professionalRepository);

        removeClientUseCase.execute(1L, 1L);

        verify(professionalRepository, times(1)).remove(1L, 1L);
    }

    //TODO teste para caso de cliente que não existe
}
