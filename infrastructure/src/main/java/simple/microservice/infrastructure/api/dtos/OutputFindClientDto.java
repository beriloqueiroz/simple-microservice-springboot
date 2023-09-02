package simple.microservice.infrastructure.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class OutputFindClientDto extends OutputDefaultDto {
    @JsonProperty("id")
    Long id;
    @JsonProperty("professionalId")
    Long professionalId;
    @JsonProperty("firstName")
    String firstName;
    @JsonProperty("surName")
    String surName;
    @JsonProperty("email")
    String email;
    @JsonProperty("cpf")
    String cpf;
    @JsonProperty("birthDate")
    LocalDate birthDate;

    public OutputFindClientDto(
            @JsonProperty("id") Long id,
            @JsonProperty("professionalId") Long professionalId,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("surName") String surName,
            @JsonProperty("email") String email,
            @JsonProperty("cpf") String cpf,
            @JsonProperty("birthDate") LocalDate birthDate,
            @JsonProperty("message") String message,
            @JsonProperty("isValid") boolean isValid
    ) {
        super(isValid, message);
        this.id = id;
        this.professionalId = professionalId;
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.birthDate = birthDate;
        this.cpf = cpf;
    }

    public OutputFindClientDto(
            @JsonProperty("message") String message,
            @JsonProperty("isValid") boolean isValid
    ){
        super(isValid, message);
    }
}