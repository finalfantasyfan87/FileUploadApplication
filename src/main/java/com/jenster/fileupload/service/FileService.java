package com.jenster.fileupload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;


public interface FileService {
    ArrayList<String> getContentOfTestFiles() throws IOException;
    String uploadFile(MultipartFile file) throws IOException;
}
