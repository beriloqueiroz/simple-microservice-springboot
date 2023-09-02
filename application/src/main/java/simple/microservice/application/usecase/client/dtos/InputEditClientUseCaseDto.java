package simple.microservice.application.usecase.client.dtos;

import java.time.LocalDate;

public record InputEditClientUseCaseDto(
        Long id,
        String firstName,
        String surName,
        String email,
        Long professionalId,
        LocalDate birthDate
){}