package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.DirectoryStream;
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
        long count = 0;
        File file = new File("src\\main\\resources\\" + path);
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files
            ) {
                if (f.isDirectory()) {
                    count += countFilesInDirectory(path + "\\" + f.getName());
                } else {
                    count++;
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
        long count = 1;
        File file = new File("src\\main\\resources\\" + path);
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files
            ) {
                if (f.isDirectory()) {
                    count += countDirsInDirectory(path + "\\" + f.getName());
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
        Path pathFrom = Paths.get("src\\main\\resources\\" + from);
        try (DirectoryStream<Path> files =  Files.newDirectoryStream(pathFrom)){
            for (Path path: files
                 ) {
                if (path.toString().endsWith(".txt")){
                    Files.copy(path, Paths.get("src\\main\\resources\\" + to + path.getFileName()));
                }
            }
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
        File file = new File("src\\main\\resources\\" + path);
        File file2 = new File(file, name);
        boolean results = false;
        try {
            results = (file.mkdir() && file2.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\main\\resources\\" + fileName))) {
            StringBuilder sb  = new StringBuilder();
            while (reader.ready()){
                sb.append(reader.readLine());
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } return null;
    }
}
