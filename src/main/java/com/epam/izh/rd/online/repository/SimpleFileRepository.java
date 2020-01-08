package com.epam.izh.rd.online.repository;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        String PATH = "src/main/resources/" + path;
        File dir = new File(PATH);
        long count = 0;

        if (dir.isFile()) {
            return 1;
        }

        if (dir.list().length == 0) {
            return 0;
        }

        for (String subDir : dir.list()) {
            count += countFilesInDirectory(path + "/" + subDir);
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
        String PATH = "src/main/resources/" + path;
        File dir = new File(PATH);
        long count = 0;

        if (dir.isFile()) {
            return 0;
        }

        if (dir.isDirectory()) {
            count++;
        }

        if (dir.list().length == 0) {
            return count;
        }

        for (String subDir : dir.list()) {
            count += countDirsInDirectory(path + "/" + subDir);
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
        File source = new File(from);
        File destination = new File(to);

        if (source.isFile()) {
            return;
        }

        if (!destination.exists()) {
            destination.mkdir();
        }

        for (String fileName : source.list()) {
            if (!fileName.endsWith(".txt")) {
                continue;
            }
            try {
                File inputFile = new File(from + fileName);
                File outputFile = new File(to + "/" + fileName);
                FileInputStream fileInputStream = new FileInputStream(inputFile);
                FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

                byte[] buffer = new byte[1024];
                int length;

                while ((length = fileInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, length);
                }

                fileInputStream.close();
                fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
        File dir = new File(path);
        String fileName = path + "/" + (name.endsWith(".txt") ? name : name + ".txt");
        File file = new File(fileName);
        try {
            if (!dir.exists()) {
                dir.mkdirs();
            }

            if (file.exists()) {
                return false;
            }

            if (!file.createNewFile()) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        String PATH = "src/main/resources/";
        StringBuffer stringBuffer = new StringBuffer();

        try {
            Stream<String> stream = Files.lines(Paths.get(PATH + fileName));
            stream.forEach(s -> stringBuffer.append(s));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }
}
