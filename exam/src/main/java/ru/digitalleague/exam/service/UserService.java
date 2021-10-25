package ru.digitalleague.exam.service;

import ru.digitalleague.exam.dto.UserDTO;
import ru.digitalleague.exam.dto.UserRegistrationDTO;
import ru.digitalleague.exam.persistence.Role;

import java.util.List;

public interface UserService {

    UserDTO saveUser(UserRegistrationDTO user);

    Role saveRole(Role role);

    Long getUserIdByUsername(String username);
}
