package hello.registration;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RegistreeValidator.class})
public @interface ValidNewUser {

    String message() default "The given passwords did not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}