package com.epam.izh.rd.online.repository;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
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

        long countFile = 0;
        long countHidden = 0;

        Path pathResult = Paths.get(path);
        if (!pathResult.isAbsolute()) {

            URL url = getClass().getClassLoader().getResource(path);
            try {
                Path pathAbsolut = Paths.get(url.toURI());
                path = pathAbsolut.toString();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        File file = new File(path);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                if (file.isHidden()) {
                    countHidden += 1;
                } else {
                    countFile += 1;
                }
            } else {
                countFile += countFilesInDirectory(files[i].getPath());
            }
        }

        return countFile + countHidden;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {

        long countKatalog = 1;

        Path pathResult = Paths.get(path);

        if (!pathResult.isAbsolute()) {

            URL url = getClass().getClassLoader().getResource(path);
            try {
                Path pathAbsolut = Paths.get(url.toURI());
                path = pathAbsolut.toString();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        File dir = new File(path);
        for (File item : dir.listFiles()) {
            if (item.isDirectory()) {
                {
                    countKatalog += countDirsInDirectory(item.getAbsolutePath());
                }
            }
        }

        return countKatalog;
    }


    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {

        Path pathResultFrom = Paths.get(from);
        Path pathResultTo = Paths.get(to);

        if (!pathResultFrom.isAbsolute()) {

            URL url = getClass().getClassLoader().getResource(from);
            try {
                Path pathAbsolut = Paths.get(url.toURI());
                from = pathAbsolut.toString();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        if (!pathResultTo.isAbsolute()) {

            URL url = getClass().getClassLoader().getResource(from);
            try {
                Path pathAbsolut = Paths.get(url.toURI());
                from = pathAbsolut.toString();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        File file = new File(from);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getPath().endsWith(".txt")) {
                Path testFile = null;
                testFile = files[i].toPath();
                try {
                    testFile = Files.copy(testFile, Paths.get(to + "\\" + files[i].getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

        Path pathResultPath = Paths.get(path);

        if (!pathResultPath.isAbsolute()) {

            try {
                Path pathAbsolut = Paths.get(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
                path = pathAbsolut.toString() + "\\" + path;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        boolean result = false;

        File folder = new File(path);
        File file = new File(path + "\\" + name);

        try {
            folder.mkdir();
            file.createNewFile();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {

        Path pathResultPath = Paths.get(fileName);

        if (!pathResultPath.isAbsolute()) {

            try {
                Path pathAbsolut = Paths.get(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
                fileName = pathAbsolut.toString() + "\\" + fileName;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        StringBuilder lines = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {

            String line;
            while ((line = reader.readLine()) != null)
                lines.append(line);

        } catch (IOException ignored) {
        }
        return lines.toString();
    }
}
