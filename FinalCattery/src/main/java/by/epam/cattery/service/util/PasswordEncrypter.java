package by.epam.cattery.service.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncrypter {
    private static final PasswordEncrypter instance = new PasswordEncrypter();

    private PasswordEncrypter() {
    }


    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


    public static PasswordEncrypter getInstance() {
        return instance;
    }
}
