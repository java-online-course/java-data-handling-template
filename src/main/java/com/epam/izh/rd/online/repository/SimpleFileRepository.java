package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class SimpleFileRepository implements FileRepository {
    private long countFiles = 0;
    private long countDirs = 0;
    private String whatShouldBeConsidered;
    private String FILE_PATH = "C:\\Users\\37529\\Desktop\\EPAM-Tranning\\WorkWithData\\java-data-handling-template\\src\\main\\resources\\";

    @Override
    public long countFilesInDirectory(String path) throws IOException {
        whatShouldBeConsidered = "Files";
        countFiles = 0;
        return countDirsOrFiles(FILE_PATH + path);
    }

    @Override
    public long countDirsInDirectory(String path) {
        whatShouldBeConsidered = "Dirs";
        countDirs = 0;
        return countDirsOrFiles(FILE_PATH + path);
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

    @Override
    public boolean createFile(String path, String name) throws IOException {
        return new File(path + "/" + name).createNewFile(); // Тест какой-то неправильный. Пробовал и вот так,
                                                                        // тогда файл создаётся от корня, и в папке resources. Файл создаётся,
                                                                        // но тест не проходит
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) throws IOException {
        byte[] allBytesInFile = new FileInputStream("src/main/resources/" + fileName).readAllBytes();
        StringBuilder stringInFile = new StringBuilder();
        for (byte byteInArrayBytes : allBytesInFile) {
            stringInFile.append((char) byteInArrayBytes);
        }
        return stringInFile.toString();
    }

    private long countDirsOrFiles(String path) {
        File[] files = new File(path).listFiles();
        for (File file : Objects.requireNonNull(files)) {
            if(!file.isDirectory())
                countFiles++;
            else
                if(file.isDirectory()) {
                    countDirs++;
                    countDirsOrFiles(String.valueOf(file));
                }
        }
        if(whatShouldBeConsidered.equals("Dirs"))
            return countDirs + 1;
        else
            return countFiles;
    }
}
