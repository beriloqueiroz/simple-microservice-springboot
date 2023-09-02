package simple.microservice.infrastructure.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record InputRemoveClientDto(
        @JsonProperty("clientId") Long clientId,
        @JsonProperty("professionalId") Long professionalId
        ) implements Serializable {

}
