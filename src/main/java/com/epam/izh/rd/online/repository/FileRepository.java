package com.epam.izh.rd.online.repository;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileRepository {


    long countFilesInDirectory(String path) throws IOException;

    long countDirsInDirectory(String path);

    void copyTXTFiles(String from, String to);

    boolean createFile(String path, String name) throws IOException;

    String readFileFromResources(String fileName) throws IOException;

}
