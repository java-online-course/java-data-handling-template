package com.epam.izh.rd.online.repository;

import java.io.*;
import java.net.URISyntaxException;
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
        Path path1 = Paths.get("./src/main/resources/" + path);
        try {
            return Files.walk(path1)
                    .parallel()
                    .filter(p -> !p.toFile().isDirectory())
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        Path path1 = Paths.get("./src/main/resources/" + path);
        try {
            return Files.walk(path1)
                    .parallel()
                    .filter(p -> p.toFile().isDirectory())
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File fileFrom = new File(from);

        if (fileFrom.listFiles() != null) {
            for (File file : fileFrom.listFiles()) {
                if (file.getName().split(".")[file.getName().split(".").length - 1].equals("txt")) {
                    fileFrom.renameTo(new File(to + File.separator + file.getName()));
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
        Path pathResultPath = Paths.get(path);

        if (!pathResultPath.isAbsolute()) {
            try {
                Path pathAbsolute = Paths.get(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
                path = pathAbsolute.toString() + "\\" + path;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        boolean result = false;
        File folder = new File(path);
        File file = new File(path + "\\" + name);
        try {
            folder.mkdir();
            file.createNewFile();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("./src/main/resources/" + fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
