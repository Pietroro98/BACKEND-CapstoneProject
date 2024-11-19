package RomanoPietro.CapstoneProject.entities.Health;


import RomanoPietro.CapstoneProject.Users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "health_data")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class HealthData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private long peso;
    private long altezza;
    private long eta;
    private LocalDate data_salvataggio;

    @OneToOne
    @JoinColumn( name = "user_id")
    private User user;

    public HealthData(long peso, long altezza, long eta, LocalDate data_salvataggio) {
        this.peso = peso;
        this.altezza = altezza;
        this.eta = eta;
        this.data_salvataggio = data_salvataggio;

    }
}
