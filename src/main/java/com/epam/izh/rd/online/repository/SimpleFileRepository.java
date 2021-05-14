package com.epam.izh.rd.online.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */

    @Override

    public long countFilesInDirectory(String path) {
        int c = 0;
        File file = new File("src/main/resources/" + path);
        File[] s = file.listFiles();
        if (s == null) return 0;

        for (int j = 0; j < s.length; j++) {
            if (s[j].isFile())
                c++;
            else
                c += countFilesInDirectory(path + "/" + s[j].getName());
        }
        return c;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        long count = 1;
        File f = new File("./src/main/resources/" + path);
        File[] files = f.listFiles();
        for (File file : files) {
            if (file.isDirectory())
                count += countDirsInDirectory(path + "/" + file.getName());
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
        Path fileFrom = Paths.get(from);
        Path fileTo = Paths.get(to);
        Path directory = fileTo.getParent();

        try {
            Files.createDirectories(directory);
            Files.copy(fileFrom, fileTo);
        } catch (IOException e) {
            e.printStackTrace();
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
    public boolean createFile(String path, String name) throws IOException {
        // Path filePath= Paths.get(path+"/"+name);
        //File filePath = new File("src/main/resources/"+path);
       // path = "C:\\gitprojects\\java-data-handling-template\\src\\main\\resources" + File.separator + "testDirCreateFile" + File.separator + "newFile.txt";
// Use relative path for Unix systems
        URL url = getClass().getResource("/");
        File f = new File(url.getPath()+path+File.separator+name);

        f.getParentFile().mkdirs();
        //f.createNewFile();

        return f.createNewFile();
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        List<String> fileContent = null;
        try {
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get("src/main/resources/readme.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent.get(0);
    }
}
