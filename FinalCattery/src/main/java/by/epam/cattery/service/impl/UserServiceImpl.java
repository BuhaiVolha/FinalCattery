package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.UserDAO;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.User;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.UserAlreadyExistsException;
import by.epam.cattery.service.exception.ValidationFailedException;
import by.epam.cattery.service.validation.Validator;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImpl implements UserService {
    private static DAOFactory daoFactory = DAOFactory.getInstance();
    private static UserDAO userDAO = daoFactory.getUserDAO();

    @Override
    public boolean register(User user) throws ServiceException, ValidationFailedException {
        //int userId = -1; // убрать возвращение кода. эксепшн или бул

        if (!Validator.validateUserData(user)) {
            throw new ValidationFailedException("user data invalid!");
        }
        try {
            if (userDAO.loginAlreadyExists(user)) {
                throw new UserAlreadyExistsException("User already exists");
            }

            String securePass = BCrypt.hashpw(user.getUserPass(), BCrypt.gensalt());
            user.setUserPass(securePass);

            return userDAO.addUser(user);

        } catch (DAOException e) {
            throw new ServiceException("Registration failed", e);
        }
    }


    @Override
    public User logIn(String login, String password) throws ServiceException {
//        if (!Validator.validateUserData(user)) {
//            System.err.println("user data invalid!");
//        }

        try {
            return userDAO.findUser(login, password); // передвавать юхера просто?
        } catch (DAOException e) {
            throw new ServiceException("error while logging in", e);
        }
    }
}
