package by.epam.cattery.service.validation;

import by.epam.cattery.entity.User;

public final class Validator {
    private Validator() {}

    public static boolean validateUserData(User user) {
        String login = user.getLogin();
        String password = user.getPassword();

        return !login.equals("") && password.length() >= 7;
    }
}
