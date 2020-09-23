package com.epam.izh.rd.online.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        if (path.length() < "src/main/resources/".length()) {
            path = "src/main/resources/" + path;
        }

        File file = new File(path);
        File[] directory = file.listFiles();

        long count = 0;

        if (directory != null) {
            for (File item : directory) {
                if (!item.isFile()) {
                    count += countFilesInDirectory(item.getPath());
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
        long count = 0;
        if (path.length() < "src/main/resources/".length()) {
            path = "src/main/resources/" + path;
            count++;
        }

        File file = new File(path);
        File[] directory = file.listFiles();


        if (directory != null) {
            for (File item : directory) {
                if (item.isDirectory()) {
                    count++;
                    count += countDirsInDirectory(item.getPath());
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
        File folder = new File(from);

        File[] listOfFiles = folder.listFiles();

        Path destDir = Paths.get(to);
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                try {
                    if (file.getAbsolutePath().endsWith(".txt")) {
                        Files.copy(file.toPath(), destDir.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }
            }
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
        if (path.length() < "src/main/resources/".length()) {
            path = "src/main/resources/" + path;
        }

        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file = new File(path + "/" + name);
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
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader is = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            String line;
            while ((line = is.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
