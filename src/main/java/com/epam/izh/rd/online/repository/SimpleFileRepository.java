package com.epam.izh.rd.online.repository;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        AtomicLong numberOfFiles = new AtomicLong(0L);

        try {
            Files.walkFileTree(this.getResourcePath(path), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (attrs.isRegularFile()) {
                        numberOfFiles.incrementAndGet();
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException | URISyntaxException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        return numberOfFiles.get();
    }

    private Path getResourcePath(String path)
            throws URISyntaxException, IllegalArgumentException {
        return Path.of(new URI(this.getResource(path)));
    }

    private String getResource(String path) {
        return String.valueOf(
                SimpleFileRepository.class.getClassLoader().getResource(path));
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        AtomicLong numberOfDirs = new AtomicLong(0L);

        try {
            Files.walkFileTree(this.getResourcePath(path), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    numberOfDirs.incrementAndGet();
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException | URISyntaxException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        return numberOfDirs.get();
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        try {
            Files.walkFileTree(Paths.get(from), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().contains(".txt")) {
                        try (FileReader fileReader = new FileReader(file.toString());
                             FileWriter fileWriter = new FileWriter(to + '/' + file.getFileName())) {
                            StringBuilder stringBuilder = new StringBuilder();
                            while (fileReader.ready()) {
                                stringBuilder.append((char) fileReader.read());
                            }

                            fileWriter.write(stringBuilder.toString());
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
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
        String resourceDir = this.getClass().getResource("/").getPath();

        File file = new File(resourceDir + path + '/' + name);
        try {
            file.getParentFile().mkdir();
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
        StringBuilder fileBody = new StringBuilder();

        try (FileReader reader = new FileReader(
                this.getResourcePath(fileName).toString())) {
            while (reader.ready()) {
                fileBody.append((char) reader.read());

            }
        } catch ( URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return fileBody.toString();
    }
}
