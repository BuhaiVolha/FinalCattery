package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.UserDAO;
import by.epam.cattery.entity.User;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.validation.Validator;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImpl implements UserService {
    private static DAOFactory daoFactory = DAOFactory.getInstance();
    private static UserDAO userDAO = daoFactory.getUserDAO();

    @Override
    public int register(User user) {
        if (!Validator.validateUserData(user)) {
            System.err.println("user data invalid!");
        }
        if (userDAO.loginAlreadyExists(user)) {
            System.err.println("login already exists!");
        }

        String securePass = BCrypt.hashpw(user.getUserPass(), BCrypt.gensalt());
        user.setUserPass(securePass);

        int userId = userDAO.addUser(user);
        return userId;
    }


    @Override
    public User logIn(String login, String password) {
//        if (!Validator.validateUserData(user)) {
//            System.err.println("user data invalid!");
//        }

        return userDAO.findUser(login, password); // передвавать юхера просто?
    }
}
