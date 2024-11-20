package RomanoPietro.CapstoneProject.entities.WorkoutPlan;


import RomanoPietro.CapstoneProject.Users.User;
import RomanoPietro.CapstoneProject.exceptions.BadRequestException;
import RomanoPietro.CapstoneProject.exceptions.NotFoundException;
import RomanoPietro.CapstoneProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class WorkoutPlanService {

    @Autowired
    WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    UserRepository userRepository;

    //GET -------------------------

    public Page<WorkoutPlan> getWorkoutPlansByUser(long userId, int page, int size) {
        try {
            userRepository.findById(userId)
                    .orElseThrow(() -> new NotFoundException("Utente con ID " + userId + " non trovato"));
            if (size > 100) size = 100;
            Pageable pageable = PageRequest.of(page, size);

            // Recupero le schede di allenamento per l'utente
            Page<WorkoutPlan> workoutPlansPage = workoutPlanRepository.findByUserId(userId, pageable);
            if (workoutPlansPage.isEmpty()) {
                throw new NotFoundException("Nessuna scheda di allenamento trovata per l'utente con ID " + userId);
            }
            return workoutPlansPage;
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero delle schede di allenamento per l'utente con ID " + userId + ": " + e.getMessage());
        }
    }

    //POST --------------------------------

    public WorkoutPlan save(NewWorkoutPlanDTO body) {
        try {
            User user = userRepository.findById(body.userId())
                    .orElseThrow(() -> new NotFoundException("Utente con ID " + body.userId() + " non trovato"));

            WorkoutPlan newPlan = new WorkoutPlan(
                    body.nomeScheda(),
                    body.dataCreazione(),
                    body.dataAllenamento()
            );
            newPlan.setUser(user);
            return workoutPlanRepository.save(newPlan);

            // non applico altri controlli perche utente puo creare tutte le schede che vuole
            // anche con lo stesso nome, tanto gli sercizi saranno sempre diversi

        } catch (Exception e) {
            throw new BadRequestException("Errore durante la creazione della scheda di allenamento: " + e.getMessage());
        }
    }

    //PUT---------------------------------------------------------------------------
    public WorkoutPlan findByIdAndUpdate(long workoutPlanId, NewWorkoutPlanDTO body) {
        try {
            WorkoutPlan found = workoutPlanRepository.findById(workoutPlanId)
                    .orElseThrow(() -> new NotFoundException("Scheda di allenamento con ID " + workoutPlanId + " non trovata"));

            //aggiorno solo i campi che voglio
            if (body.nomeScheda() != null && !body.nomeScheda().isEmpty()) {
                found.setNome_scheda(body.nomeScheda());
            }
            if (body.dataCreazione() != null) {
                found.setData_creazione(body.dataCreazione());
            }
            if (body.dataAllenamento() != null) {
                found.setData_allenamento(body.dataAllenamento());
            }
            return this.workoutPlanRepository.save(found);

        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'aggiornamento della scheda di allenamento: " + e.getMessage());
        }

    }

    //DELETE --------------------------------

    public void deleteWorkoutPlan(long id, User user) {
        try {
            WorkoutPlan workoutPlan = workoutPlanRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Scheda con ID " + id + " non trovata"));

            workoutPlanRepository.delete(workoutPlan);

        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione della scheda: " + e.getMessage());
        }

    }
}
