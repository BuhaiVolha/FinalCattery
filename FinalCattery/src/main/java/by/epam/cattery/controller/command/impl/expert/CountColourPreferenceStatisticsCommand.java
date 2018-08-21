package by.epam.cattery.controller.command.impl.expert;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;

import by.epam.cattery.util.ConfigurationManager;

import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The command for taking colour preference statistics.
 *
 */
public class CountColourPreferenceStatisticsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(CountColourPreferenceStatisticsCommand.class);

    private static final String COLOUR_STATISTICS_PAGE = ConfigurationManager.getInstance()
            .getProperty(PathConst.COLOUR_PREFERENCES_STATISTICS_PAGE);


    /**
     *
     * Loads string with colour preference statistics, which later will be represented as pie chart.
     * Puts it into {@code requestContent}.
     * Makes forward to a page with colour preference statistics display.
     *
     * @param requestContent - {@link RequestContent) object that accumulates the data from request
     * @return {@link RequestResult) object that contains next page and the type of operation that will be performed
     * @throws ServiceException
     *
     */
    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();

        String statistics = userService.countStatistics();
        requestContent.setAttribute(RequestConst.COLOUR_PREFERENCE_STATISTICS, statistics);

        return new RequestResult(NavigationType.FORWARD, COLOUR_STATISTICS_PAGE);
    }
}
