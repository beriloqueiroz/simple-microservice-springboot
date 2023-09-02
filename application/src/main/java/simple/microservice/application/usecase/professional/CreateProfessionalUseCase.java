package simple.microservice.application.usecase.professional;

import simple.microservice.application.gateway.ProfessionalRepository;
import simple.microservice.application.usecase.professional.dtos.InputCreateProfessionalUseCaseDto;
import simple.microservice.domain.entity.Professional;
import simple.microservice.domain.shared.DomainException;

public class CreateProfessionalUseCase {

	private final ProfessionalRepository professionalRepository;

	public CreateProfessionalUseCase(ProfessionalRepository professionalRepository) {
		this.professionalRepository = professionalRepository;
	}

	public void execute(InputCreateProfessionalUseCaseDto input) throws DomainException {
		Professional professional = new Professional(
				null, input.cpf(), input.document(), input.email(), input.name(), null
		);

		professionalRepository.save(professional);
	}
}
