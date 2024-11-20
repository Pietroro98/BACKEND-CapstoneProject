package RomanoPietro.CapstoneProject.entities.WorkoutPlan;


import RomanoPietro.CapstoneProject.Users.User;
import RomanoPietro.CapstoneProject.entities.Health.HealthData;
import RomanoPietro.CapstoneProject.entities.Health.NewHealthDTO;
import RomanoPietro.CapstoneProject.exceptions.BadRequestException;
import RomanoPietro.CapstoneProject.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/workout_plans")
public class WorkoutPlanController {
    @Autowired
    WorkoutPlanService workoutPlanService;

    //GET---------------------------------

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public Page<WorkoutPlan> getWorkoutPlansByUser(
            @PathVariable long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return workoutPlanService.getWorkoutPlansByUser(userId, page, size);
    }

    //POST----------------------------------------

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutPlan save(@RequestBody @Validated NewWorkoutPlanDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        } try {
            return this.workoutPlanService.save(body);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio della scheda di allenamento: " + e.getMessage());
        }
    }

    //PUT---------------------------------------------------------------------------
    @PutMapping("/{workoutPlanId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public WorkoutPlan findByIdAndUpdate(@PathVariable long workoutPlanId,
                                             @RequestBody @Validated NewWorkoutPlanDTO body,
                                             BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }
        try {
            return this.workoutPlanService.findByIdAndUpdate(workoutPlanId, body);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'aggiornamento della scheda di allenamento: " + e.getMessage());
        }
    }

    //DELETE-------------------------------------------------------------------------
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkoutPlan(@PathVariable long id, @AuthenticationPrincipal User currentAuthenticatedUser) {
        try {
            workoutPlanService.deleteWorkoutPlan(id, currentAuthenticatedUser);
        } catch (NotFoundException e) {
            throw new NotFoundException("Scheda con ID " + id + " non trovata.");
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione della scheda: " + e.getMessage());
        }
    }
}
