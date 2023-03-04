package org.mockito.testing;

import java.util.*;

public class UserRepositoryImpl implements UserRepository{
    private final List<User> users = new ArrayList<>();

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst();
    }

    @Override
    public User addUser(User user) {
        if (this.users.contains(user)) {
            throw new UserNonUniqueException("Такой пользователь уже существует!");
        } else {
            this.users.add(user);
        }
        return user;
    }
}