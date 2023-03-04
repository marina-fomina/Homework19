package org.mockito.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepositoryImpl userRepository;

    @InjectMocks
    UserServiceImpl userService;

    private final User user1 = new User("user1", "u1");
    private final User user2 = new User("user2", "u2");

    @Test
    @DisplayName("When users are in repository then list of all logins will be returned")
    public void getListOfAllLogins() {
        when(userRepository.getAllUsers()).thenReturn(List.of(user1, user2));
        assertThat(userService.getAllLogins()).isEqualTo(List.of(user1.getLogin(), user2.getLogin()));
    }

    @Test
    @DisplayName("When correct user is added then 'addUser' method from repository is called")
    public void addCorrectUser() {
        when(userRepository.addUser(ArgumentMatchers.any(User.class))).thenReturn(null);
        userService.addUser(user1.getLogin(), user1.getPassword());
        verify(userRepository).addUser(any());
    }

    @Test
    @DisplayName("When invalid user is passed then user service throws exception")
    public void addInvalidUser() {
        assertThatThrownBy(() -> userService.addUser(null, "psw"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Не указан логин и/ или пароль.");
        verify(userRepository, never()).addUser(any());
        verify(userRepository, never()).getAllUsers();
    }

    @Test
    @DisplayName("When existed user is passed then user service throws exception")
    public void addExistedUser() {
        when(userRepository.getAllUsers()).thenReturn(List.of(user1));
        assertThatThrownBy(() -> userService.addUser(user1.getLogin(), user1.getPassword()))
                .isInstanceOf(UserNonUniqueException.class)
                .hasMessage("Такой пользователь уже существует!");
    }

    @Test
    @DisplayName("When existed user is passed then 'findUserByLoginAndPassword' method from repository is called")
    public void findExistedUserByLoginAndPassword() {
        when(userRepository.findUserByLoginAndPassword("login", "password"))
                .thenReturn(Optional.of(new User("login", "password")));
        userService.findUserByLoginAndPassword("login", "password");
        verify(userRepository).findUserByLoginAndPassword("login", "password");
    }
}
