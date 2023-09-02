package simple.microservice.infrastructure.configurations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import simple.microservice.application.gateway.PaymentGateway;
import simple.microservice.infrastructure.service.gateway.FakePaymentGateway;
import simple.microservice.infrastructure.service.gateway.FakePaymentGatewayWithResilience;

@Configuration
public class ServiceInjection {
    @Bean @Primary
    public PaymentGateway paymentGatewayWithResilience() {
        return new FakePaymentGatewayWithResilience();
    }

    @Bean @Qualifier("basic-payment-gateway")
    public PaymentGateway paymentGateway(){
        return new FakePaymentGateway();
    }

}
