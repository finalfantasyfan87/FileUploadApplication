package com.jenster.fileupload.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public interface FileService {
    ArrayList<String> getContentOfTestFiles() throws IOException;
}
