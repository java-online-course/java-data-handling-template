package com.epam.izh.rd.online.repository;

import java.io.File;

public class SimpleFileRepository implements FileRepository {
    static long countFiles = 0;
    static long countDir = 1; //1 для того, что бы сосчитать саму ROOT

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        File dir = new File(path);

        File[] files = dir.listFiles();
        if (files == null) {
            return 0;
        }
        for (File need : files) {
            if (need.isDirectory()) {
                countFilesInDirectory(need.getPath());
            }
            if (need.isFile()) {
                countFiles++;
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
        File dir = new File(path);

        File[] files = dir.listFiles();
        if (files == null) {
            return 0;
        }
        for (File need : files) {
            if (need.isDirectory()) {
                countDir++;
                countDirsInDirectory(need.getPath());
            }

        }
        return countDir;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
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
