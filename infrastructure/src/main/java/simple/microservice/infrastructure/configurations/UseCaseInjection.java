package simple.microservice.infrastructure.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simple.microservice.application.gateway.PaymentGateway;
import simple.microservice.application.gateway.ProfessionalRepository;
import simple.microservice.application.usecase.client.*;
import simple.microservice.application.usecase.professional.CreateProfessionalUseCase;
import simple.microservice.application.usecase.professional.FindProfessionalUseCase;

import java.util.Objects;

@Configuration
public class UseCaseInjection {

    private final PaymentGateway paymentGateway;
    private final ProfessionalRepository professionalRepository;

    public UseCaseInjection(
            PaymentGateway paymentGateway,
            ProfessionalRepository professionalRepository
    ){
       this.professionalRepository = Objects.requireNonNull(professionalRepository);
       this.paymentGateway = Objects.requireNonNull(paymentGateway);

    }

    @Bean
    public AddClientUseCase addClientUseCase(){
        return new AddClientUseCase(this.professionalRepository);
    }

    @Bean
    public EditClientUseCase editClientUseCase(){
        return new EditClientUseCase(this.professionalRepository);
    }

    @Bean
    public FindClientUseCase findClientUseCase(){
        return new FindClientUseCase(this.professionalRepository);
    }

    @Bean
    public FindAllClientsUseCase findAllClientsUseCase(){
        return new FindAllClientsUseCase(this.professionalRepository);
    }

    @Bean
    public FindPaymentsUseCase findPaymentsUseCase(){
        return new FindPaymentsUseCase(this.professionalRepository, this.paymentGateway);
    }

    @Bean
    public RemoveClientUseCase removeClientUseCase(){
        return new RemoveClientUseCase(this.professionalRepository);
    }

    @Bean
    public FindProfessionalUseCase findProfessionalUseCase(){
        return new FindProfessionalUseCase(this.professionalRepository);
    }

    @Bean
    public CreateProfessionalUseCase createProfessionalUseCase(){
        return new CreateProfessionalUseCase(this.professionalRepository);
    }
}
