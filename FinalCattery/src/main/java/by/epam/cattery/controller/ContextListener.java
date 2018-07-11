package by.epam.cattery.controller;

import by.epam.cattery.dao.connection.ConnectionPool;
import by.epam.cattery.dao.connection.ConnectionPoolException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {

        try {
            ConnectionPool.getInstance().initialize();

        } catch (ConnectionPoolException e) {
            System.out.println("conn pool wasn't initialized");
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {

        try {
            ConnectionPool.getInstance().closeConnectionQueue();

        } catch (ConnectionPoolException e) {
            System.out.println("conn pool wasn't closed");
        }
    }
}
