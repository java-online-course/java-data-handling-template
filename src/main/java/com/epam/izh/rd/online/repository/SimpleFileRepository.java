package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class SimpleFileRepository implements FileRepository {
    long countDir = 0;
    long countFile = 0;

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        File folder = new File("C:\\Users\\\u041b\u0438\u044f\\IdeaProjects\\java-data-handling-template\\src\\main\\resources\\" + path);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile())
                countFile++;
            if (file.isDirectory()) {
                String newPath = path + "\\" + file.getName();
                countFilesInDirectory(newPath);
            }
        }
        return countFile;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        File folder = new File("C:\\Users\\\u041b\u0438\u044f\\IdeaProjects\\java-data-handling-template\\src\\main\\resources\\" + path);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                countDir++;
                String newPath = path + "\\" + file.getName();
                countDirsInDirectory(newPath);
            }
        }
        return countDir + 1;
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
        if (listOfFiles != null)
            for (File file : listOfFiles) {
                try {
                    Files.copy(file.toPath(), destDir.resolve(file.getName()), REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
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
        File filePath = new File("C:\\Programs\\java-data-handling-template\\target\\classes\\" + path);
        filePath.mkdir();
        File file = new File(filePath, name);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.exists();
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        String line;
        StringBuilder string = new StringBuilder();
        try (FileReader reader = new FileReader("src\\main\\resources\\" + fileName)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {
                string.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string.toString();
    }
}