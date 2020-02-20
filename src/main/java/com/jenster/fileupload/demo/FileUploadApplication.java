package com.jenster.fileupload.demo;

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
File helloSong = new File("07 Hello.jpg");
        System.out.println(  helloTextFile.exists());
        System.out.println(  helloTextFile.getAbsolutePath());
        System.out.println(  helloSong.exists());
        System.out.println(  helloSong.getAbsolutePath());
        Tika tika = new Tika();
        String detect = tika.detect(helloTextFile);
        System.out.println(detect);
        String detect2 = tika.detect(helloSong);
        System.out.println(detect2);
    }

}
