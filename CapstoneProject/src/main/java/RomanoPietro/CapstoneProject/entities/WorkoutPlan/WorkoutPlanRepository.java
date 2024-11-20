package RomanoPietro.CapstoneProject.entities.WorkoutPlan;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
    Page<WorkoutPlan> findByUserId(long userId, Pageable pageable);
}
