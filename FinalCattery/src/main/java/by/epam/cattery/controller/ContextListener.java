package by.epam.cattery.controller;

import by.epam.cattery.dao.connection.ConnectionPool;
import by.epam.cattery.dao.connection.ConnectionPoolException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * The class initializes and destroys the connection pool when application starts and shuts down respectively.
 *
 */
@WebListener()
public class ContextListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(ContextListener.class);

    public void contextInitialized(ServletContextEvent sce) {

        try {
            ConnectionPool.getInstance().initialize();
            logger.log(Level.DEBUG, "Connection pool was initialized");

        } catch (ConnectionPoolException e) {
            logger.log(Level.ERROR, "Connection pool wasn't initialized");
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {

        try {
            ConnectionPool.getInstance().closeConnectionQueue();
            logger.log(Level.DEBUG, "Connection pool was closed");
            ConnectionPool.getInstance().deregisterAllDrivers();
            logger.log(Level.DEBUG, "Drivers were deregistered");

        } catch (ConnectionPoolException e) {
            logger.log(Level.ERROR, "Connection pool wasn't closed");
        }
    }
}
