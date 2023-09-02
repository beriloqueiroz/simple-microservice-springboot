package simple.microservice.infrastructure.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class OutputDefaultDto implements Serializable {
    @JsonProperty("isValid")
    public final boolean isValid;
    @JsonProperty("message")
    public final String message;

    public OutputDefaultDto(
            @JsonProperty("isValid") boolean isValid,
            @JsonProperty("message") String message
    ) {
        this.isValid = isValid;
        this.message = message;
    }

}
