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
        File[] files = new File("src/main/resources/"+path).listFiles();
        if(files == null) return 0L;
        long counter = 0L;
        for(File file : files) {
            if(file.isFile()) counter++;
            else counter += countFilesInDirectory(path.concat("/").concat(file.getName()));
        }
        return counter;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        File[] files = new File("src/main/resources/"+path).listFiles();
        if(files == null) return 0L;
        long counter = 1L;
        for(File file : files) {
            if(file.isDirectory()) {
                counter += countDirsInDirectory(path.concat("/").concat(file.getName()));
            }
        }
        return counter;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File dirFrom = new File(from).getParentFile();
        File dirTo = new File(to).getParentFile();
        if(!dirTo.exists()) dirTo.mkdirs();
        File[] files = dirFrom.listFiles();
        if(files == null) return;
        for (File file : files) {
            if(file.isFile() && file.getName().endsWith(".txt")) {
                try {
                    Files.copy(file.toPath(), new File(dirTo+"/"+file.getName()).toPath());
                } catch (IOException exception) {
                    exception.printStackTrace();
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
        File folder = new File(getClass().getResource("/").getPath() + path);
        if(!folder.exists()) folder.mkdirs();
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
        StringBuilder builder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/"+fileName))) {
            String line;
            while((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
