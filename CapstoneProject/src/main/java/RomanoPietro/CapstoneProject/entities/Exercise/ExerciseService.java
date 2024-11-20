package RomanoPietro.CapstoneProject.entities.Exercise;

import RomanoPietro.CapstoneProject.exceptions.BadRequestException;
import RomanoPietro.CapstoneProject.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    // GET ALL with Pagination
    public Page<Exercise> findAll(int page, int size, String sortBy) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return exerciseRepository.findAll(pageable);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero degli esercizi: " + e.getMessage());
        }
    }

    // GET by ID
    public Exercise findById(long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Esercizio con ID " + id + " non trovato"));
    }

    // POST
    public Exercise save(NewExerciseDTO body) {
        try {
            Exercise newExercise = new Exercise(
                    body.equipment(),
                    body.target(),
                    body.secondaryMuscles(),
                    body.instructions(),
                    body.gifURL()
            );
            return exerciseRepository.save(newExercise);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio dell'esercizio: " + e.getMessage());
        }
    }

    // DELETE
    public void delete(long id) {
        try {
            Exercise exercise = findById(id);
            exerciseRepository.delete(exercise);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione dell'esercizio: " + e.getMessage());
        }
    }
}
