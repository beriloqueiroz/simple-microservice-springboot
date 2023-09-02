package simple.microservice.application.usecase.client;

import simple.microservice.application.gateway.ProfessionalRepository;
import simple.microservice.application.usecase.UseCaseException;
import simple.microservice.application.usecase.client.dtos.InputFindClientUseCaseDto;
import simple.microservice.application.usecase.client.dtos.OutputFindClientUseCaseDto;
import simple.microservice.domain.entity.Client;
import simple.microservice.domain.shared.DomainException;

import java.util.Optional;

public class FindClientUseCase {
    private final ProfessionalRepository professionalRepository;

    public FindClientUseCase(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    public OutputFindClientUseCaseDto execute(InputFindClientUseCaseDto input) throws DomainException, UseCaseException {
        Optional<Client> client = professionalRepository.findClient(input.professionalId(), input.clientId());

        if (client.isEmpty()){
            throw new UseCaseException("cliente não encontrado");
        }

        return new OutputFindClientUseCaseDto(
                client.get().getId(),
                client.get().getProfessionalId(),
                client.get().getFirstName(),
                client.get().getSurname(),
                client.get().getEmail(),
                client.get().getCpf(),
                client.get().getBirthdate()
        );
    }
}
