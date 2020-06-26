package com.epam.izh.rd.online;

import com.epam.izh.rd.online.repository.SimpleFileRepository;

public class Main {
    public static void main(String ... args)
    {
        String path = "testDirCountFiles";
        String path2 = "testDirCountFiles";
        SimpleFileRepository fr = new SimpleFileRepository();
        fr.copyTXTFiles(path,path2);
    }
}
