package simple.microservice.infrastructure.api.client;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import simple.microservice.infrastructure.api.WebDefaultT;

public class ClientControllerResilienceE2eT extends WebDefaultT {
    @Autowired
    ClientController clientController;

    @Autowired
    protected CircuitBreakerRegistry registry;

    @Before
    public void setup() {
        super.setUp();
        //TODO whens
    }

    @Test
    public void run(){

    }

    //TODO
}
