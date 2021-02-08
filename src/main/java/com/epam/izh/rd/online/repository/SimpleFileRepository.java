package com.epam.izh.rd.online.repository;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        long sum = 0 ;

        File[] files = new File("./src/main/resources/" + path).listFiles() ;

        for (File file : files ) {
            if(file.isFile())
                sum++;
            else
                sum+= countFilesInDirectory(path + "/" + file.getName());
        }
        return sum;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        long sum = 1 ;

        File[] files = new File("./src/main/resources/" + path).listFiles() ;

        for (File file : files ) {
            if(file.isDirectory())
               sum+= countDirsInDirectory(path + "/" + file.getName());
        }
        return sum;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        Path file = Paths.get(from) ;
        Path fileName = file.getFileName() ;
        System.out.println(fileName);
        Path fileCopy = Paths.get(to) ;
        System.out.println(fileCopy);
        System.out.println(fileName.toString().endsWith(".txt"));
        Path directory = fileCopy.getParent() ;
        try {
             Files.createDirectories(directory) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Files.exists(Paths.get(String.valueOf(directory))));


        try {
            Files.copy(file, fileCopy) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {
        try {
            Path pathAbs = Paths.get(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()) ;
            path = pathAbs.toString() + "/" + path ;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        File fold = new File(path) ;
        File file = new File(path + "/" + name) ;
        fold.mkdir() ;
        try {
            file.createNewFile() ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.exists() ;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        try (FileReader con = new FileReader("src/main/resources/" + fileName)){
            BufferedReader content = new BufferedReader (con) ;
            String text = content.readLine();
            return text;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;

    }
}
