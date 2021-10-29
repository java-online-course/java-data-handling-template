package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        String absPath = "src" + "/" + "main" + "/" + "resources" + "/";
        Path relPath;
        if (path.contains("src\\main\\resources")) {
            relPath = Path.of(path);
        } else {
            relPath = Path.of(absPath + path);
        }

        long count = 0;
        if (Files.isRegularFile(relPath)) {
            return ++count;
        }

        if (Files.isDirectory(relPath)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(relPath)) {
                for (Path files : directoryStream) {
                    count += countFilesInDirectory(files.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        String absPath = "src" + "/" + "main" + "/" + "resources" + "/";
        Path relPath;
        if (path.contains("src\\main\\resources")) {
            relPath = Path.of(path);
        } else {
            relPath = Path.of(absPath + path);
        }

        long count = 1;

        if (Files.isDirectory(relPath)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(relPath)) {
                for (Path files : directoryStream) {
                    if (Files.isRegularFile(files)) {
                        continue;
                    }
                    count += countDirsInDirectory(files.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        File dir = new File(from);

        if (!Files.exists(Paths.get(to))) {
            try{
                Files.createFile(Paths.get(to));
            } catch (IOException e) {}

        }

        if (dir.getName().endsWith(".txt")) {
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(dir));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(to))
            ) {
                byte[] buff = new byte[32 * 1024];
                int len = 0;
                while ((len = in.read(buff)) > 0)
                    out.write(buff, 0, len);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        path = Objects.requireNonNull(getClass().getResource("/")).getPath() + path;
        path = path.replaceFirst("/", "");

        if (!Files.exists(Path.of(path))) {

            try {
                Files.createDirectories(Path.of(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!Files.exists(Path.of(path + "/" + name))) {
            try {
                Files.createFile(Paths.get(path + "/" + name));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Files.exists(Path.of(path + "/" + name));
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        String line = null;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            line = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
