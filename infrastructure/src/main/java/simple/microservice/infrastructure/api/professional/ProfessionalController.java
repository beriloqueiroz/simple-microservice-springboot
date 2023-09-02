package simple.microservice.infrastructure.api.professional;

import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import simple.microservice.infrastructure.api.dtos.OutputDefaultDto;

@RestController
@Setter
public class ProfessionalController {

    private ResponseEntity<OutputDefaultDto> genericValidResponse(){
        return new ResponseEntity<>(new OutputDefaultDto(true, "Operação realizada com sucesso"),HttpStatus.OK);
    }

    private ResponseEntity<OutputDefaultDto> invalidResponse(String error){
        return new ResponseEntity<>( new OutputDefaultDto(false, "error: "+error), HttpStatus.BAD_REQUEST);
    }
}
