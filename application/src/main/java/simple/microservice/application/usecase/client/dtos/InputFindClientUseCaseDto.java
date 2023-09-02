package simple.microservice.application.usecase.client.dtos;

public record InputFindClientUseCaseDto(
        Long professionalId,
        Long clientId
){}