package org.mockito.testing;

public class UserNonUniqueException extends RuntimeException {
    public UserNonUniqueException() {
        super();
    }

    public UserNonUniqueException(String message) {
        super(message);
    }
}
