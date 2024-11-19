package RomanoPietro.CapstoneProject.services;

import RomanoPietro.CapstoneProject.Users.User;
import RomanoPietro.CapstoneProject.exceptions.BadRequestException;
import RomanoPietro.CapstoneProject.exceptions.NotFoundException;
import RomanoPietro.CapstoneProject.payloads.NewUserDTO;
import RomanoPietro.CapstoneProject.repositories.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public User findById(long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con email " + email + " non è stato trovato"));
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User save(NewUserDTO body) {
        // Controllo se l'email è già in uso
        this.userRepository.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("Email " + body.email() + " già in uso!");
                }
        );

        // Controllo se l'username è già in uso
        this.userRepository.findByUsername(body.username()).ifPresent(
                user -> {
                    throw new BadRequestException("Username " + body.username() + " è già in uso!");
                }
        );

        String avatarUrl = generateAvatarUrl(body.name(), body.surname());
        User newUtente = new User(
                body.username(),
                body.name(),
                body.surname(),
                body.email(),
                bcrypt.encode(body.password()),
                avatarUrl);
        return this.userRepository.save(newUtente);
    }

    public User save(User utente) {
        return this.userRepository.save(utente);
    }

    // Metodo per generare l'avatar URL
    private String generateAvatarUrl(String nome, String cognome) {
        return "https://ui-avatars.com/api/?name=" + nome + "+" + cognome;
    }

    public User findByIdAndUpdate(long id, NewUserDTO body) {
        User found = this.findById(id);

        if (!found.getEmail().equals(body.email())) {
            this.userRepository.findByEmail(body.email()).ifPresent(
                    user -> {
                        throw new BadRequestException("Email " + body.email() + " già in uso!");
                    }
            );
        }
        found.setUsername(body.username());
        found.setName(body.name());
        found.setSurname(body.surname());
        found.setEmail(body.email());

        return this.userRepository.save(found);
    }

    public void findByIdAndDelete(long id) {
        User found = this.findById(id);
        this.userRepository.delete(found);
    }

    public String uploadAvatar(MultipartFile file, long userId) {
        String url = null;
        try {
            url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        } catch (IOException e) {
            throw new BadRequestException("Ci sono stati problemi con l'upload del file!");
        }
        return url;
    }
}
