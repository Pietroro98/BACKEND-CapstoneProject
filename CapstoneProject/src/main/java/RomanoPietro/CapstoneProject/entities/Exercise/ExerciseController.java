package RomanoPietro.CapstoneProject.entities.Exercise;

import RomanoPietro.CapstoneProject.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    // GET ALL
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public Page<Exercise> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        return exerciseService.findAll(page, size, sortBy);
    }

    //GET AND SEARCH
    @GetMapping("/search")
    public Page<Exercise> searchExercises(@RequestParam("keyword") String keyword,
                                          @RequestParam("bodyPartId") Long bodyPartId,
                                          @RequestParam("page") int page,
                                          @RequestParam("size") int size,
                                          @RequestParam("sortBy") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return exerciseService.searchExercises(keyword, bodyPartId, pageable);
    }

    // GET by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public Exercise findById(@PathVariable long id) {
        return exerciseService.findById(id);
    }

    // POST
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Exercise save(@RequestBody @Validated NewExerciseDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Errore nel payload: " + message);
        }
        return exerciseService.save(body);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDeleteById(@PathVariable long id) {
        exerciseService.findAndDeleteById(id);
    }


    @PatchMapping("/avatar")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String uploadAvatar(@RequestParam("exerciseId") long exerciseId,
                               @RequestParam("avatar") MultipartFile file) {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Il file è vuoto");
        }

        try {
            String avatarUrl = exerciseService.uploadAvatar(file, exerciseId);
            return avatarUrl;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Errore durante l'upload dell'avatar: " + e.getMessage(), e);
        }
    }
}
