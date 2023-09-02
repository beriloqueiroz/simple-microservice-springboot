package simple.microservice.application.usecase.client;

import simple.microservice.application.gateway.ProfessionalRepository;

public class RemoveClientUseCase {
    private final ProfessionalRepository professionalRepository;

    public RemoveClientUseCase(ProfessionalRepository professionalRepository) {
        //TODO verificar se cliente existe
        this.professionalRepository = professionalRepository;
    }

    public void execute(Long professionalId, Long clientId) {
        professionalRepository.remove(professionalId, clientId);
    }
}
