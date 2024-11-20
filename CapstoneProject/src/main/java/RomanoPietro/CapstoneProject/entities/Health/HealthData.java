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

    private double peso;
    private double altezza;
    private int eta;
    private String genere;
    private LocalDate data_salvataggio;


    @OneToOne
    @JoinColumn( name = "user_id")
    private User user;

    public HealthData(double peso, double altezza, int eta, String genere, LocalDate data_salvataggio) {
        this.peso = peso;
        this.altezza = altezza;
        this.eta = eta;
        this.genere = genere;
        this.data_salvataggio = data_salvataggio;
    }



    // Calcolo BMI
    public double calculateBMI() {
        return peso / (altezza * altezza);
    }

    // Calcolo percentuale massa grassa
    public double calculateFatPercentage() {
        double bmi = calculateBMI();
        double fatPercentage = 0.0;

        if ("M".equals(genere)) {
            fatPercentage = 1.20 * bmi + 0.23 * eta - 16.2;
        } else if ("F".equals(genere)) {
            fatPercentage = 1.20 * bmi + 0.23 * eta - 5.4;
        }

        //ho usato m per maschio ed f per femmina

        return fatPercentage;
    }

    // Calcolo percentuale massa magra
    public double calculateLeanBodyMass() {
        double fatPercentage = calculateFatPercentage();
        return (100 - fatPercentage) * peso / 100;
    }
}
