package RomanoPietro.CapstoneProject.entities.Health;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewHealthDTO(
        @Min(0)
        @NotNull(message = "Il peso è obbligatorio")
        double peso,

        @Min(0)
        @NotNull(message = "L'altezza è obbligatoria")
        double altezza,

        @Min(0)
        @NotNull(message = "L'etá è obbligatoria")
        int eta,

        @NotEmpty(message = "Il genere é obbligatorio")
        String genere,

        @NotNull(message = "La data è obbligatoria")
        LocalDate data_salvataggio,

        @NotNull(message = "L'ID utente è obbligatorio")
        long userId
) {
}
