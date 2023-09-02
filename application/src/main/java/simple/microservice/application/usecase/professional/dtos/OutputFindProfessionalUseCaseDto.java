package simple.microservice.application.usecase.professional.dtos;

import simple.microservice.application.usecase.client.dtos.OutputFindClientUseCaseDto;

import java.util.List;

public record OutputFindProfessionalUseCaseDto(
        Long professionalId,
        String cpf,
        String document,
        String email,
        String name,
        List<OutputFindClientUseCaseDto> clients
) {
}
