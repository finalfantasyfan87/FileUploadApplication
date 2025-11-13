package com.jenster.fileupload.serviceImpl;

import com.jenster.fileupload.service.FileService;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service("fileService")
public class FileServiceImpl implements FileService {

    private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList("image/jpeg", "image/png", "application/pdf");
    private static final long MAX_FILE_SIZE_BYTES = 10 * 1024 * 1024; // 10MB
    private static final String UPLOAD_DIRECTORY = "uploads";
    private static final String FILENAME_SANITIZATION_PATTERN = "[^a-zA-Z0-9\\.\\-_]";


    @Override
    public ArrayList<String> getContentOfTestFiles() throws IOException {
        ArrayList<String> listOfFileTypes = new ArrayList();
        File helloTextFile = new File("hello.txt");
        //really a mp3 file
        File helloSong = new File("07 Hello.jpg");
        Tika tika = new Tika();
        String detect = tika.detect(helloTextFile);
        System.out.println(detect);
        String detect2 = tika.detect(helloSong);
        System.out.println(detect2);
        listOfFileTypes.add(detect);
        listOfFileTypes.add(detect2);
        return listOfFileTypes;
    }

    @Override
    public String uploadFile(MultipartFile userFile) throws IOException {
        if (userFile == null || userFile.isEmpty()) {
            return "There is no file to upload!";
        }

        // Validate file type
        String contentType = userFile.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType)) {
            return "Invalid file type. Only JPEG, PNG, and PDF files are allowed.";
        }

        // Validate file size
        if (userFile.getSize() > MAX_FILE_SIZE_BYTES) {
            return "File size exceeds the maximum limit of 10MB.";
        }

        Path uploadDirectory = Paths.get(UPLOAD_DIRECTORY).toAbsolutePath().normalize();
        try {
            Files.createDirectories(uploadDirectory);

            String originalFileName = userFile.getOriginalFilename();
            if (originalFileName == null || originalFileName.trim().isEmpty()) {
                return "Invalid filename.";
            }

            // Sanitize filename - remove path components and dangerous characters
            String sanitizedFileName = Paths.get(originalFileName).getFileName().toString()
                    .replaceAll(FILENAME_SANITIZATION_PATTERN, "_");

            // Use UUID for better uniqueness
            String uniqueFileName = UUID.randomUUID() + "_" + sanitizedFileName;
            Path destinationFilePath = uploadDirectory.resolve(uniqueFileName);

            // Additional security check
            if (!destinationFilePath.startsWith(uploadDirectory.toAbsolutePath())) {
                return "Invalid file path.";
            }

            userFile.transferTo(destinationFilePath.toFile());
            return "The file was successfully uploaded as: " + uniqueFileName;

        } catch (Exception e) {
            // Log the actual exception server-side
            // logger.error("File upload failed", e);
            return "There was an error uploading the file. Please try again.";
        }
    }
}

