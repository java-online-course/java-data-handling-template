package com.epam.izh.rd.online.repository;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        long result = 0;
        File dir = new File("src/main/resources/" + path);
        File[] arr = dir.listFiles();
        if (dir.isDirectory()) {
            for (File file : arr) {
                result += countFilesInDirectory(path + "/" + file.getName());
            }
        } else
            result++;
        return result;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        long result = 0;
        File dir = new File("src/main/resources/" + path);
        if (dir.isDirectory()) {
            result++;
            for (File file : dir.listFiles()) {
                result += countDirsInDirectory(path + "/" + file.getName());
            }
        }
        return result;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File dirFrom = new File(from);
        File dirTo = new File(to);
        if (!dirTo.getParentFile().exists()) {
            dirTo.getParentFile().mkdirs();
        }
        try {
            Files.copy(dirFrom.getAbsoluteFile().toPath(), dirTo.getAbsoluteFile().toPath());
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
        URL url = getClass().getResource("/");
        File dir = new File(url.getPath() + "/" + path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(dir.getPath() + "/" + name);
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
    public String readFileFromResources(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + fileName));

        return reader.readLine();
    }
}
