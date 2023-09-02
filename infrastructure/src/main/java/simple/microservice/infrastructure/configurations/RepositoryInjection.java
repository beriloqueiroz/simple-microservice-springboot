package simple.microservice.infrastructure.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simple.microservice.application.gateway.ProfessionalRepository;
import simple.microservice.infrastructure.persistency.ProfessionalRepositoryImpl;

@Configuration
public class RepositoryInjection {
    @Bean
    public ProfessionalRepository professionalRepository(){
        return new ProfessionalRepositoryImpl();
    }
}
