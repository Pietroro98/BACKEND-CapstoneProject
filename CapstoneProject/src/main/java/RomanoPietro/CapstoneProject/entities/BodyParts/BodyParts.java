package RomanoPietro.CapstoneProject.entities.BodyParts;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "body_parts")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BodyParts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private String nome;


    public BodyParts(String nome) {
        this.nome = nome;
    }
}
