package simple.microservice.application.usecase.client.dtos;

public record InputFindAllClientsUseCaseDto(
        Long professionalId,
        int pageSize,
        int pageIndex
){}