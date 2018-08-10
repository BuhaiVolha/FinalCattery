package by.epam.cattery.controller.command.util;

import by.epam.cattery.service.exception.ServiceException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
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


    public void moveFileToAnotherDirectory(String filename, String pathFrom, String pathTo) {

        try {
            File file = new File(pathFrom + filename);
            File dest = new File(pathTo);
            FileUtils.copyFileToDirectory(file, dest);

        } catch (IOException e) {
            logger.log(Level.WARN, "Failed to move file from one directory to another", e);
        }
    }


    public String upload(Part filePart, String path, String prefixToName) throws ServiceException {
        File directory = new File(path);

        if (!directory.exists()) {
            directory.mkdir();
        }

        String suffix = getFileSuffix(filePart);
        File file;

        try (InputStream input = filePart.getInputStream()) {
            file = File.createTempFile(prefixToName, suffix, directory);
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            logger.log(Level.WARN, "Failed to upload file", e);
            throw new ServiceException(e);
        }
        return file.getName();
    }


    private String getFileSuffix(Part part) {
        String fileName = getFileName(part);

        if (fileName != null) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return "";
    }


    private String getFileName(Part part) {

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }


    public static UploadHelper getInstance() {
        return instance;
    }
}
