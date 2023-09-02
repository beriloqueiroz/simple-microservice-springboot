package simple.microservice.infrastructure.persistency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import simple.microservice.infrastructure.persistency.jpa.ProfessionalJpaEntity;
import simple.microservice.infrastructure.persistency.jpa.ProfessionalJpaRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DatabaseSeeder {
    private final ProfessionalJpaRepository professionalJpaRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseSeeder(
            ProfessionalJpaRepository professionalJpaRepository,
            JdbcTemplate jdbcTemplate) {
        this.professionalJpaRepository = professionalJpaRepository;
        this.jdbcTemplate = jdbcTemplate;
    }
    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedProfessional();
    }

    private void seedProfessional() {
        String sql = "SELECT * FROM professionals c WHERE 1 LIMIT 1";
        List<ProfessionalJpaEntity> professionals;
        try {
           professionals = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        } catch (Exception e) {
            log.info("Error in seeders");
            return;
        }
        if(professionals == null || professionals.size() == 0) {
            List<ProfessionalJpaEntity> professionalList = new ArrayList<>();

            ProfessionalJpaEntity professional1 = new ProfessionalJpaEntity();
            professional1.setId(1L);
            //TODO sets

            professionalList.add(professional1);

            ProfessionalJpaEntity professional2 = new ProfessionalJpaEntity();
            professional2.setId(2L);
            //TODO sets

            professionalList.add(professional2);

            professionalJpaRepository.saveAll(professionalList);

            log.info("Professional Seeded");
        } else {
            log.info("Professional Seeding Not Required");
        }
    }
}
