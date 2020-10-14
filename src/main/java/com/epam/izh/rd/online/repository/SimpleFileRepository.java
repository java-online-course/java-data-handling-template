package com.epam.izh.rd.online.repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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
        File[] files = new File("src/main/resources/" + path).listFiles();
        long count = 0;
        for (File file : files) {
            if (file.isFile()) count++;
            else {
                count += countFilesInDirectory(path + "/" + file.getName());
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
        File[] files = new File("src/main/resources/" + path).listFiles();
        long count = 1;
        for (File file : files) {
            if (file.isDirectory()) {
                count += countDirsInDirectory(path + "/" + file.getName());
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
        File fileTo = new File(new File(to).getParent());
        if (!fileTo.exists())fileTo.mkdir();
        File[] files = new File(new File(from).getParent()).listFiles();
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                try {
                    Files.copy(file.toPath(), Paths.get(to));
                } catch (IOException e) {
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
        File folder = new File(getClass().getResource("/").getPath() + "/" + path);
        if (!folder.exists()) folder.mkdir();
        File file = new File(folder.getPath() + "/" + name);
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
        String str = "";
        try (FileReader fileReader = new FileReader(new File("src/main/resources/" + fileName))) {
            int c = 0;
            while ((c=fileReader.read())!=-1){
                str += String.valueOf((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }
}
