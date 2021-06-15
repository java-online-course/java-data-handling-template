package com.epam.izh.rd.online.repository;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
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
        long c = 0;
        File dir = new File("src/main/resources/" + path);
        File[] dirs = dir.listFiles();
        if (dirs != null) {
            for (File file : dirs) {
                if (file.isFile()) {
                    c++;
                }
                if (file.isDirectory()) {
                    c += countFilesInDirectory(path + "/" + file.getName());
                }
            }
        }
        return c;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        long c = 1;
        File dir = new File("src/main/resources/" + path);
        File[] dirs = dir.listFiles();
        if (dirs != null) {
            for (File file : dirs) {
                if (file.isDirectory()) {
                    c++;
                }
                if (file.isDirectory()) {
                    c += countDirsInDirectory(path + "/" + file.getName())-1;
                }
            }
        }
        return c;
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
        String targetFolder = "target/classes/";
        File dir = new File(targetFolder + path);
        dir.mkdir();
        File file = new File(dir.getPath() + File.separator + name);
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
        URL resource = getClass().getClassLoader().getResource(fileName);
        File file = new File(resource.getFile());
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            return in.lines().collect(Collectors.joining());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
