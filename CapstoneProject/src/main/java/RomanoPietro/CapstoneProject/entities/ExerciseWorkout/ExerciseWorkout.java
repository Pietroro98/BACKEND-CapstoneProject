package RomanoPietro.CapstoneProject.entities.ExerciseWorkout;


import RomanoPietro.CapstoneProject.entities.Exercise.Exercise;
import RomanoPietro.CapstoneProject.entities.WorkouPlan.WorkoutPlan;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exercise_workout")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ExerciseWorkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "workoutPlan_id")
    private WorkoutPlan workoutPlan;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    private int ripetizioni;
    private int serie;
    private double pesoUsato;

    public ExerciseWorkout(WorkoutPlan workoutPlan, Exercise exercise, int ripetizioni, int serie, double pesoUsato) {
        this.workoutPlan = workoutPlan;
        this.exercise = exercise;
        this.ripetizioni = ripetizioni;
        this.serie = serie;
        this.pesoUsato = pesoUsato;
    }
}