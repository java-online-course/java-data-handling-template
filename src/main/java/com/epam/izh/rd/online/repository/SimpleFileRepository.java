package com.epam.izh.rd.online.repository;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.*;


public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        File folder = new File(path);
        File[] folderList = folder.listFiles();
        long count = 0;
        if (folderList == null) {
            return 0;
        }
        for (File x: folderList) {
            count++;
            if (x.isDirectory()) {
                count += countFilesInDirectory(String.valueOf(x.toPath()));
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
        File folder = new File(path);
        File[] folderList = folder.listRoots();
        long count = 0;
        if (folderList == null){
            return 0;
        }
        for (File x: folderList) {
            count++;
            count += countFilesInDirectory(String.valueOf(x.toPath()));

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
        FilenameFilter filter = (f, name) -> name.endsWith(".txt");
        File[] filesInFolder = folder.listFiles(filter);
        for (File x: filesInFolder) {
            try {
                Files.copy(x.toPath(), Paths.get(to), StandardCopyOption.REPLACE_EXISTING);
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
        if (!dir.isDirectory()){
            dir.mkdir();
        }
        File file = new File(dir, name);
        boolean isFileCreated = false;
        if (!file.isFile()){
            try {
                isFileCreated = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } //Это работает в Main, но по какой-то причине не здесь
        return isFileCreated;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        String inFile = null;
        try {
            inFile = Files.lines(Paths.get("src\\main\\resources\\" + fileName)).reduce("", String::concat);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inFile;
    }
}
