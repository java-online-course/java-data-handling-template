package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.*;
import java.util.*;


public class SimpleFileRepository implements FileRepository {
    private long count;
    private ArrayList<File> fileList;

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        count = 0;
        File file = new File("src\\main\\resources\\" + path);
        if (file.isDirectory())
            fileCounter(file);

        return count;
    }

    private void fileCounter(File file) {
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                fileCounter(f);
            } else {
                ++count;
            }
        }
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        count = 0;
        File file = new File("src\\main\\resources\\" + path);
        if (file.isDirectory()) {
            ++count;
            folderCounter(file);
        }
        return count;
    }

    private void folderCounter(File file) {
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                ++count;
                folderCounter(f);
            }
        }
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        fileList = new ArrayList<>();
        File directory = new File("src\\main\\resources\\" + from);
        findAllFilesNames(directory, ".txt");
        fileSCopyTo(fileList, to);
    }

    private void findAllFilesNames(File file, String filter) {
        for (File files : file.listFiles()) {
            if (files.isDirectory()) {
                findAllFilesNames(files, filter);
            } else if (files.getName().contains(filter)) {
                fileList.add(files);
            }
        }
    }

    private void fileSCopyTo(ArrayList<File> fileList, String to) {
        for (File files : fileList) {
            fileCopy(files, to);
        }
    }

    private void fileCopy(File f, String to) {
        try {
            Files.copy(Paths.get(f.getPath()), Paths.get(to + "\\" + f.getName()));
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
    public boolean createFile(String path, String name) {
        return create(path, name);
    }

    private boolean create(String path, String name) {
        try {
            Files.createFile(Paths.get(path + "/" + name));
        } catch (NoSuchFileException noFile) {
            noFile.printStackTrace();
            createDirectory(path);
            createFile(path, name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Files.exists(Paths.get(path + "\\" + name));
    }

    private void createDirectory(String path) {
        try {
            Files.createDirectory(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        return readFile(fileName);
    }

    private String readFile(String fileName) {
        try {
            Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("src\\main\\resources\\" + fileName)));
            return sc.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
