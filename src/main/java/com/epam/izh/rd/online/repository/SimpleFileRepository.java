package com.epam.izh.rd.online.repository;

import java.io.*;
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
        File filesInDir = new File("src/main/resources/" + path);
        File[] files = filesInDir.listFiles();
        long count = 0;
        for (File file : files) {
            if (file.isDirectory()) {
                count = countFilesInDirectory(path + "/" + file.getName()) + count;
            } else {
                count++;
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
        File filesInDir = new File("src/main/resources/" + path);
        File[] files = filesInDir.listFiles();
        long count = 1;
        for (File file : files) {
            if (file.isDirectory()) {
                count = countDirsInDirectory(path + "/" + file.getName()) + count;
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
        File fileTo = new File(to);
        File fileFrom = new File(from);
        File fileToPath = new File(fileTo.getParent());
        if (!fileToPath.exists()) {
            fileToPath.mkdirs();
        }
        for (File file : fileFrom.getParentFile().listFiles()) {
            if (file.getName().endsWith(".txt")) {
                try {
                    Files.copy(file.toPath(), fileTo.toPath());
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
        File file = new File(path);
        File filePath = new File(file.getAbsolutePath());
        if(!filePath.exists()){
            file.mkdirs();
        }
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
        StringBuilder textFromFile = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/main/resources/" + fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                textFromFile.append(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textFromFile.toString();
    }
}
