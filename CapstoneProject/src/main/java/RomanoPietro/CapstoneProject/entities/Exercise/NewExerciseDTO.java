package RomanoPietro.CapstoneProject.entities.Exercise;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewExerciseDTO(
        @NotEmpty(message = "Il nome dell'esercizio è obbligatorio")
        String name,

        @NotEmpty(message = "L'equipaggiamento è obbligatorio")
        String equipment,

        @NotEmpty(message = "Il target è obbligatorio")
        String target,

//        campi che possono anche non essere obbligatori avere
//        poiché si tratta di istruzioni secondarie, e un es
//        puó anche non avere la dimostrazione tramite gifUrl animata.

        String secondaryMuscles,

        String instructions,

        String avatarUrl,

        @NotNull(message = "L'ID del bodyPart è obbligatorio")
        long bodyPartId
) {
}
