package RomanoPietro.CapstoneProject.entities.BodyParts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyPartsRepository extends JpaRepository<BodyParts, Long> {
}
