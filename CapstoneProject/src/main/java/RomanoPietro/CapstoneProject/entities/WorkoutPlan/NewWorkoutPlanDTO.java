package RomanoPietro.CapstoneProject.entities.WorkoutPlan;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewWorkoutPlanDTO(
        @NotEmpty(message = "Il nome della scheda è obbligatorio")
        String nomeScheda,

        @NotNull(message = "La data di creazione è obbligatoria")
        LocalDate dataCreazione,

        @NotNull(message = "La data di allenamento è obbligatoria")
        LocalDate dataAllenamento,

        @NotNull(message = "L'ID utente è obbligatorio")
        long userId
) {
}
