package com.epam.izh.rd.online.repository;


import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директории
     * @return файлов, в том числе скрытых
     */

    @Override
    public long countFilesInDirectory(String path) {
        File file = new File("src/main/resources/" + path);
        int countFiles = 0;
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                countFiles += countFilesInDirectory(path + "/" + f.getName());
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
        File file = new File("src/main/resources/" + path);
        int countDir = 0;
        if (file.isDirectory()) {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                countDir += countDirsInDirectory(path + "/" + f.getName());
            }
            countDir++;
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
    public void copyTXTFiles(String from, String to) throws IOException {
        File folder = new File(from);
        File[] listOfFiles = folder.listFiles();
        Path destination = Paths.get(to);
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                Files.copy(file.toPath(), destination.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
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
        boolean isCreated = false;
        try {
            Path abs = Paths.get(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
            path = abs.toString() + "\\" + path;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        File file = new File(path + "\\" + name);
        try {
            isCreated = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isCreated;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        String content = "";
        String symbol;
        try {
            FileReader fileReader = new FileReader("src/main/resources/" + fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((symbol = bufferedReader.readLine()) != null) {
                content = symbol + content;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
