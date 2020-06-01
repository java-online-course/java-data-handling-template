package com.epam.izh.rd.online.repository;


import java.io.*;
import java.util.Objects;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */

    @Override
    public long countFilesInDirectory(String path) {
        File file = new File(Objects.requireNonNull(getClass()
                .getClassLoader().getResource(path)).getFile());
        int count = 0;
        for (File f : Objects.requireNonNull(file.listFiles())) {
            if (f.isDirectory()) {
                count += countFilesInDirectory(path + "/" + f.getName());
            } else count++;
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
        File file = new File(Objects.requireNonNull(getClass().getClassLoader()
                .getResource(path)).getFile());
        int count = 0;
        if (file.isDirectory()) {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                count += countDirsInDirectory(path + "/" + f.getName());
            }
            count++;
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
        File file = new File(Objects.requireNonNull(getClass()
                .getClassLoader().getResource(path)).getFile() + File.separator + name);
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
        String path = Objects.requireNonNull(getClass().getClassLoader()
                .getResource(fileName)).getPath();
        String readStr = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            readStr = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readStr;
    }
}
