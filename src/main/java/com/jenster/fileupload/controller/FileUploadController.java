package com.jenster.fileupload.controller;

import com.jenster.fileupload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
public class FileUploadController {

    @Autowired
    FileService fileService;

    @GetMapping("/detect")
    public ArrayList<String> detectFiles() throws IOException {
        return fileService.getContentOfTestFiles();
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam(value ="userFile", required = false) MultipartFile userFile) {
        if(userFile == null || userFile.isEmpty()){
            return "There is no file to upload!";
        }
    
        // Validate file type
        String contentType = userFile.getContentType();
        List<String> allowedTypes = Arrays.asList("image/jpeg", "image/png", "application/pdf");
        if(contentType == null || !allowedTypes.contains(contentType)) {
            return "Invalid file type. Only JPEG, PNG, and PDF files are allowed.";
        }
    
        // Validate file size (e.g., 10MB limit)
        if(userFile.getSize() > 10 * 1024 * 1024) {
            return "File size exceeds the maximum limit of 10MB.";
        }
    
        Path uploadDirectory = Paths.get("uploads").toAbsolutePath().normalize();
        try {
            Files.createDirectories(uploadDirectory);
        
            String originalFileName = userFile.getOriginalFilename();
            if(originalFileName == null || originalFileName.trim().isEmpty()) {
                return "Invalid filename.";
            }
        
            // Sanitize filename - remove path components and dangerous characters
            String sanitizedFileName = Paths.get(originalFileName).getFileName().toString()
                .replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");
        
            // Use UUID for better uniqueness
            String uniqueFileName = UUID.randomUUID() + "_" + sanitizedFileName;
            Path destinationFilePath = uploadDirectory.resolve(uniqueFileName);
        
            // Additional security check
            if(!destinationFilePath.startsWith(uploadDirectory.toAbsolutePath())) {
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
