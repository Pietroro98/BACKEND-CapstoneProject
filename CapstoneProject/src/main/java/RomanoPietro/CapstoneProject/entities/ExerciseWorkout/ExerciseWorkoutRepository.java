package RomanoPietro.CapstoneProject.entities.ExerciseWorkout;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseWorkoutRepository extends JpaRepository<ExerciseWorkout, Long> {
    // Recupero per un WorkoutPlan specifico
    Page<ExerciseWorkout> findByWorkoutPlanId(long workoutPlanId, Pageable pageable);

    // Recupero  per un Exercise specifico
    Page<ExerciseWorkout> findByExerciseId(long exerciseId, Pageable pageable);
}
