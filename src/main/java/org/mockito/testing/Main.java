package org.mockito.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("user1", "u1");
        User user2 = new User("user2", "u2");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        Optional<User> actual = users.stream()
                .filter(u -> user1.getLogin().equals("u"))
                .findFirst();

        System.out.println(actual);

        Optional<User> foundByLoginAndPassword = users.stream()
                .filter(u -> user1.getLogin().equals("hjkh") && user1.getPassword().equals("u1"))
                .findFirst();

        System.out.println(foundByLoginAndPassword);
    }
}