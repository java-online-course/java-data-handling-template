package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        if (!Paths.get(path).toFile().isDirectory()) return 0;

        try {
            final long[] count = {0};
            Files.walkFileTree(Paths.get(path), new SimpleFileVisitor() {
                @Override
                public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
                    count[0]++;
                    return super.visitFile(path, attrs);
                }
            });
            return count[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        if (!Paths.get(path).toFile().isDirectory()) return 0;

        try {
            final long[] count = {0};
            Files.walkFileTree(Paths.get(path), new SimpleFileVisitor() {
                @Override
                public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
                    count[0]++;
                    return super.preVisitDirectory(dir, attrs);
                }
            });
            return count[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        if (!Paths.get(from).toFile().isDirectory() || !Paths.get(to).toFile().isDirectory()) return;

        final int[] countCopiedFiles = {0};
        File[] files = Paths.get(from).toFile().listFiles();
        if (files == null) return;
        Arrays.stream(files)
                .filter(file -> !file.isDirectory() && file.toString().toLowerCase().endsWith(".txt"))
                .forEach(file -> {
                    try {
                        Files.copy(file.toPath(), Paths.get(to + "/" + file.getName()), StandardCopyOption.REPLACE_EXISTING);
                        countCopiedFiles[0]++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        System.out.println(countCopiedFiles[0] + " files copied.");
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
        try {
//            the dir is created additionally if it's missing
            if (!Paths.get(path).toFile().exists()) {
                boolean result = Paths.get(path).toFile().mkdir();
                if (!result) {
                    return false;
                }
            }

            return Paths.get(path + "/" + name).toFile().createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        if (!fileName.toLowerCase().endsWith(".txt")) return "";

        try ( BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            StringBuilder result = new StringBuilder();
            reader.lines().forEach(line -> result.append("\n" + line));
            return result.toString().substring(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
