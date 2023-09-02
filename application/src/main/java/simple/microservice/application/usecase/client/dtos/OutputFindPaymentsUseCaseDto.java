package simple.microservice.application.usecase.client.dtos;

import java.math.BigDecimal;
import java.util.List;

public record OutputFindPaymentsUseCaseDto(
        Long clientId,
        Long professionalId,
        List<ClientPaymentDto> payments,
        BigDecimal totalPaid
) {

}
