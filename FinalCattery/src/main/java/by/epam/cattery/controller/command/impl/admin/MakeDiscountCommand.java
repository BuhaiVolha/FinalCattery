package by.epam.cattery.controller.command.impl.admin;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.constant.SessionConst;

import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;

import by.epam.cattery.entity.Role;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.User;

import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The command for making user a discount.
 *
 */
public class MakeDiscountCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(BanUserCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.SUCCESS_PAGE);
    private static final String ACCESS_DENIED_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.ACCESS_DENIED_PAGE);


    /**
     * If user's role isn't {@code ADMIN} redirects to the access denied page with message shown.
     * Otherwise updates user's discount and redirects to the success page.
     *
     * @param requestContent - {@link RequestContent) object that accumulates the data from request
     * @return {@link RequestResult) object that contains next page and the type of operation that will be performed
     * @throws ServiceException
     *
     */
    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        String path = ACCESS_DENIED_PAGE;

        if (requestContent.getSessionAttribute(SessionConst.ROLE) == Role.ADMIN) {
            UserService userService = ServiceFactory.getInstance().getUserService();

            User user = formUser(requestContent);
            userService.makeDiscount(user);

            path = SUCCESS_PAGE;
        }

        return new RequestResult(NavigationType.REDIRECT, path);
    }


    private User formUser(RequestContent requestContent) {
        int discount = Integer.parseInt(requestContent.getParameter(RequestConst.USER_DISCOUNT));
        int userId = Integer.parseInt(requestContent.getParameter(SessionConst.ID));

        User user = new User();
        user.setId(userId);
        user.setDiscount(discount);

        return user;
    }
}
