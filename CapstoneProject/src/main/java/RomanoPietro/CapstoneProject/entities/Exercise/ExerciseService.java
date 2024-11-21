package RomanoPietro.CapstoneProject.entities.Exercise;

import RomanoPietro.CapstoneProject.entities.BodyParts.BodyParts;
import RomanoPietro.CapstoneProject.entities.BodyParts.BodyPartsService;
import RomanoPietro.CapstoneProject.exceptions.BadRequestException;
import RomanoPietro.CapstoneProject.exceptions.NotFoundException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Service
public class ExerciseService {
    @Autowired
    private Cloudinary cloudinaryUploader;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    BodyPartsService bodyPartsService;

    // GET--------------------------------------------------------
    public Page<Exercise> findAll(int page, int size, String sortBy) {
        try {
            // Validazione del campo sortBy
            if (!Arrays.asList("name", "createdAt", "updatedAt").contains(sortBy)) {
                sortBy = "name"; // Campo predefinito in caso di errore
            }
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return exerciseRepository.findAll(pageable);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero degli esercizi: " + e.getMessage());
        }
    }

    public Page<Exercise> searchExercises(String keyword, long bodyPartId, Pageable pageable) {
        return exerciseRepository.findByNameContainingAndBodyPartsId(keyword, bodyPartId, pageable);
    }

    // GET by ID -----------------------------------------------------------------
    public Exercise findById(long id) {
        try {
            return exerciseRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Esercizio con ID " + id + " non trovato"));
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero dell'esercizio: " + e.getMessage());
        }

    }

    // POST -----------------------------------------------------------------------
    public Exercise save(NewExerciseDTO body) {
        try {
            BodyParts bodyParts = bodyPartsService.findById(body.bodyPartId());
            Exercise newExercise = new Exercise(
                    body.name(),
                    body.equipment(),
                    body.target(),
                    body.secondaryMuscles(),
                    body.instructions()
            );
            newExercise.setBodyParts(bodyParts);
            return exerciseRepository.save(newExercise);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio dell'esercizio: " + e.getMessage());
        }
    }

    // DELETE ---------------------------------------------------------------------------------------------------
    public void findAndDeleteById(long id) {
        try {
            Exercise exercise = findById(id);
            exerciseRepository.delete(exercise);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione dell'esercizio: " + e.getMessage());
        }
    }

    //PATCH---------------
    public String uploadAvatar(MultipartFile file, long exerciseId) {
        String url = null;
        try {
            url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
            Exercise exercise = exerciseRepository.findById(exerciseId)
                    .orElseThrow(() -> new NotFoundException("Esercizio con ID " + exerciseId + " non trovato"));
            exercise.setAvatar(url);
            this.exerciseRepository.save(exercise);
        } catch (IOException e) {
            throw new BadRequestException("Ci sono stati problemi con l'upload del file!");
        }
        return url;
    }
}
