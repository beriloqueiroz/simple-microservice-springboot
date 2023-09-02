package simple.microservice.application.usecase.client;

import simple.microservice.application.gateway.PaymentGateway;
import simple.microservice.application.gateway.PaymentGatewayException;
import simple.microservice.application.gateway.ProfessionalRepository;
import simple.microservice.application.usecase.UseCaseException;
import simple.microservice.application.usecase.client.dtos.ClientPaymentDto;
import simple.microservice.application.usecase.client.dtos.InputFindPaymentUseCaseDto;
import simple.microservice.application.usecase.client.dtos.OutputFindPaymentsUseCaseDto;
import simple.microservice.domain.entity.Client;
import simple.microservice.domain.entity.Payment;
import simple.microservice.domain.shared.DomainException;

import java.util.List;
import java.util.Optional;

public class FindPaymentsUseCase {
    private final ProfessionalRepository professionalRepository;
    private final PaymentGateway paymentGateway;

    public FindPaymentsUseCase(ProfessionalRepository professionalRepository, PaymentGateway paymentGateway) {
        this.professionalRepository = professionalRepository;
        this.paymentGateway = paymentGateway;
    }

    public OutputFindPaymentsUseCaseDto execute(InputFindPaymentUseCaseDto input) throws DomainException, UseCaseException, PaymentGatewayException {
        Optional<Client> client = professionalRepository.findClient(input.professionalId(), input.clientId());

        if (client.isEmpty()){
            throw new UseCaseException("cliente não encontrado");
        }

        client.get().populatePayments(paymentGateway.getClientPayments(client.get().getCpf(), input.startDate(), input.endDate()));

        List<Payment> payments = client.get().getPayments();

        List<ClientPaymentDto> paymentsDto = List.of();

        if (payments != null){
            paymentsDto = client.get().getPayments().stream().map(
                    payment -> new ClientPaymentDto(
                            payment.getStatus().toString(),
                            payment.getDate(),
                            payment.getValue()
                    )
            ).toList();
        }

        return new OutputFindPaymentsUseCaseDto(
                client.get().getId(),
                client.get().getProfessionalId(),
                paymentsDto,
                client.get().totalPaid()
        );
    }
}
