package simple.microservice.infrastructure.api.client;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simple.microservice.application.usecase.client.*;
import simple.microservice.application.usecase.client.dtos.InputAddClientUseCaseDto;
import simple.microservice.application.usecase.client.dtos.InputFindClientUseCaseDto;
import simple.microservice.application.usecase.client.dtos.OutputFindClientUseCaseDto;
import simple.microservice.domain.shared.DomainException;
import simple.microservice.infrastructure.api.dtos.InputAddClientDto;
import simple.microservice.infrastructure.api.dtos.OutputDefaultDto;
import simple.microservice.infrastructure.api.dtos.OutputFindClientDto;

import java.util.Objects;

@RestController
@Setter
@RequestMapping(value = "client")
public class ClientController {

    private AddClientUseCase addClientUseCase;
    private EditClientUseCase editClientUseCase;
    private FindAllClientsUseCase findAllClientsUseCase;
    private FindClientUseCase findClientUseCase;
    private FindPaymentsUseCase findPaymentsUseCase;
    private RemoveClientUseCase removeClientUseCase;

    @Value("${simple.professionalId}")
    private Long professionalId;

    public ClientController(AddClientUseCase addClientUseCase,
                            EditClientUseCase editClientUseCase,
                            FindAllClientsUseCase findAllClientsUseCase,
                            FindClientUseCase findClientUseCase) {
        this.addClientUseCase = Objects.requireNonNull(addClientUseCase);
        this.editClientUseCase = Objects.requireNonNull(editClientUseCase);
        this.findAllClientsUseCase = Objects.requireNonNull(findAllClientsUseCase);
        this.findClientUseCase = Objects.requireNonNull(findClientUseCase);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputDefaultDto> addClient(@RequestBody InputAddClientDto requestDto) throws DomainException {
           try {
               this.addClientUseCase.execute(
                       new InputAddClientUseCaseDto(
                               professionalId,
                               requestDto.firstName(),
                               requestDto.surname(),
                               requestDto.email(),
                               requestDto.cpf(),
                               requestDto.birthDate()
                       )
               );
               return genericValidResponse();
           } catch (Exception e){
               return invalidResponse(e.getMessage());
           }
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{clientId}")
    public ResponseEntity<OutputDefaultDto> removeClient(@PathVariable Long clientId) {
        try {
            this.removeClientUseCase.execute(professionalId, clientId);
            return genericValidResponse();
        } catch (Exception e) {
            return invalidResponse(e.getMessage());
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{clientId}")
    public ResponseEntity<OutputFindClientDto> findClient(
            @PathVariable Long clientId) {
        try {

            OutputFindClientUseCaseDto output = this.findClientUseCase.execute(
                    new InputFindClientUseCaseDto(
                            professionalId, clientId
                    )
            );

            OutputFindClientDto outputFindClientDto = new OutputFindClientDto(
                    output.id(),
                    output.professionalId(),
                    output.firstName(),
                    output.surName(),
                    output.email(),
                    output.cpf(),
                    output.birthDate(),
                    "Operação realizada com sucesso",
                    true
            );
            return new ResponseEntity<>(outputFindClientDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new OutputFindClientDto("error: "+e.getMessage(),false),
                    HttpStatus.BAD_REQUEST);
        }
    }

    //TODO outros endpoints

    private ResponseEntity<OutputDefaultDto> genericValidResponse(){
        return new ResponseEntity<>(new OutputDefaultDto(true, "Operação realizada com sucesso"),HttpStatus.OK);
    }

    private ResponseEntity<OutputDefaultDto> invalidResponse(String error){
        return new ResponseEntity<>( new OutputDefaultDto(false, "error: "+error), HttpStatus.BAD_REQUEST);
    }
}
