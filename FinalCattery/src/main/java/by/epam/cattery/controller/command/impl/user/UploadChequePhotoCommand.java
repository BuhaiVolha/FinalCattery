package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.util.UploadHelper;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.service.ReservationService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;

public class UploadChequePhotoCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(UploadChequePhotoCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty("path.page.success-page");
    private static final String CHEQUE_PHOTO_SAVE_PATH = ConfigurationManager.getInstance().getProperty("path.photo.cheque");

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        ReservationService reservationService = ServiceFactory.getInstance().getReservationService();

        int reservationId = Integer.parseInt(requestContent.getParameter("reservationId"));
        Part filePart = requestContent.getPart("cheque");
        String prefixToName = "cheque";

        reservationService.addChequePhoto(reservationId, UploadHelper.getInstance()
                .upload(filePart, CHEQUE_PHOTO_SAVE_PATH, prefixToName));

        return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);
    }
}