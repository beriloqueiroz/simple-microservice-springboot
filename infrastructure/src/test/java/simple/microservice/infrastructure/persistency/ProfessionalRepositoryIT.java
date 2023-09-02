package simple.microservice.infrastructure.persistency;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import simple.microservice.infrastructure.persistency.jpa.ProfessionalJpaRepository;

public class ProfessionalRepositoryIT extends InMemoryRepository {

    @Autowired
    ProfessionalJpaRepository repository;

    @Test
    public void run(){

    }

   //TODO
}
