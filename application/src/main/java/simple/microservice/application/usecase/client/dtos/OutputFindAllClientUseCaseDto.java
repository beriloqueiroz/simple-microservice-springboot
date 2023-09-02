package simple.microservice.application.usecase.client.dtos;

import org.springframework.data.domain.Page;

public record OutputFindAllClientUseCaseDto(
        Page<OutputFindClientUseCaseDto> result
) {

}
