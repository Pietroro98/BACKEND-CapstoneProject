package RomanoPietro.CapstoneProject.entities.Health;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewHealthDTO(
        @Min(0)
        @NotNull(message = "Il peso è obbligatorio")
        long peso,

        @Min(0)
        @NotNull(message = "L'altezza è obbligatoria")
        long altezza,

        @Min(0)
        @NotNull(message = "L'etá è obbligatoria")
        long eta,

        @NotNull(message = "La data è obbligatoria")
        LocalDate data_salvataggio,

        @NotNull(message = "L'ID utente è obbligatorio")
        long userId
) {
}
