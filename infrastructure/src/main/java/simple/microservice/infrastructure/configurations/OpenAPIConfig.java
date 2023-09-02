package simple.microservice.infrastructure.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${simple.openapi.dev-url}")
    private String devUrl;

    @Value("${simple.openapi.prod-url}")
    private String prodUrl;

    @Value("${simple.openapi.homolog-url}")
    private String homologUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Server homologServer = new Server();
        homologServer.setUrl(homologUrl);
        homologServer.setDescription("Server URL in Homolog environment");

        Contact contact = new Contact();
        contact.setEmail("contato@simple.com.br");
        contact.setName("Simple");
        contact.setUrl("https://www.qualquercoisa.com.br");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Clients and Professionals Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to simple microservice.").termsOfService("https://www.qualquercoisa.com.br/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer, homologServer));
    }
}