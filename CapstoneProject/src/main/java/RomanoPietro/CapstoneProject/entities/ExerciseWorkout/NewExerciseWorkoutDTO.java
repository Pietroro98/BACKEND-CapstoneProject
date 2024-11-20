package RomanoPietro.CapstoneProject.entities.ExerciseWorkout;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record NewExerciseWorkoutDTO(
        @NotNull(message = "L'ID dell'esercizio è obbligatorio")
        long exerciseId,

        @NotNull(message = "L'ID del workout plan è obbligatorio")
        long workoutPlanId,

        @Min(1)
        int ripetizioni,

        @Min(1)
        int serie,

        @Min(0)
        double pesoUsato
) {
}
