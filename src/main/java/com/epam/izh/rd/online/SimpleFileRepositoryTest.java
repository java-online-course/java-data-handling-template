package com.epam.izh.rd.online;

import com.epam.izh.rd.online.repository.SimpleFileRepository;

import java.io.File;

public class SimpleFileRepositoryTest {
    static int count = 0;
    public static void main(String[] args) {
        int count = 0;

        SimpleFileRepository simpleFileRepository = new SimpleFileRepository();
        System.out.println(count("F:\\"));
    }

    public static void countPlus() {
        count++;
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

        return count;
    }
}
