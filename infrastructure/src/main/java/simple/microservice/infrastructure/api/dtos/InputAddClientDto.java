package simple.microservice.infrastructure.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record InputAddClientDto(
                                @JsonProperty("email") String email,
                                @JsonProperty("firstName") String firstName,
                                @JsonProperty("surName") String surname,
                                @JsonProperty("cpf") String cpf,
                                @JsonProperty("birthdate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate birthDate) implements Serializable {

}
