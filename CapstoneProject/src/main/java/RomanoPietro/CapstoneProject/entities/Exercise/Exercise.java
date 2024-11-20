package RomanoPietro.CapstoneProject.entities.Exercise;


import RomanoPietro.CapstoneProject.entities.BodyParts.BodyParts;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exercise")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "bodyPart_id", nullable = false)
    private BodyParts bodyParts;

    private String name;
    private String equipment;
    private String target;
    private String secondaryMuscles;
    private String instructions;
    private String gifURL;

    public Exercise(BodyParts bodyParts, String name, String equipment, String target, String secondaryMuscles, String instructions) {
        this.bodyParts = bodyParts;
        this.name = name;
        this.equipment = equipment;
        this.target = target;
        this.secondaryMuscles = secondaryMuscles;
        this.instructions = instructions;
        this.gifURL = "giftURL";
    }
}
