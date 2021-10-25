package ru.digitalleague.exam.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalleague.exam.dto.UserDTO;
import ru.digitalleague.exam.dto.UserRegistrationDTO;
import ru.digitalleague.exam.persistence.Role;
import ru.digitalleague.exam.persistence.User;
import ru.digitalleague.exam.repository.RoleRepository;
import ru.digitalleague.exam.repository.UserRepository;
import ru.digitalleague.exam.service.UserService;

import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @SneakyThrows
    @Override
    @Transactional
    public UserDTO saveUser(UserRegistrationDTO user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = Optional.ofNullable(roleRepository.findRoleByName(user.getRole()))
                .orElseThrow(() -> new RoleNotFoundException(user.getRole()));
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role);
        newUser.setRoles(roles);
        newUser = userRepository.save(newUser);

        return new UserDTO(newUser.getId(), newUser.getName(), newUser.getUsername());
    }

    @Override
    @Transactional
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Long getUserIdByUsername(String username) {
        return userRepository.findByUsername(username).getId();
    }
}