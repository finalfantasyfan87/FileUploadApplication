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
    public String uploadFile(@RequestParam(value ="userFile", required = false) MultipartFile userFile) throws IOException {
        return fileService.uploadFile(userFile);
    }
}
