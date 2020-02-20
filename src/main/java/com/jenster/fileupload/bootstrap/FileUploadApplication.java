package com.jenster.fileupload.bootstrap;

import org.apache.tika.Tika;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class FileUploadApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(FileUploadApplication.class, args);
        File helloTextFile = new File("hello.txt");
        //really a mp3 file
File helloSong = new File("07 Hello.jpg");

        Tika tika = new Tika();
        String detect = tika.detect(helloTextFile);
        System.out.println(detect);
        String detect2 = tika.detect(helloSong);
        System.out.println(detect2);
    }

}
