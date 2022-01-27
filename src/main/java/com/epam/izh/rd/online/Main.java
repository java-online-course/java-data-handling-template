package com.epam.izh.rd.online;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    private static final String TEST_DIR_COUNT_PATH = "testDirCountFiles";
    private static final String TEST_FILE_TO_CREATE = "newFile.txt";

    public static void main( String args[]) {

        String path = "testDirCountFiles";
        String name = "newFile.txt";

        String location = path + File.separator + name;
        Path newFilePath = Paths.get(location);
        try {
            Files.createDirectories(newFilePath.getParent());
            System.out.println(newFilePath.getParent());
            if (Files.exists(newFilePath)) {
                System.out.println( "true_1" );
            } else {
                Files.createFile(newFilePath);
                System.out.println( "true_2" );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( "true_3" );

        /*
        private static final String TEST_DIR_COUNT_PATH = "testDirCountFiles";
        private static final String TEST_FILE_TO_CREATE = "newFile.txt";
*/

//        File file = add.getFile(TEST_DIR_COUNT_PATH + "/" + TEST_FILE_TO_CREATE);//
//        System.out.println( file.exists() );



    }




}
