package RomanoPietro.CapstoneProject.entities.ExerciseWorkout;




import RomanoPietro.CapstoneProject.entities.Exercise.Exercise;
import RomanoPietro.CapstoneProject.entities.Exercise.ExerciseRepository;
import RomanoPietro.CapstoneProject.entities.WorkoutPlan.WorkoutPlan;
import RomanoPietro.CapstoneProject.entities.WorkoutPlan.WorkoutPlanRepository;
import RomanoPietro.CapstoneProject.exceptions.BadRequestException;
import RomanoPietro.CapstoneProject.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ExerciseWorkoutService {
    @Autowired
    ExerciseWorkoutRepository exerciseWorkoutRepository;
    @Autowired
    WorkoutPlanRepository workoutPlanRepository;
    @Autowired
    ExerciseRepository exerciseRepository;



    // GET by WorkoutPlan ID ---------------------------
    public Page<ExerciseWorkout> findByWorkoutPlanId(long workoutPlanId, Pageable pageable) {
        try {
            return exerciseWorkoutRepository.findByWorkoutPlanId(workoutPlanId, pageable);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero degli ExerciseWorkout: " + e.getMessage());
        }
    }

    // GET by Exercise ID --------------------------------
    public Page<ExerciseWorkout> findByExerciseId(long exerciseId, Pageable pageable) {
        try {
            return exerciseWorkoutRepository.findByExerciseId(exerciseId, pageable);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero degli ExerciseWorkout: " + e.getMessage());
        }
    }

    // POST -----------------------------------------------------------------------
    public ExerciseWorkout save(NewExerciseWorkoutDTO body) {
        try {
            // Recupero il WorkoutPlan
            WorkoutPlan workoutPlan = workoutPlanRepository.findById(body.workoutPlanId())
                    .orElseThrow(() -> new NotFoundException("WorkoutPlan con ID " + body.workoutPlanId() + " non trovato"));

            // Recupero l'Exercise
            Exercise exercise = exerciseRepository.findById(body.exerciseId())
                    .orElseThrow(() -> new NotFoundException("Exercise con ID " + body.exerciseId() + " non trovato"));

            // Creo un nuovo ExerciseWorkout
            ExerciseWorkout newExerciseWorkout = new ExerciseWorkout(
                    body.ripetizioni(),
                    body.serie(),
                    body.pesoUsato()
            );
            newExerciseWorkout.setWorkoutPlan(workoutPlan);
            newExerciseWorkout.setExercise(exercise);
            return this.exerciseWorkoutRepository.save(newExerciseWorkout);

        } catch (Exception e) {
            throw new BadRequestException("Errore durante la creazione dell'ExerciseWorkout: " + e.getMessage());
        }
    }


    //PUT --------------------------------------------
    public ExerciseWorkout findByIdAndUpdate(long id, NewExerciseWorkoutDTO body) {
        try {
            // Trova l'ExerciseWorkout esistente tramite l'ID
            ExerciseWorkout found = this.findById(id);

            if (body.ripetizioni() != null) {
                found.setRipetizioni(body.ripetizioni());
            }
            if (body.serie() != null) {
                found.setSerie(body.serie());
            }
            if (body.pesoUsato() != null) {
                found.setPesoUsato(body.pesoUsato());
            }

            return this.exerciseWorkoutRepository.save(found);
        } catch (NotFoundException e) {
            throw e;  // Se l'ExerciseWorkout non viene trovato, lancia NotFoundException
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'aggiornamento! " + e.getMessage());
        }
    }

    //GET singoloexeWor
    public ExerciseWorkout findById(long id) {
        return exerciseWorkoutRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ExerciseWorkout con ID " + id + " non trovato"));
    }

    // DELETE -----------------------------------------------------------------------
    public void deleteById(long id) {
        try {
            ExerciseWorkout found = exerciseWorkoutRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("ExerciseWorkout con ID " + id + " non trovato"));
            exerciseWorkoutRepository.delete(found);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione dell'ExerciseWorkout: " + e.getMessage());
        }
    }
}
