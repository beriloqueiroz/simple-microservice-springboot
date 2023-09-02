package simple.microservice.domain.entity;

import lombok.Getter;
import simple.microservice.domain.helpers.validation.error.CpfValidationError;
import simple.microservice.domain.helpers.validation.error.CpfValidator;
import simple.microservice.domain.shared.DomainException;
import simple.microservice.domain.shared.Entity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Professional extends Entity {

    private final String cpf;

    private final String document;

    private final String email;

    private final String name;

    private List<Client> clients;

    public Professional(Long id, String cpf, String document, String email, String name, List<Client> clients) throws DomainException {
        super(id);
        this.cpf = cpf;
        this.document = document;
        this.email = email;
        this.name = name;
        this.clients = clients;
        if (this.clients == null) {
            this.clients = new ArrayList<>();
        }
        this.validate();
        if (!this.validationErrors.isValid()){
            throw new DomainException(validationErrors.getAllErrorMessages());
        }
    }

    @Override
    public void validate() {
        CpfValidator cpfValidator = new CpfValidator();
        if(cpf.isBlank() || !cpfValidator.isValid(cpf, null)) {
            this.validationErrors.addError(new CpfValidationError("Cpf é obrigatório"));
        }

        if (name.isBlank()){
            this.validationErrors.addError(new CpfValidationError("Nome é obrigatório"));
        }
    }

    public void addClient(Client client){
        this.clients.add(client);
    }

    public void populateClient(List<Client> clients){
        this.clients = clients;
    }
}
