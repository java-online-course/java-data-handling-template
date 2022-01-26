package com.epam.izh.rd.online;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

        long count = 1;
        String directory = "";//"src//main//resources//";
        String path = "testDirCountFiles";

        long x = countDirsInDirectory( path);
        System.out.println(x);

    }

    public static long countDirsInDirectory(String source) {
        while ( source.equals("testDirCountFiles")) {
            String directory = "src//main//resources//";
            source = directory + source;
        }
        long count = 0;
        File file = new File( source);
        File[] s = file.listFiles();
        if (s != null || s.length > 0) {
            for (int j = 0; j < s.length; j++) {
                if (!s[j].isDirectory()) {
                    count++;
                } else {
                    count += countDirsInDirectory(s[j].getPath());
                }
            }
        }
        return count;

        /// триалспорт + адидас//на пушкинской
        // скетчерсы
        //тариф 720 вместе +
    }

}
