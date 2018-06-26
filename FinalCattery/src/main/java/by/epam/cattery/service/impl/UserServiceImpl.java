package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.UserDAO;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.User;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;
import by.epam.cattery.service.validation.Validator;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImpl implements UserService {
    private static DAOFactory daoFactory = DAOFactory.getInstance();
    private static UserDAO userDAO = daoFactory.getUserDAO();

    @Override
    public int register(User user) throws ServiceException, ValidationFailedException {
        int userId = -1;

        if (!Validator.validateUserData(user)) {
            throw new ValidationFailedException("user data invalid!");
        }
        try {
            if (userDAO.loginAlreadyExists(user)) {
                //throw new ValidationFailedException("login already exists!");
                return -1;
            }

            String securePass = BCrypt.hashpw(user.getUserPass(), BCrypt.gensalt());
            user.setUserPass(securePass);

            userId = userDAO.addUser(user);
            return userId;
        } catch (DAOException e) {
            throw new ServiceException("Registration failed", e);
        }
    }


    @Override
    public User logIn(String login, String password) {
//        if (!Validator.validateUserData(user)) {
//            System.err.println("user data invalid!");
//        }

        return userDAO.findUser(login, password); // передвавать юхера просто?
    }
}
