package simple.microservice.infrastructure.persistency;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import simple.microservice.application.gateway.ProfessionalRepository;
import simple.microservice.domain.entity.Client;
import simple.microservice.domain.entity.Professional;
import simple.microservice.domain.shared.DomainException;
import simple.microservice.infrastructure.persistency.jpa.ClientJpaRepository;
import simple.microservice.infrastructure.persistency.jpa.ProfessionalJpaRepository;

import java.util.Optional;

//TODO implementar métodos

@Setter
public class ProfessionalRepositoryImpl implements ProfessionalRepository {

    @Autowired
    ProfessionalJpaRepository professionalJPARepository;

    @Autowired
    ClientJpaRepository clientJPARepository;

    public ProfessionalRepositoryImpl(simple.microservice.infrastructure.persistency.jpa.ProfessionalJpaRepository professionalJPARepository, ClientJpaRepository clientJPARepository) {
        this.professionalJPARepository = professionalJPARepository;
        this.clientJPARepository = clientJPARepository;
    }

    public ProfessionalRepositoryImpl() {
    }


    @Override
    public void save(Professional professional) {

    }

    @Override
    public Optional<Professional> find(Long professionalId) throws DomainException {
        return Optional.empty();
    }

    @Override
    public Optional<Client> findClient(Long professionalId, Long clientId) throws DomainException {
        return Optional.empty();
    }

    @Override
    public Page<Client> findAllClients(Long professionalId, Pageable pageable) throws DomainException {
        return null;
    }

    @Override
    public void remove(Long professionalId, Long clientId) {

    }

    @Override
    public void save(Client client) {

    }
}
