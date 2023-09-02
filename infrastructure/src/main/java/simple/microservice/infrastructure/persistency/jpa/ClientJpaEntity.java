package simple.microservice.infrastructure.persistency.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import simple.microservice.domain.entity.Client;

@Data
@Entity(name = "Client")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
public class ClientJpaEntity {
	//TODO many to many com ProfessionalJpaEntity

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	//TODO estrutura de dados , atributos da entidade

	//TODO construtor com todos parametros

	public static ClientJpaEntity from(final Client client){
		return new ClientJpaEntity(
				//TODO
		);
	}

	public void fill(final Client client){
		//TODO
	}

	//TODO
//	public Client to() throws DomainException {
//		return new Client(
//
//		);
//	}
}