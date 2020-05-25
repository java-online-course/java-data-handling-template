package com.epam.izh.rd.online.repository;

import sun.reflect.misc.FieldUtil;

import java.io.*;
import java.nio.file.Files;

public class SimpleFileRepository implements FileRepository {
    private int countFiles = 0;
    private int countDir = 0;


    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */

    @Override
    public long countFilesInDirectory(String path) {
        path = "src/main/resources/";
        File file = new File(path);
        File[] listFiles = file.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isFile()) {
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
        path = "src/main/resources/";
        File file = new File(path);
        File[] listFiles = file.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isDirectory()) {
                countDir++;
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
    public void copyTXTFiles(String from, String to) throws IOException {
        File fileFrom = new File(from);
        File fileTo = new File(to);
        Files.copy(fileFrom.toPath(), fileTo.toPath());
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
        path = "src/main/resources/";
        File filePath = new File(path);
        filePath.mkdir();
        File file = new File(filePath + "\\test.txt");
        try {
            file.createNewFile();
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
        String content = "";
        String symbol;
        try {
            FileReader fileReader = new FileReader("src/main/resources" + fileName);
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
