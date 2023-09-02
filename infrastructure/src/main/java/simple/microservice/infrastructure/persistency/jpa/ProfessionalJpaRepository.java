package simple.microservice.infrastructure.persistency.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalJpaRepository extends JpaRepository<ProfessionalJpaEntity, Long> {
}