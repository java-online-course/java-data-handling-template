package com.epam.izh.rd.online;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main( String args[]) {

        final String path = "testDirCreateFile";
        final String name = "newFile.txt";

        try {
            String location = File.separator + path + File.separator + name;
            Path newFilePath = Paths.get(location) ;
            Files.createDirectories(newFilePath.getParent());

            if(!Files.exists(newFilePath)) {
                Files.createFile(newFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("false");
            //return false;
        }
        //return true;
        System.out.println("true");



    }

}
