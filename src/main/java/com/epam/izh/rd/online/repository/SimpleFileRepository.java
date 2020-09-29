package com.epam.izh.rd.online.repository;

import java.io.*;
import java.net.URL;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        File folder = new File("src/main/resources/" + path);
        long count = 0;
        if(folder.isDirectory()) {
            for(File file : folder.listFiles()) {
                count += countFilesInDirectory(path + "/" + file.getName());
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
        File folder = new File("src/main/resources/" + path);
        long count = 0;
        if(folder.isDirectory()) {
            count++;
            for(File file : folder.listFiles()) {
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
        File dir = new File(from);
        for (File file : dir.listFiles()) {
            if(file.getName().endsWith(".txt")) {
                try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(from))) ;
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(to)))) {
                    String line = null;
                    while((line = bufferedReader.readLine())!=null) {
                        bufferedWriter.write(line, 0, line.length());
                        bufferedWriter.newLine();
                    }
                } catch(FileNotFoundException ex){
                    ex.printStackTrace();
                } catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }
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
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(path);
        File file;

        if(resource != null) {
            file = new File(resource.getFile(), name);
        } else {
            file = new File("", name);
        }
        try {
            return file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
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
        File file = new File("src/main/resources/" + fileName);
        String result = "";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                result += line;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
