package com.epam.izh.rd.online;

import com.epam.izh.rd.online.repository.SimpleFileRepository;

import java.io.File;

public class SimpleFileRepositoryTest {
    static int countFiles = 0;
    public static void main(String[] args) {
        int count = 0;

        SimpleFileRepository simpleFileRepository = new SimpleFileRepository();
        //указываю путь к папке где у меня лежит проект и получаю как раз эти 10 файлов, но тесты не проходят!!
        //Что не так??
       long files = simpleFileRepository.countFilesInDirectory("E:\\Git\\java-data-handling-template\\src\\main\\resources\\testDirCountFiles");
        System.out.println(files);
    }

    public static void countPlus() {
        countFiles++;
    }
    public static int count(String path) {
        File dir = new File(path);

        File[] files = dir.listFiles();
        assert files != null;
        for (File need : files) {
            if (need.isDirectory()) {
                count(need.getPath());
            }
            if (need.isFile()) {
                countPlus();
            }
        }

        return countFiles;
    }
}
