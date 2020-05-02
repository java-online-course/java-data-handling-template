package com.epam.izh.rd.online.repository;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;

public class SimpleFileRepository implements FileRepository {

    @Override
    public long countFilesInDirectory(String path) {
        File file = new File("src/main/resources/" + path);
        long count = 0;

        if (file.isDirectory()) {
            for (File bufferFile : Objects.requireNonNull(file.listFiles())) {
                count += countFilesInDirectory(path + "/" + bufferFile.getName());
            }
        } else {
            count++;
        }

        return count;
    }

    @Override
    public long countDirsInDirectory(String path) {
        File file = new File("src/main/resources/" + path);
        long count = 0;

        if (file.isDirectory()) {
            for (File bufferFile : Objects.requireNonNull(file.listFiles())) {
                count += countDirsInDirectory(path + "/" + bufferFile.getName());
            }
            count++;
        }

        return count;
    }

    @Override
    public void copyTXTFiles(String from, String to) {
        try {
            File dir = new File(from);
            for(File file: Objects.requireNonNull(dir.listFiles())) {
                Files.copy(file.toPath(), new File(to).toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean createFile(String path, String name) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(path);
        assert resource != null;
        File file = new File(resource.getPath());

        try {
            FileWriter fileWriter = new FileWriter(file.getPath() + File.separator + name);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String readFileFromResources(String fileName) {
        String path = "src/main/resources/";

        String result = "";

        try (FileReader reader = new FileReader(path + fileName);
             BufferedReader bufferedReader = new BufferedReader(reader)
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
