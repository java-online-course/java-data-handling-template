package com.epam.izh.rd.online.repository;

import java.io.IOException;

public interface FileRepository {


    long countFilesInDirectory(String path);

    long countDirsInDirectory(String path);

    void copyTXTFiles(String from, String to) throws IOException;

    boolean createFile(String path, String name);

    String readFileFromResources(String fileName);

}
