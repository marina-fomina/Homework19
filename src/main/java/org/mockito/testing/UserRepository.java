package org.mockito.testing;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository {
    List<User> getAllUsers();

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserByLoginAndPassword(String login, String password);

    User addUser(User user);
}
