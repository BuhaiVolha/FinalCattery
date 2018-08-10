package by.epam.cattery.controller.command.impl.expert;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.CatPedigreeType;
import by.epam.cattery.service.ReservationService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class CountPedigreeStatisticsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(CountPedigreeStatisticsCommand.class);

    private static final String ERROR_PAGE = ConfigurationManager.getInstance().getProperty("path.page.error");
    private static final String PEDIGREE_PAGE = ConfigurationManager.getInstance().getProperty("path.page.pedigree");

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        ReservationService reservationService = ServiceFactory.getInstance().getReservationService();

        Map<CatPedigreeType, Integer> pedigreeCount = reservationService.countPedigreeTypes();
        requestContent.setAttribute("pedigreeCount", pedigreeCount);

        return new RequestResult(NavigationType.FORWARD, PEDIGREE_PAGE);
    }
}
