package simple.microservice.application.usecase.client;

import org.springframework.data.domain.*;
import simple.microservice.application.gateway.ProfessionalRepository;
import simple.microservice.application.usecase.client.dtos.InputFindAllClientsUseCaseDto;
import simple.microservice.application.usecase.client.dtos.OutputFindAllClientUseCaseDto;
import simple.microservice.application.usecase.client.dtos.OutputFindClientUseCaseDto;
import simple.microservice.domain.entity.Client;
import simple.microservice.domain.shared.DomainException;

public class FindAllClientsUseCase {

    private final ProfessionalRepository professionalRepository;

    public FindAllClientsUseCase(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    public OutputFindAllClientUseCaseDto execute(InputFindAllClientsUseCaseDto input) throws DomainException {
        Page<Client> clients = professionalRepository.findAllClients(input.professionalId(), PageRequest.of(input.pageIndex(),input.pageSize()));
        return new OutputFindAllClientUseCaseDto(new PageImpl<>(
                clients.getContent().stream().map(
                        client -> new OutputFindClientUseCaseDto(
                                    client.getId(),
                                    client.getProfessionalId(),
                                    client.getFirstName(),
                                    client.getSurname(),
                                    client.getEmail(),
                                    client.getCpf(),
                                    client.getBirthdate()
                            )).toList()
        ));
    }

}
