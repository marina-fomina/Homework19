package org.mockito.testing;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> getAllLogins() {
        return this.userRepository.getAllUsers().
                stream().
                map(User::getLogin).
                collect(Collectors.toList());
    }

    @Override
    public void addUser(String login, String password) {
        User user = new User(login, password);

        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Не указан логин и/ или пароль.");
        }
        boolean userExists = this.userRepository
                .getAllUsers()
                .stream()
                .anyMatch(u -> u.equals(user));

        if (userExists) {
            throw new UserNonUniqueException("Такой пользователь уже существует!");
        }

        this.userRepository.addUser(new User(login, password));
    }

    @Override
    public boolean findUserByLoginAndPassword(String login, String password) {
        return this.userRepository.findUserByLoginAndPassword(login, password).isPresent();
    }
}
