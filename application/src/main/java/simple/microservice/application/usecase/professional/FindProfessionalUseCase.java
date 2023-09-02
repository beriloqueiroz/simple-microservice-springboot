package simple.microservice.application.usecase.professional;

import simple.microservice.application.gateway.ProfessionalRepository;
import simple.microservice.application.usecase.UseCaseException;
import simple.microservice.application.usecase.professional.dtos.InputFindProfessionalUseCaseDto;
import simple.microservice.application.usecase.client.dtos.OutputFindClientUseCaseDto;
import simple.microservice.application.usecase.professional.dtos.OutputFindProfessionalUseCaseDto;
import simple.microservice.domain.entity.Professional;
import simple.microservice.domain.shared.DomainException;

import java.util.Optional;

public class FindProfessionalUseCase {
    private final ProfessionalRepository professionalRepository;

    public FindProfessionalUseCase(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    public OutputFindProfessionalUseCaseDto execute(InputFindProfessionalUseCaseDto input) throws DomainException, UseCaseException {
        Optional<Professional> professional = professionalRepository.find(input.professionalId());

        if (professional.isEmpty()){
            throw new UseCaseException("Profissional não encontrado");
        }

        return new OutputFindProfessionalUseCaseDto(
             professional.get().getId(),
             professional.get().getCpf(),
                professional.get().getDocument(),
                professional.get().getEmail(),
                professional.get().getName(),
                professional.get().getClients().stream().map(
                        client -> new OutputFindClientUseCaseDto(
                                client.getId(),
                                client.getProfessionalId(),
                                client.getFirstName(),
                                client.getSurname(),
                                client.getEmail(),
                                client.getCpf(),
                                client.getBirthdate())).toList()
        );
    }
}
