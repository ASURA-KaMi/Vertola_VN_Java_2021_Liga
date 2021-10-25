package ru.digitalleague.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.digitalleague.exam.persistence.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
