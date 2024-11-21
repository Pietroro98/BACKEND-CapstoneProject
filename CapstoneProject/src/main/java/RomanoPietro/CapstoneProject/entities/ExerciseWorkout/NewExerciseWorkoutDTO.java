package RomanoPietro.CapstoneProject.entities.ExerciseWorkout;
import jakarta.validation.constraints.NotNull;


public record NewExerciseWorkoutDTO(
        @NotNull(message = "WorkoutPlan ID è obbligatorio")
        long workoutPlanId,

        @NotNull(message = "Exercise ID è obbligatorio")
        long exerciseId,

        @NotNull(message = "Il numero di ripetizioni è obbligatorio")
        int ripetizioni,

        @NotNull(message = "Il numero di serie è obbligatorio")
        int serie,

        @NotNull(message = "Il peso usato è obbligatorio")
        double pesoUsato
) {
}
