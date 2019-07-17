package hello.registration;

import hello.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegistreeValidator implements ConstraintValidator<ValidNewUser, Registree> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(ValidNewUser constraint) {
    }

    @Override
    public boolean isValid(Registree registree, ConstraintValidatorContext context) {
        boolean totalValid = true;
        boolean validPasswords = registree.getPassword().equals(registree.getPassword2());
        if (!validPasswords) {
            context.buildConstraintViolationWithTemplate("The given passwords did not match")
                    .addPropertyNode("password2")
                    .addConstraintViolation();
            totalValid = false;
        }

        if (userRepository.findByUsername(registree.getUsername()) != null) {
            context.buildConstraintViolationWithTemplate("The given Username is already taken")
                    .addPropertyNode("username")
                    .addConstraintViolation();
            totalValid = false;
        }

        return totalValid;
    }
}