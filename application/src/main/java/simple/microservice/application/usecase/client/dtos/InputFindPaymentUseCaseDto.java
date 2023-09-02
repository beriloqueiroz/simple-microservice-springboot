package simple.microservice.application.usecase.client.dtos;

import java.time.LocalDate;

public record InputFindPaymentUseCaseDto(
        Long professionalId,
        Long clientId,
        LocalDate startDate,
        LocalDate endDate
){}