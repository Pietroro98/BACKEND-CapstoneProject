package RomanoPietro.CapstoneProject.entities.ExerciseWorkout;
import RomanoPietro.CapstoneProject.exceptions.BadRequestException;
import RomanoPietro.CapstoneProject.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/exercise_workout")
public class ExerciseWorkoutController {
    @Autowired
    private ExerciseWorkoutService exerciseWorkoutService;


    @GetMapping("/workout_plan/{workoutPlanId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public Page<ExerciseWorkout> findByWorkoutPlanId(
            @PathVariable long workoutPlanId,
            @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {
        return exerciseWorkoutService.findByWorkoutPlanId(workoutPlanId, pageable);
    }


    @GetMapping("/exercise/{exerciseId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public Page<ExerciseWorkout> findByExerciseId(
            @PathVariable long exerciseId,
            @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {
        return exerciseWorkoutService.findByExerciseId(exerciseId, pageable);
    }

    //PUT----------------------------------------------------------------
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ExerciseWorkout findByIdAndUpdate(@PathVariable long id, @RequestBody @Validated NewExerciseWorkoutDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }
        try {
            return this.exerciseWorkoutService.findByIdAndUpdate(id, body);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'aggiornamento del cliente: " + e.getMessage());
        }
    }



    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseWorkout save(@RequestBody @Validated NewExerciseWorkoutDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        } try {
            return this.exerciseWorkoutService.save(body);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio della scheda dell'esercizio: " + e.getMessage());
        }
    }

   // GET singolo
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ExerciseWorkout getExerciseWorkoutById(@PathVariable long id) {
        return exerciseWorkoutService.findById(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable long id) {
        try {
            exerciseWorkoutService.deleteById(id);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione dell'ExerciseWorkout: " + e.getMessage());
        }
    }

}
