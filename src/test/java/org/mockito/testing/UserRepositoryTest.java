package org.mockito.testing;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    private User user1;
    private User user2;
    private User user3;
    private UserRepository userRepository;

    @Test
    @DisplayName("When empty user list is created then empty list will be returned")
    public void getEmptyListTest() {
        userRepository = new UserRepositoryImpl();
        List<User> expected = userRepository.getAllUsers();
        List<User> actual = new ArrayList<User>();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("When user list is created then list will be returned")
    public void getListOfUsersTest() {
        user1 = new User("user1", "u1");
        user2 = new User("user2", "u2");
        user3 = new User("user3", "u3");
        userRepository = new UserRepositoryImpl();
        List<User> expected = userRepository.getAllUsers();
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);


        List<User> actual = new ArrayList<User>();
        actual.add(user1);
        actual.add(user2);
        actual.add(user3);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("When user exists then it will be found by login")
    public void findUserByLoginWhenUserExists() {
        userRepository = new UserRepositoryImpl();
        user1 = new User("user1", "u1");
        userRepository.addUser(user1);
        Optional<User> expected = userRepository.findUserByLogin(user1.getLogin());
        Optional<User> actual = Optional.of(user1);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("When user doesn't exist then empty result will be returned")
    public void findUserByLoginWhenUserDoesNotExist() {
        userRepository = new UserRepositoryImpl();
        user1 = new User("user1", "u1");
        userRepository.addUser(user1);
        Optional<User> expected = userRepository.findUserByLogin("abc");
        Optional<User> actual = Optional.empty();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("When user exists then it will be found by login and password")
    public void findUserByLoginAndPasswordWhenUserExists() {
        userRepository = new UserRepositoryImpl();
        user1 = new User("user1", "u1");
        userRepository.addUser(user1);
        Optional<User> expected = userRepository.findUserByLoginAndPassword(user1.getLogin(), user1.getPassword());
        Optional<User> actual = Optional.of(user1);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("When password is correct but login is not then empty result will be returned")
    public void findUserByLoginAndPasswordWhenPasswordIsCorrectButLoginIsNot() {
        userRepository = new UserRepositoryImpl();
        user1 = new User("user1", "u1");
        userRepository.addUser(user1);
        Optional<User> expected = userRepository.findUserByLoginAndPassword("login", user1.getPassword());
        Optional<User> actual = Optional.empty();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("When login is correct but password is not then empty result will be returned")
    public void findUserByLoginAndPasswordWhenLoginIsCorrectButPasswordIsNot() {
        userRepository = new UserRepositoryImpl();
        user1 = new User("user1", "u1");
        userRepository.addUser(user1);
        Optional<User> expected = userRepository.findUserByLoginAndPassword(user1.getLogin(), "password");
        Optional<User> actual = Optional.empty();

        assertEquals(expected, actual);
    }
}
