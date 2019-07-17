package hello;

import hello.model.*;
import hello.persistence.ProtocolRepository;
import hello.persistence.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder, ProtocolRepository protocolRepository) {
        return (args) -> {
            User user = new User();
            user.setUsername("m");
            user.setPassword(passwordEncoder.encode("m"));
            user.getRoles().add(Roles.ROLE_USER);
            userRepository.save(user);

            user = new User();
            user.setUsername("a");
            user.setPassword(passwordEncoder.encode("a"));
            user.getRoles().add(Roles.ROLE_ADMIN);
            user = userRepository.save(user);

            FileWrapper file = new FileWrapper();
            file.setContent("HelloWorld".getBytes());
            file.setFileName("foo");
            file.setFileType("application/pdf");
            Protocol p = new Protocol();
            p.setUploader(user);
            p.setFile(file);
            p = protocolRepository.save(p);
            int i = 0;
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

}
