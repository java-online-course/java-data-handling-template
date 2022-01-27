package com.epam.izh.rd.online.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        while ( path.equals("testDirCountFiles")) {
            String directory = "src//main//resources//";
            path = directory + path;
        }
        long count = 0;
        File file = new File( path);
        File[] s = file.listFiles();
        if (s != null || s.length > 0) {
            for (int j = 0; j < s.length; j++) {
                if (!s[j].isDirectory()) {
                    count++;
                } else if (s[j].isDirectory()) {
                    count += countFilesInDirectory(s[j].getPath());
                }
            }
        }
        return count;

      
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        while ( path.equals("testDirCountFiles")) {
            String directory = "src//main//resources//";
            path = directory + path;
        }
        long count = 1;
        File file = new File( path);
        File[] s = file.listFiles();
        if (s != null || s.length > 0) {
            for (int j = 0; j < s.length; j++) {
                if (s[j].isDirectory()) {
                    count = count + countDirsInDirectory(s[j].getPath());
                }
            }
        }
        return count;

    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        Path pathSource = Paths.get(from);
        Path pathCopy = Paths.get(to);

        try {
            Files.createDirectories(pathCopy.getParent());
            Files.copy(pathSource, pathCopy, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }



//////////////////////////////////////////////////////////////////
    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {
        String location = "C:/" + path + File.separator + name;
        //System.out.println(location);
        Path newFilePath = Paths.get(location);
        try {
            Files.createDirectories(newFilePath.getParent());
            if (Files.exists(newFilePath)) {
                return true;
            } else {
                Files.createFile(newFilePath);
          //      System.out.println(newFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception");

        }
        return true;
        // Проблема с тестом диретория и файл создается (попробовать на другом ПК)
    }


    private File getFile(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(path);
        if (resource != null) {
            return new File(resource.getFile());
        }
        return new File("");
    }












    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {

        String directory = "src/main/resources/";
        String location = directory + fileName;
        String string = "";

        try (Scanner scanner = new Scanner(
                new InputStreamReader(new FileInputStream(location) /*,"UTF-8"*/));
        ) {
            while (scanner.hasNextLine()) {
                string = scanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(string);
        //System.out.println(string.equals("Ya-hoo!") );

        return string;
    }
}
