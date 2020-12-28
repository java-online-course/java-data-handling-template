package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class SimpleFileRepository implements FileRepository {
    private static String prePathForCountFiles = "src/main/resources/";
    private static String prePathForCountDirectory = "src/main/resources/";

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директории
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        long count = 0;
        File dir = new File(prePathForCountFiles + path);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    count++;
                }
                if (file.isDirectory()) {
                    prePathForCountFiles = "";
                    count = count + countFilesInDirectory(file.getPath());
                }
            }
            return count;
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
        if (!prePathForCountDirectory.equals("")) {
            count++;
        }
        File dir = new File(prePathForCountDirectory + path);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    count++;
                    prePathForCountDirectory = "";
                    count = count + countDirsInDirectory(file.getPath());
                }
            }
            return count;
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
        File src = new File(from);
        File copyFile = new File(to);
        File copyFileDirectory = new File(copyFile.getParent());
        if (!copyFileDirectory.isDirectory()) {
            copyFileDirectory.mkdirs();
        }
        try {
            Files.copy(src.toPath(), copyFile.toPath());
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
        File dir = new File(getClass().getResource("/" + path).getPath());
        if (!dir.exists()) {
            dir.mkdirs();
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
    public String readFileFromResources(String fileName) {
        String content = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            content = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}

