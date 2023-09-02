package simple.microservice.application.usecase.client;

import simple.microservice.application.gateway.ProfessionalRepository;
import simple.microservice.application.usecase.client.dtos.InputAddClientUseCaseDto;
import simple.microservice.domain.entity.Client;
import simple.microservice.domain.shared.DomainException;

public class AddClientUseCase {
    private final ProfessionalRepository professionalRepository;

    public AddClientUseCase(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    public void execute(InputAddClientUseCaseDto input) throws DomainException {
        //TODO verificar se cliente já existe (cpf ou email e professionalId)
        Client client = new Client(
                null,
                input.firstName(),
                input.surName(),
                input.email(),
                input.cpf(),
                true,
                input.birthDate(),
                input.professionalId()
        );

        professionalRepository.save(client);
    }
}
