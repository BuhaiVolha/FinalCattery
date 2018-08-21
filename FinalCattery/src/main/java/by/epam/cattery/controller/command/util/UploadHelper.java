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

/**
 * A class that performs different operations concerning uploading images.
 */
public class UploadHelper {
    private static final Logger logger = LogManager.getLogger(UploadHelper.class);

    private static final UploadHelper instance = new UploadHelper();

    private final static String UPLOAD_PARAM_DOT = ".";
    private final static String UPLOAD_PARAM_EMPTY_STRING = "";
    private final static String UPLOAD_PARAM_CONTENT_DISPOSITION = "content-disposition";
    private final static String UPLOAD_PARAM_PARTS_DIVIDER = ";";
    private final static String UPLOAD_PARAM_FILENAME = "filename";
    private final static String UPLOAD_PARAM_PATH_SLASH = "\"";

    private UploadHelper() {
    }


    /**
     * Moves file to another directory.
     *
     * @param filename the name of the file
     * @param pathFrom the path from where the file will be taken
     * @param pathTo   the path to where the file will be moved
     *
     */
    public void moveFileToAnotherDirectory(String filename, String pathFrom, String pathTo) {

        try {
            File file = new File(pathFrom + filename);
            File dest = new File(pathTo);
            FileUtils.copyFileToDirectory(file, dest);

        } catch (IOException e) {
            logger.log(Level.WARN, "Failed to move file from one directory to another", e);
        }
    }


    /**
     * Uploads image to a path and returns generated name.
     *
     * @param filePart     the image
     * @param path         the path to where the image will be saved
     * @param prefixToName the prefix that will be used while generating of unique name
     * @return generated name
     * @throws ServiceException the service exception
     *
     */
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


    /**
     * Gets file's extension, empty string - if file was {@code null}.
     *
     */
    private String getFileSuffix(Part part) {
        String fileName = getFileName(part);

        if (fileName != null) {
            return fileName.substring(fileName.lastIndexOf(UPLOAD_PARAM_DOT));
        }
        return UPLOAD_PARAM_EMPTY_STRING;
    }


    /**
     * Returns file's name, {@code null} - otherwise.
     *
     */
    private String getFileName(Part part) {

        for (String content : part.getHeader(UPLOAD_PARAM_CONTENT_DISPOSITION).split(UPLOAD_PARAM_PARTS_DIVIDER)) {

            if (content.trim().startsWith(UPLOAD_PARAM_FILENAME)) {
                return content.substring(content.indexOf('=') + 1).trim().replace(UPLOAD_PARAM_PATH_SLASH, UPLOAD_PARAM_EMPTY_STRING);
            }
        }
        return null;
    }


    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UploadHelper getInstance() {
        return instance;
    }
}
