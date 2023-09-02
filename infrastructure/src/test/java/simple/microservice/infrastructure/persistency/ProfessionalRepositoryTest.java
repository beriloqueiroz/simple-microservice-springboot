package simple.microservice.infrastructure.persistency;

import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import simple.microservice.application.gateway.ProfessionalRepository;
import simple.microservice.domain.shared.DomainException;
import simple.microservice.infrastructure.persistency.jpa.ClientJpaRepository;
import simple.microservice.infrastructure.persistency.jpa.ProfessionalJpaRepository;

@ExtendWith(MockitoExtension.class)
public class ProfessionalRepositoryTest {

    private ProfessionalRepository repository;

    @Before
    public void init() throws DomainException {
        ProfessionalJpaRepository professionalJpaRepository = Mockito.mock(ProfessionalJpaRepository.class);
        ClientJpaRepository clientJpaRepository =Mockito.mock(ClientJpaRepository.class);
        //TODO whens
        repository = new ProfessionalRepositoryImpl(professionalJpaRepository, clientJpaRepository);
    }


    //TODO
}
