package RomanoPietro.CapstoneProject.entities.BodyParts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/body-parts")
public class bodyPartsController {

    @Autowired
    BodyPartsService bodyPartsService;

    // GET - Recupera tutte le BodyParts (accessibile a tutti)
    @GetMapping
    public List<BodyParts> findAll() {
        return bodyPartsService.findAll();
    }

    // POST - Creazione di una nuova BodyPart
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public BodyParts save(@RequestBody NewBodyPartDTO body) {
        return bodyPartsService.save(body);
    }

    // GET - Trova una BodyPart per ID
    @GetMapping("/{id}")
    public BodyParts findById(@PathVariable long id) {
        return bodyPartsService.findById(id);
    }

    // DELETE - Elimina una BodyPart per ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT) // HTTP 204 - No Content
    public void deleteById(@PathVariable long id) {
        bodyPartsService.deleteById(id);
    }

}
