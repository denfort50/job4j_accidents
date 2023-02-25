package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.data.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> save(User user) {
        Optional<User> newUser;
        try {
            newUser = Optional.of(userRepository.save(user));
        } catch (Exception exception) {
            return Optional.empty();
        }
        return newUser;
    }
}
