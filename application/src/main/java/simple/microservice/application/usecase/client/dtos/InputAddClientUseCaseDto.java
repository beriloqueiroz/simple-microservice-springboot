package simple.microservice.application.usecase.client.dtos;

import java.time.LocalDate;

public record  InputAddClientUseCaseDto(
               Long professionalId,
               String firstName,
               String surName,
               String email,
               String cpf,
               LocalDate birthDate) {
}
