package by.epam.cattery.dao;

import by.epam.cattery.entity.User;

public interface UserDAO {
    int addUser(User user);
    boolean loginAlreadyExists(User user);
    User findUser(String login, String password);
}
