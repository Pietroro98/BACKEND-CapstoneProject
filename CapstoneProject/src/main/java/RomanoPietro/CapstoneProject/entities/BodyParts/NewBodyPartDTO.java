package RomanoPietro.CapstoneProject.entities.BodyParts;
import jakarta.validation.constraints.NotEmpty;

public record NewBodyPartDTO(
        @NotEmpty(message = "Il nome del body part è obbligatorio")
        String nome,

        String descrizione
) {
}
