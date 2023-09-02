package simple.microservice.infrastructure.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import simple.microservice.infrastructure.api.WebDefaultT;

public class ClientControllerE2eT extends WebDefaultT {

    ObjectMapper mapper;

    @Before
    public void setup(){
        super.setUp();
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void run(){

    }

    //TODO
}
