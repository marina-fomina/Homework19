package org.mockito.testing;

import java.util.List;

public interface UserService{
    List<String> getAllLogins();

    void addUser(String login, String password);

    boolean findUserByLoginAndPassword(String login, String password);
}
