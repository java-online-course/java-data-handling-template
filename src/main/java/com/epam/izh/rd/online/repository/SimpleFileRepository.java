package com.epam.izh.rd.online.repository;

import java.io.File;
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
        long count = 0;
        try {
            Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
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
        try {
            Files.walk(Paths.get(path))
                    .filter(p -> p.toFile().isDirectory())
                    .count();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
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
        Path fromPath = Paths.get(from).normalize();
        Path toPath = Paths.get(to).normalize();
        if (toPath.getParent() != null) {
            if (fromPath.endsWith(".txt") && (Files.notExists(toPath.getParent()))) {
                try {
                    Files.createDirectories(toPath.getParent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Files.copy(fromPath, toPath);
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
        boolean isCreateDir = false;
        boolean isCreateFile = false;
        Path dirPath = Paths.get(System.getProperty("user.dir") + File.separator + path).normalize();
        Path filePath = Paths.get(dirPath + File.separator + name).normalize();

        if (Files.notExists(dirPath)) {
            try {
                Files.createDirectories(dirPath);
                isCreateDir = Files.exists(dirPath);
            } catch (IOException e) {
                isCreateDir = Files.exists(dirPath);
                e.printStackTrace();
            }
            if (Files.notExists(filePath)) {
                try {
                    Files.createFile(filePath);
                    isCreateFile = Files.exists(filePath);
                } catch (IOException e) {
                    isCreateFile = Files.exists(filePath);
                    e.printStackTrace();
                }
            }

        } else if (Files.exists(dirPath) && Files.notExists(filePath)) {
            isCreateDir = Files.exists(dirPath);
            try {
                Files.createFile(filePath);
                isCreateFile = Files.exists(filePath);
            } catch (IOException e) {
                isCreateFile = Files.exists(filePath);
                e.printStackTrace();
            }

        } else {
            isCreateDir = Files.exists(dirPath);
            isCreateFile = Files.exists(filePath);
        }
        return (isCreateDir && isCreateFile);
    }


    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        Path path = Paths.get("src/main/resources" + File.separator + fileName);
        String read = "";
        try {
            read = Files.readAllLines(path).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return read;
    }
}
