package simple.microservice.application.usecase.professional.dtos;

public record InputCreateProfessionalUseCaseDto(
        String email,
        String name,
        String cpf,
        String document
){}