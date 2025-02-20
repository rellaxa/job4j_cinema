package ru.job4j.cinema.service.user;

import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.repository.user.UserRepository;

import java.util.Optional;

@Repository
public class SimpleUserService implements UserService {

    private final UserRepository userRepository;

    public SimpleUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByUsername(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
