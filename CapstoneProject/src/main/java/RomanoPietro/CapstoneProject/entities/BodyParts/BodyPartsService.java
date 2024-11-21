package RomanoPietro.CapstoneProject.entities.BodyParts;

import RomanoPietro.CapstoneProject.entities.Exercise.Exercise;
import RomanoPietro.CapstoneProject.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodyPartsService {
    @Autowired
    BodyPartsRepository bodyPartsRepository;

    // POST -----------------------------------------------------------------------
    public BodyParts save(NewBodyPartDTO body) {
        try {
            BodyParts newBodyPart = new BodyParts(
                    body.nome()
            );
            return this.bodyPartsRepository.save(newBodyPart);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio della BodyPart : " + e.getMessage());
        }
    }

    //GET---------------------------------------------

    public BodyParts findById(long id) {
        try {
            return bodyPartsRepository.findById(id)
                    .orElseThrow(() -> new BadRequestException("BodyPart con ID " + id + " non trovato"));
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Errore durante la ricerca della BodyPart: " + e.getMessage());
        }
    }
    //GETALL---------------------------------------------

    public List<BodyParts> findAll() {
        try {
            return this.bodyPartsRepository.findAll();
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero delle BodyParts: " + e.getMessage());
        }
    }

    // DELETE ---------------------------------------------------------------------------------------------------
    public void deleteById(long id) {
        try {
            if (!bodyPartsRepository.existsById(id)) {
                throw new BadRequestException("BodyPart con ID " + id + " non esistente");
            }
            bodyPartsRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione della BodyPart: " + e.getMessage());
        }
    }

}
