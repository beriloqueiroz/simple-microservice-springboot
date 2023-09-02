package simple.microservice.application.usecase.client.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ClientPaymentDto(
        String status,
        LocalDate date,
        BigDecimal value
) {
}
