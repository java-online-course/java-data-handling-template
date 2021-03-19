package com.epam.izh.rd.online.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        long count = 0;
        File[] aFiles = new File("./src/main/resources/" + path).listFiles();
        for (File file : aFiles) {
            if (file.isFile()) {
                count++;
            } else {
                count += countFilesInDirectory(path + "/" + file.getName());
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
        long count = 1;
        File dir = new File("./src/main/resources/" + path);
        for (File items : dir.listFiles()) {
            if (items.isDirectory()) {
                count += countDirsInDirectory(path + "/" + items.getName());
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
        File dirFrom = new File(from).getParentFile();
        File dirTo = new File(to).getParentFile();
        if (!dirTo.exists()) dirTo.mkdirs();
        File[] files = dirFrom.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
               try{
                   Files.copy(file.toPath(), new File(dirTo + "/" + file.getName()).toPath());
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
        URL aUrl = getClass().getResource("/");
        File dir = new File(aUrl.getPath() + "/" + path);
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
    public String readFileFromResources(String fileName) {
        File aFile = new File("src/main/resources/" + fileName);
        try {
            BufferedReader bfReader = new BufferedReader(new FileReader(aFile));
            String str = bfReader.readLine();
            return str;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
