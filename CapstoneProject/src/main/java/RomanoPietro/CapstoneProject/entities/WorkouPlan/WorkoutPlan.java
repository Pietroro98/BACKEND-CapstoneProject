package RomanoPietro.CapstoneProject.entities.WorkouPlan;

import RomanoPietro.CapstoneProject.Users.User;
import RomanoPietro.CapstoneProject.entities.ExerciseWorkout.ExerciseWorkout;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "workout_plan")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class WorkoutPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(nullable = false)
    private String nome_scheda;

    private LocalDate data_creazione;
    private LocalDate data_allenamento;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ExerciseWorkout> exerciseWorkouts;

    public WorkoutPlan(String nome_scheda, LocalDate data_creazione, LocalDate data_allenamento, User user) {
        this.nome_scheda = nome_scheda;
        this.data_creazione = data_creazione;
        this.data_allenamento = data_allenamento;
        this.user = user;
    }
}