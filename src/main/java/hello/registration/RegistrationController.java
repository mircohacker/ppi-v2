package hello.registration;

import hello.model.Roles;
import hello.model.User;
import hello.persistence.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String getRegistration(Registree registree) {
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@Valid Registree registree, BindingResult bindingResult, HttpServletRequest req) throws ServletException {
        // if the validation fails return to register
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        // store new user in database
        User u = new User();
        u.setUsername(registree.getUsername());
        u.setPassword(passwordEncoder.encode(registree.getPassword()));
        u.getRoles().add(Roles.ROLE_USER);
        userRepository.save(u);

        // Log user in
        req.login(registree.getUsername(), registree.getPassword());

        return "hello";
    }


}
