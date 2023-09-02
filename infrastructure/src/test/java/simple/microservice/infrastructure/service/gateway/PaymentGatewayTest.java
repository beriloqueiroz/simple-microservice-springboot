package simple.microservice.infrastructure.service.gateway;

import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import simple.microservice.application.gateway.PaymentGateway;

@ExtendWith(MockitoExtension.class)
public class PaymentGatewayTest {
    private PaymentGateway paymentGateway;

    @Before
    public void init() {
        //TODO whens
        paymentGateway = new FakePaymentGateway();
    }

   //TODO
}
