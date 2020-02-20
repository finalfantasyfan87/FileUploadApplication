package com.jenster.fileupload.bootstrap;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.IOException;
@SpringBootApplication
//WITHOUT this line, the app won't find the api endpoints
@ComponentScan(basePackages = {"com.jenster.fileupload"})

public class FileUploadApplication  extends SpringBootServletInitializer {


    public static void main(String[] args) throws IOException {
        SpringApplication.run(FileUploadApplication.class, args);

    }

}
