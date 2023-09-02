package simple.microservice.application.gateway;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import simple.microservice.domain.entity.Client;
import simple.microservice.domain.entity.Professional;
import simple.microservice.domain.shared.DomainException;

import java.util.Optional;

public interface ProfessionalRepository {

    void save(Professional professional);

    Optional<Professional> find(Long professionalId) throws DomainException;

    Optional<Client> findClient(Long professionalId, Long clientId) throws DomainException;

    Page<Client> findAllClients(Long professionalId, Pageable pageable) throws DomainException;

    void remove(Long professionalId, Long clientId);

    void save(Client client);
}
