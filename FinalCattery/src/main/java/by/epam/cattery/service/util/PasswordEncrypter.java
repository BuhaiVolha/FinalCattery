package by.epam.cattery.service.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * The purpose of this class is to encrypt user's password
 * taking into account their total amount and items per page.
 *
 */
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
