package ru.digitalleague.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.digitalleague.exam.persistence.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
        Role findRoleByName(String name);
}
