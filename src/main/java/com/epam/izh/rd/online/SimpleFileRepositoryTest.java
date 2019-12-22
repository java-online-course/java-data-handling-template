package com.epam.izh.rd.online;

import com.epam.izh.rd.online.repository.SimpleFileRepository;

import java.io.File;

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
        //Скорее всего дело в путях, что то напитули в относительных путях, по моему напрочь забыли указать папку resources, в пути
        long dir = simpleFileRepository.countDirsInDirectory("E:\\Git\\java-data-handling-template\\src\\main\\resources\\testDirCountFiles");
        System.out.println(dir);

        System.out.println(countDir("F:\\"));
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
