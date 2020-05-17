package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    long counterOfFiles = 0;
    @Override
    public long countFilesInDirectory(String path) {
        if (!path.substring(0,3).equals("src")) path = "src" + File.separator + "main" + File.separator +"resources" + File.separator + path;
        File files = new File(path);
        File[] array = files.listFiles();
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                File file = array[i];
                if (file.isFile()) counterOfFiles++;
                if (file.isDirectory()) {
                     countFilesInDirectory(file.getPath());
                }
            }
        }
        return counterOfFiles;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */

    long counterOfDirectories = 1;
    @Override
    public long countDirsInDirectory(String path) {
        if (!path.substring(0,3).equals("src")) path = "src" + File.separator + "main" + File.separator +"resources" + File.separator + path;
        File files = new File(path);
        File[] array = files.listFiles();
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                File file = array[i];
                if (file.isDirectory()) {
                    counterOfDirectories++;
                    countDirsInDirectory(file.getPath());
                }
            }
        }
        return counterOfDirectories;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File directory = new File(to);
        File files = new File(from);
        String[] names = files.list();
        File[] paths = files.listFiles();
        for (int i = 0; i < names.length; i++) {
            if (names[i].contains(".txt")) {
                try {
                    Files.copy(paths[i].toPath(), directory.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
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
        File file = new File (path);
        file.mkdir();
        File file1 = new File(path + File.separator + name);
        try {
            return file1.createNewFile();
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
        String s = "";
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src\\main\\resources\\" + fileName))) {
            s = reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
