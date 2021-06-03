package com.epam.izh.rd.online.repository;import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class SimpleFileRepository implements FileRepository {


    @Override
    public long countFilesInDirectory(String path) {
        long c = 0;
        File dir = new File("src/main/resources/" + path);
        File[] dirs = dir.listFiles();
        if (dirs != null) {
            for (File file : dirs) {
                if (file.isFile()) {
                    c++;
                }
                if (file.isDirectory()) {
                    c += countFilesInDirectory(path + "/" + file.getName());
                }
            }
        }
        return c;
    }


    @Override
    public long countDirsInDirectory(String path) {
        long c = 1;
        File dir = new File("src/main/resources/" + path);
        File[] dirs = dir.listFiles();
        if (dirs != null) {
            for (File file : dirs) {
                if (file.isDirectory()) {
                    c++;
                }
                if (file.isDirectory()) {
                    c += countDirsInDirectory(path + "/" + file.getName())-1;
                }
            }
        }
        return c;
    }


    @Override
    public void copyTXTFiles(String from, String to) {
        File dirFrom = new File(from);
        File dirTo = new File(to);
        if (!dirTo.getParentFile().exists()) {
            dirTo.getParentFile().mkdirs();
        }
        try {
            Files.copy(dirFrom.getAbsoluteFile().toPath(), dirTo.getAbsoluteFile().toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean createFile(String path, String name)  {
        String targetFolder = "target/classes/";
        File dir = new File(targetFolder + path);
        dir.mkdir();
        File file = new File(dir.getPath() + File.separator + name);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public String readFileFromResources(String fileName) {
        URL resource = getClass().getClassLoader().getResource(fileName);
        File file = new File(resource.getFile());
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            return in.lines().collect(Collectors.joining());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
