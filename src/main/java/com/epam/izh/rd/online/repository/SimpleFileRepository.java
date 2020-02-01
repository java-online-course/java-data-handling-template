package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleFileRepository implements FileRepository {

    private int countFIles = 0;
    private int countDir = 0;
    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    countFilesInDirectory(file.getPath());
                }
                if (file.isFile()) {
                    countFIles++;
                }
            }
        }
        return countFIles;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        File dir = new File(path);
        if (dir.isDirectory()){
            countDir++;
            for(File file:dir.listFiles()){
                if(file.isDirectory()){
                    countDirsInDirectory(file.getPath());
                }
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
    public void copyTXTFiles(String from, String to) {
        try {
            File dir = new File(from);
            for(File file: dir.listFiles()) {
                Files.copy(file.toPath(), new File(to).toPath());
            }
            } catch (IOException e) {
                e.printStackTrace();
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
        if(!file.exists()){
            try {
                file.mkdir();
                file = new File(path+"/"+name);
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file.exists();
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        fileName  = "target/classes/"+fileName;
        String s="";
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            s=reader.readLine();
            while (s!=null){
                s +="\n"+ reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
