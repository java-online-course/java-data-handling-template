package com.epam.izh.rd.online.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        File file = new File(path);
        if (!(file.isDirectory() || file.isFile()))
        {
            URL url = getClass().getResource("/" + path);
            file = new File(url.getPath());
        }
        File[] directory = file.listFiles();

        long count = 0;

        if (directory != null) {
            for (File item : directory) {
                if (!item.isFile()) {
                    count += countFilesInDirectory(item.getPath());
                } else {
                    count++;
                }
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
        long count = 0;

        File file = new File(path);
        if (!(file.isDirectory() || file.isFile()))
        {
            URL url = getClass().getResource("/" + path);
            file = new File(url.getPath());
            count++;
        }

        File[] directory = file.listFiles();


        if (directory != null) {
            for (File item : directory) {
                if (item.isDirectory()) {
                    count++;
                    count += countDirsInDirectory(item.getPath());
                }
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
        File file = new File(to);
        createFile(file.getParent(), file.getName());

        try {
            Files.copy(Paths.get(from), Paths.get(to), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
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
        URL url = getClass().getResource("/");
        File folder = new File(url.getPath() + "/" + path);

        File file = new File(folder.getPath() + "/" + name);
        boolean isDirectoryCreated = !new File(path).isDirectory();

        try {
            if (isDirectoryCreated) {
                Files.createDirectory(Paths.get(path));
            }
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
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader is = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            String line;
            while ((line = is.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
