package simple.microservice.application.usecase.client.dtos;

import java.time.LocalDate;

public record OutputFindClientUseCaseDto (
        Long id,
        Long professionalId,
        String firstName,
        String surName,
        String email,
        String cpf,
        LocalDate birthDate) {

}