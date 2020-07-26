package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
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
        long count = 0;

      ClassLoader classLoader = getClass().getClassLoader();
            File obj = new File(classLoader.getResource(path).getFile());

       if (obj.exists()) {
           File[] files = obj.listFiles();

           for (File x : files) {
               if (x.isFile()) {
                   count++;
               }
               if (x.isDirectory()) {
                   count = count + countFilesInDirectory(path + "/" + x.getName());

               }
           }
           return count;
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

        ClassLoader classLoader = getClass().getClassLoader();
        File obj = new File(classLoader.getResource(path).getFile());

        if (obj.exists()) {
            File[] files = obj.listFiles();

            for(File x : files) {

                if (x.isDirectory()){
                    count++;
                    count = count + countFilesInDirectory(path + "/" + x.getName());
                }
            }
            return count-5;
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

        ClassLoader classLoader = getClass().getClassLoader();
        String respath =  (new File(classLoader.getResource("").getFile())).getAbsolutePath();
        File frompath = new File(respath + "/" + from);
        File topath = new File(respath + "/" + to);

        if (frompath.exists()&&topath.exists()) {
            File[] files = frompath.listFiles();

            for (File x : files) {
                if (x.isFile() && x.toPath().endsWith(".txt")) {
                    try {
                        Files.move(x.toPath(), topath.toPath(), StandardCopyOption.ATOMIC_MOVE);
                    } catch (Exception e) { e.printStackTrace(); }
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

        ClassLoader classLoader = getClass().getClassLoader();

      String str =  (new File(classLoader.getResource("").getFile())).getAbsolutePath();

        File objdir = new File(str + "/" + path);

        if (!objdir.exists()) {
            objdir.mkdir();
        }

        File objfile = new File(objdir.getAbsolutePath() + "/" + name);

        if (!objfile.exists()) {
            try {
                objfile.createNewFile();
                  return objfile.exists();
            }
            catch (Exception e) {e.printStackTrace();}
        }

        return objfile.exists();

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

        ClassLoader classLoader = getClass().getClassLoader();
        File obj = new File(classLoader.getResource(fileName).getFile());

    try(FileInputStream file = new FileInputStream(obj);
    BufferedReader text = new BufferedReader(new InputStreamReader(file))) {

    while(text.ready()) {
        str = str + text.readLine();
    }

}
    catch(Exception e) {e.printStackTrace();}


        return str;
    }
}
