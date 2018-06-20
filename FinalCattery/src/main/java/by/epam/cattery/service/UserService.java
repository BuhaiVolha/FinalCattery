package by.epam.cattery.service;

import by.epam.cattery.entity.User;

public interface UserService {
    int register(User user);
    User logIn(String login, String password);
}
