package RomanoPietro.CapstoneProject.services;



import RomanoPietro.CapstoneProject.Users.User;
import RomanoPietro.CapstoneProject.exceptions.UnauthorizedException;
import RomanoPietro.CapstoneProject.payloads.UserLoginDTO;
import RomanoPietro.CapstoneProject.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private JWT jwt;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(UserLoginDTO body) {
        User found = this.userService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            // 2. Se sono OK --> Genero il token
            String accessToken = jwt.createToken(found);
            // 3. Ritorno il token
            return accessToken;
        } else {

            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}
