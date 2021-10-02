package com.epam.izh.rd.online.repository;

import java.io.File;
import java.io.IOException;
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
        int countFiles = 0;
        File folder = new File("src/main/resources/" + path);

        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                countFiles += countFilesInDirectory(path + "/" + file.getName());
            }
        } else {
            countFiles++;
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
        int countDirectories = 1;
        File folder = new File("src/main/resources/" + path);
        File[] files = folder.listFiles();
        if (files == null) {
            return 0;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                countDirectories += countDirsInDirectory(path + "/" + file.getName());
            }
        }
        return countDirectories;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File pathToCopy = new File(to);
        if (!pathToCopy.getParentFile().exists()) {
            pathToCopy.getParentFile().mkdirs();
        }
        File pathFromCopy = new File(from);
        try {
            Files.copy(pathFromCopy.getAbsoluteFile().toPath(), pathToCopy.getAbsoluteFile().toPath());
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
        return null;
    }
}
