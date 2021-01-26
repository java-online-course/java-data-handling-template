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

        Path pathResult = Paths.get(path);
        if (!pathResult.isAbsolute()) {

            URL url = getClass().getClassLoader().getResource(path);
            try {
                assert url != null;
                Path pathAbsolut = Paths.get(url.toURI());
                path = pathAbsolut.toString();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        File dir = new File(path);
        long countFiles = 0;
        if (dir.exists() && dir.isDirectory()) {
            for (File innerDir: dir.listFiles()) {
                if (innerDir.isFile()) {
                    countFiles++;
                } else {
                    countFiles += new SimpleFileRepository().countFilesInDirectory(innerDir.getPath());
                }
            }
        }
        return countFiles;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {

        Path pathResult = Paths.get(path);
        if (!pathResult.isAbsolute()) {

            URL url = getClass().getClassLoader().getResource(path);
            try {
                assert url != null;
                Path pathAbsolut = Paths.get(url.toURI());
                path = pathAbsolut.toString();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        File dir = new File(path);
        long countDirs = 1;
        if (dir.exists() && dir.isDirectory()) {
            for (File innerDir: dir.listFiles()) {
                if (innerDir.isDirectory()) {
                    countDirs += new SimpleFileRepository().countDirsInDirectory(innerDir.getPath());
                }
            }
        }
        return countDirs;
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

        Path pathResultPath = Paths.get(path);

        if (!pathResultPath.isAbsolute()) {

            try {
                Path pathAbsolut = Paths.get(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
                path = pathAbsolut.toString() + "\\" + path;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        File file = new File(path, name);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
                return file.exists();

            } catch (IOException e) {
                e.printStackTrace();
            }
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

        String textFromFile = "";
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/main/resources"+File.separator+fileName))){
            while (reader.ready()) {
                textFromFile += reader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textFromFile;
    }
}
