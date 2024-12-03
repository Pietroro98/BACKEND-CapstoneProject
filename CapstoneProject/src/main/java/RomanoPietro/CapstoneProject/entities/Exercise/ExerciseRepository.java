package RomanoPietro.CapstoneProject.entities.Exercise;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Page<Exercise> findByNameContainingAndBodyPartsId(String name, long bodyPartId, Pageable pageable);
    Page<Exercise> findByBodyParts_NomeContainingIgnoreCase(String bodyPartName, Pageable pageable);
}
