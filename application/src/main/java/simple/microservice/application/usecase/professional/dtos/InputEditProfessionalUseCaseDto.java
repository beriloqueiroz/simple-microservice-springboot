package simple.microservice.application.usecase.professional.dtos;

public record InputEditProfessionalUseCaseDto(
        Long id,
        String email,
        String name,
        String document
){}