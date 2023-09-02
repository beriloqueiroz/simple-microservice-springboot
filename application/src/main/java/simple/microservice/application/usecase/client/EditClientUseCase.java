package simple.microservice.application.usecase.client;

import simple.microservice.application.gateway.ProfessionalRepository;
import simple.microservice.application.usecase.UseCaseException;
import simple.microservice.application.usecase.client.dtos.InputEditClientUseCaseDto;
import simple.microservice.domain.entity.Client;
import simple.microservice.domain.shared.DomainException;

import java.util.Optional;

public class EditClientUseCase {

    private final ProfessionalRepository professionalRepository;

    public EditClientUseCase(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    public void execute(InputEditClientUseCaseDto input) throws DomainException, UseCaseException {
        Optional<Client> client = professionalRepository.findClient(input.professionalId(), input.id());

        if (client.isEmpty()){
            throw new UseCaseException("cliente não encontrado");
        }

        client.get().setFirstName(input.firstName());
        client.get().setSurname(input.surName());
        client.get().setEmail(input.email());
        client.get().setBirthdate(input.birthDate());

        professionalRepository.save(client.get());
    }

}
