package RomanoPietro.CapstoneProject.entities.Health;

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
public class HealthService {
    @Autowired
    HealthRepository healthRepository;

    @Autowired
    UserRepository userRepository;

    //get all
    public Page<HealthData> findAll(int page, int size, String sortBy) {
        try {
            if (size > 100) size = 100;
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return this.healthRepository.findAll(pageable);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero di HealthData: " + e.getMessage());
        }
    }

    public HealthData findById(long id) {
        try {
            return this.healthRepository.findById(id).orElseThrow(() -> new NotFoundException("HealthData con id: " + id + " non trovato"));
        } catch (NotFoundException e) {
            throw e; // Rilancia l'eccezione NotFoundException
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero del HealthData: " + e.getMessage());
        }
    }

    //POST---------------------------------------------
    public HealthData save(NewHealthDTO body) {
        try {

            User newUser = userRepository.findById(body.userId())
                    .orElseThrow(() -> new NotFoundException("Utente con ID: " + body.userId() + " non trovato"));

            HealthData newHealth = new HealthData(
                    body.peso(),
                    body.altezza(),
                    body.eta(),
                    body.data_salvataggio());
            newHealth.setUser(newUser);

            return this.healthRepository.save(newHealth);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio di HealthData: " + e.getMessage());
        }
    }

    //DELETE --------------------------------------------
    //l'eliminazione la può fare solamente l'utente che lo ha creato
    public void findByIdAndDelete(long id, User user) {
        try {
            HealthData found = this.findById(id);
            if (found.getUser().getId() != user.getId()) {
                throw new BadRequestException("Non hai i permessi per eliminare questo HealthData");
            }
            this.healthRepository.delete(found);

        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione di HealthData: " + e.getMessage());
        }
    }
}
