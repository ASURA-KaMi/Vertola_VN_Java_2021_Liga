package ru.digitalleague.exam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.digitalleague.exam.dto.UserRegistrationDTO;
import ru.digitalleague.exam.persistence.Role;
import ru.digitalleague.exam.repository.UserRepository;
import ru.digitalleague.exam.service.ApplicationService;
import ru.digitalleague.exam.service.UserService;

@SpringBootApplication
@EnableScheduling
public class ExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(UserService userService, ApplicationService applicationService, UserRepository userRepository) {
        return (args) -> {
            userService.saveRole(new Role(null, "ADMIN"));
            userService.saveRole(new Role(null, "USER"));

            userService.saveUser(new UserRegistrationDTO("admin", "admin", "admin", "ADMIN"));
            userService.saveUser(new UserRegistrationDTO("123", "asura", "test", "USER"));
        };
    }
}
