package simple.microservice.infrastructure.persistency.jpa;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import simple.microservice.domain.entity.Professional;

import java.io.Serializable;

@Data
@Entity(name = "Professional")
@Table(name = "professionals")
@NoArgsConstructor
public class ProfessionalJpaEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	//TODO estrutura de dados , atributos da entidade

	//TODO construtor com todos parametros

	public void fill(final Professional professional) {
			//TODO
	}

	public static ProfessionalJpaEntity from(final Professional professional) {
		return new ProfessionalJpaEntity(
				//TODO
		);
	}

	//TODO
//	public Professional to() throws DomainException {
//		return new Professional(
//
//		);
//	}
}