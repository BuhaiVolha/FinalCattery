package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.util.UploadHelper;
import by.epam.cattery.service.ReservationService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

public class UploadChequePhotoCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(UploadChequePhotoCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReservationService reservationService = ServiceFactory.getInstance().getReservationService();

        try {
            int reservationId = Integer.parseInt(request.getParameter("reservationId"));
            Part filePart = request.getPart("file");
            String prefixToName = "cheque";

            reservationService.addChequePhoto(reservationId, UploadHelper.getInstance()
                    .upload(filePart, ConfigurationManager.getInstance().getProperty("path.photo.cheque"), prefixToName));

            response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.success-page")); //Обратно в кабинет

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Something pretty bad has happened: ", e);
        }
    }
}