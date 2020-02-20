package com.jenster.fileupload.controller;

import com.jenster.fileupload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class FileUploadController {

    @Autowired
    FileService fileService;

    @GetMapping("/detect")
    public ArrayList<String> detectFiles() throws IOException {
        return fileService.getContentOfTestFiles();
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile userFile) {
        //add some logic to upload file
        return "The File was succesfully upload!";
    }
}
