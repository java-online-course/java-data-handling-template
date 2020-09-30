package com.epam.izh.rd.online.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        long count;
        Path filePath = Paths.get(path);
        File file;
        if (filePath.isAbsolute()){
            file = new File(path);
        } else {
            file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(path)).getFile());
        }

        count = new File(file.getPath()).listFiles(File::isFile).length;
        List<File> directories = Arrays.asList(new File(file.getPath()).listFiles(File::isDirectory));
        for (File directory : directories) {
            count += countFilesInDirectory(directory.toPath().toString());

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
        int count = 0;
        Path filePath = Paths.get(path);
        File file;
        if (filePath.isAbsolute()){
            file = new File(path);
        } else {
            count++;
            file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(path)).getFile());
        }

        List<File> directories = Arrays.asList(Objects.requireNonNull(new File(file.getPath()).listFiles(File::isDirectory)));
        count += directories.size();
        for(File directory : directories) {
            count += countDirsInDirectory(directory.toString());
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
        return;
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name){
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(path);
        File file;
        if (resource != null) {
             file = new File(resource.getFile(), name);
        } else {
             file = new File("", name);
        } //c
        try{
           return file.createNewFile();
       } catch (IOException e){
           System.out.println(e.getMessage());
       }
        return false;

    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile());
        String value = "";
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            value = reader.readLine();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return value;

    }
}
