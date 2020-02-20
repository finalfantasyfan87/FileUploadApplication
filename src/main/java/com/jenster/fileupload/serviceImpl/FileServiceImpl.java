package com.jenster.fileupload.serviceImpl;

import com.jenster.fileupload.service.FileService;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
@Service("fileService")
public class FileServiceImpl implements FileService {
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
}
