package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.service.OfferService;
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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UploadChequePhotoCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(UploadChequePhotoCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReservationService reservationService = ServiceFactory.getInstance().getReservationService();

        try {
            int reservationId = Integer.parseInt(request.getParameter("reservationId"));
            File path = new File(ConfigurationManager.getInstance().getProperty("path.photo.cheque"));

            if (!path.exists()) {
                path.mkdir();
            }

            Part filePart = request.getPart("file");
            String suffix = getFileSuffix(filePart);
            File file = File.createTempFile("cheque", suffix, path);

            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            reservationService.addChequePhoto(reservationId, file.getName());

            response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.success-page")); //Обратно в кабинет

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Something pretty bad has happened: ", e);
        }
    }


    private String getFileSuffix(Part part) throws ServiceException {
        String fileName = getFileName(part);

        if (fileName != null) {
            return fileName.substring(fileName.lastIndexOf("."));
        } else {
            throw new ServiceException("Wrong file format");
        }
    }


    private String getFileName(Part part) {

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}