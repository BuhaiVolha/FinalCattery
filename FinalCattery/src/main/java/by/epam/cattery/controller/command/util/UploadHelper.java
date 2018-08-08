package by.epam.cattery.controller.command.util;

import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UploadHelper {
    private static final Logger logger = LogManager.getLogger(UploadHelper.class);

    private static final UploadHelper instance = new UploadHelper();

    private UploadHelper() {
    }

    public static UploadHelper getInstance() {
        return instance;
    }


    public String upload(Part filePart, String path, String prefixToName) throws IOException, ServiceException {
        File directory = new File(path);

        if (!directory.exists()) {
            directory.mkdir();
        }

        String suffix = getFileSuffix(filePart);
        File file = File.createTempFile(prefixToName, suffix, directory);

        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        return file.getName();
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
