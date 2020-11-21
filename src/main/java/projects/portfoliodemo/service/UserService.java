package projects.portfoliodemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projects.portfoliodemo.converter.UserConverter;
import projects.portfoliodemo.domain.model.User;
import projects.portfoliodemo.domain.repository.UserRepository;
import projects.portfoliodemo.exception.UserAlreadyExistsException;
import projects.portfoliodemo.web.command.RegisterUserCommand;

import java.util.Set;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long create(RegisterUserCommand registerUserCommand) {
        log.debug("Dane uzytkownika do zapisania : {}" , registerUserCommand);

        User userToCreate = userConverter.from(registerUserCommand);

        log.debug("Uzyskany obiekt uzytkownika do zapisu: {}", userToCreate);
        if(userRepository.existsByUsername(userToCreate.getUsername())) {
            throw new UserAlreadyExistsException(String.format("Użytkownik %s już istnieje", userToCreate.getUsername()));
        }
        userToCreate.setActive(Boolean.TRUE);
        userToCreate.setRoles(Set.of("ROLE_USER"));
        userRepository.save(userToCreate);
        log.debug("Zapisany uzytkownik: {}", userToCreate);

        return userToCreate.getId();
    }
}
