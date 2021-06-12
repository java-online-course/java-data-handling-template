package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        File dir = new File("src/main/resources/" + path);
        long counter = 0;
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                counter += countFilesInDirectory(path + "/" + file.getName());
            }
        } else {
            counter++;
        }
        return counter;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        long counter = 1;
        File dir = new File("src/main/resources/" + path);
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()){
                if (file.isDirectory()){
                    counter += countDirsInDirectory(path + "/" + file.getName());
                }
            }
        }
        return counter;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File originalDir = new File(from);
        File finalDir = new File(to);
        if (!finalDir.exists()) {
            finalDir.mkdir();
        }
        try {
            Files.copy(originalDir.toPath(), finalDir.toPath(), REPLACE_EXISTING);
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
        File directory = new File(getClass().getResource("/").getPath() + "/" + path);

        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(directory + "/" + name);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
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
        File file = new File("src/main/resources/", fileName);
        try {
            List<String> result = Files.readAllLines(file.toPath());
            return result.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
