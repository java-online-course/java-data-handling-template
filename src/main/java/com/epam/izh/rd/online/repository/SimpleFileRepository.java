package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        int count = 0;
        File file = new File("src\\main\\resources\\" + path);
        File[] files = file.listFiles();
        for (File f: files) {
            if (f.isDirectory()) {
                count += countFilesInDirectory(path + "\\" + f.getName());
            } else {
                count += 1;
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
        int count = 1;
        File file = new File("src\\main\\resources\\" + path);
        File[] files = file.listFiles();
        for (File dir: files) {
            if (dir.isDirectory()) {
                count += countDirsInDirectory(path + "\\" + dir.getName());
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
    public boolean createFile(String path, String name) {

        File dir = new File("src\\main\\resources\\" + path);
        File file = new File(dir, name);
        boolean createFile = false;
        try {
            boolean mkdir = dir.mkdir();
            if (mkdir) {
                createFile = file.createNewFile();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return createFile;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {

        String path = "src\\main\\resources\\" + fileName;
        String content = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            content = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
