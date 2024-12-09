package RomanoPietro.CapstoneProject.entities.ExerciseWorkout;
import jakarta.validation.constraints.NotNull;


public record NewExerciseWorkoutDTO(

        long workoutPlanId,


        long exerciseId,

        @NotNull(message = "Il numero di ripetizioni è obbligatorio")
        Integer ripetizioni,

        @NotNull(message = "Il numero di serie è obbligatorio")
        Integer serie,

        @NotNull(message = "Il peso usato è obbligatorio")
        Double pesoUsato
) {
}
