package simple.microservice.application.usecase.professional.dtos;

public record InputFindProfessionalUseCaseDto(
        Long professionalId,
        int pageSize,
        int pageIndex
){}