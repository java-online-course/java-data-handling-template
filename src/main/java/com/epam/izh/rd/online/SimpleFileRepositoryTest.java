package com.epam.izh.rd.online;

import com.epam.izh.rd.online.repository.SimpleFileRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SimpleFileRepositoryTest {
    static int countFiles = 0;
    static int countDir = 1;

    public static void main(String[] args) {

        SimpleFileRepository simpleFileRepository = new SimpleFileRepository();
        //Указываю путь к папке где у меня лежит проект и получаю как раз эти 10 файлов, но тесты не проходят!!
        //Что не так??
        long files = simpleFileRepository.countFilesInDirectory("E:\\Git\\java-data-handling-template\\src\\main\\resources\\testDirCountFiles");
        System.out.println(files);
        //Аналогично, указываю путь к папке где у меня лежит проект и получаю как раз эти 7 папок, но тесты не проходят!!
        //Что не так??
        //Скорее всего дело в путях, что то напутули в относительных путях, по моему напрочь забыли указать папку resources, в пути
        long dir = simpleFileRepository.countDirsInDirectory("E:\\Git\\java-data-handling-template\\src\\main\\resources\\testDirCountFiles");
        System.out.println(dir);
//
//        System.out.println(countDir("F:\\"));
//        copyOneFile("F:\\5\\6.txt","F:\\10\\6.txt");
//        copyTXTFiles("F:\\10", "F:\\10C");
//        simpleFileRepository.copyTXTFiles("F:\\10", "F:\\10C");
//        simpleFileRepository.createFile("F:\\10C", "create");
//        System.out.println(simpleFileRepository.readFileFromResources("F:\\10\\proba.txt"));
    }

    public static void copyTXTFiles(String dirFrom, String dirTo) {
        File dir = new File(dirFrom + "\\");
        File[] files = dir.listFiles();
        assert files != null;
        for (File need : files) {
            if (need.isDirectory()) {
                copyTXTFiles(need.getPath(), dirTo);
            }
            if (need.isFile()) {
                if (need.toPath().toString().substring(need.toPath().toString().length() - 4).equalsIgnoreCase(".txt")) {
                    System.out.println(need.toPath().toString());
                    System.out.println(11);
                    dirTo = dirTo + "\\";
                    System.out.println(22);
                    File newFile = new File(dirTo + need.getPath().substring((dirFrom + "\\").length()));
                    System.out.println(newFile.toString());
                    copyOneFile(need.toPath().toString(), newFile.toString());
                }
            }
        }

    }

    public static void copyOneFile(String from, String to) {
        File source = new File(from);
        File dest = new File(to);

        try {
            Files.copy(source.toPath(), dest.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void countPlus() {
        countFiles++;
    }

    public static void countDirPlus() {
        countDir++;
    }

    public static int countFiles(String path) {
        File dir = new File(path);

        File[] files = dir.listFiles();
        assert files != null;
        for (File need : files) {
            if (need.isDirectory()) {
                countFiles(need.getPath());
            }
            if (need.isFile()) {
                countPlus();
            }
        }

        return countFiles;
    }

    public static int countDir(String path) {
        File dir = new File(path);

        File[] files = dir.listFiles();
        assert files != null;
        for (File need : files) {
            if (need.isDirectory()) {
                countDirPlus();
                countDir(need.getPath());
            }

        }

        return countDir;
    }
}
