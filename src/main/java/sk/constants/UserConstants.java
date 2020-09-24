package sk.constants;

import sk.models.User;

public class UserConstants {
    private UserConstants() {
        throw new AssertionError("UserConstants class must not be instantiated");
    }

    public static User SERVICE_USER = new User("candidatex", "qa-is-cool");
}
