package ru.digitalleague.exam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.exam.dto.UserDTO;
import ru.digitalleague.exam.dto.UserRegistrationDTO;
import ru.digitalleague.exam.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @PostMapping("/register")
    public UserDTO registerNewUser(@RequestBody UserRegistrationDTO user) {
        return userService.saveUser(user);
    }
}