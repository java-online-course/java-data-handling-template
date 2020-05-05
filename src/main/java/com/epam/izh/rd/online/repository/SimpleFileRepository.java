package com.epam.izh.rd.online.repository;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    long c = 0;
    @Override
    public long countFilesInDirectory(String path) {
        File file = new File("src/main/resources/" + path);
        long count = 0;
        if (file.isDirectory()) {
            for (File bufferFile : Objects.requireNonNull(file.listFiles())) {
                count += countFilesInDirectory(path + "/" + bufferFile.getName());
            }
        } else {
            count++;
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

        File file = new File("src/main/resources/" + path);
        long count = 0;
        if (file.isDirectory()) {
            for (File bufferFile : Objects.requireNonNull(file.listFiles())) {
                count += countDirsInDirectory(path + "/" + bufferFile.getName());
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
        Path pathResultPath = Paths.get(path);

        if (!pathResultPath.isAbsolute()) {

            try {
                Path pathAbsolut = Paths.get(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
                path = pathAbsolut.toString() + "\\" + path;
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
        try (FileReader reader = new FileReader("src/main/resources/"+fileName)) {
            int symbol = reader.read();
            char[] buffer = new char[7];
            reader.read(buffer);
            String valueOfchar = String.valueOf(buffer);
            return valueOfchar;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}

