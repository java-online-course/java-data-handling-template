package com.epam.izh.rd.online.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SimpleFileRepository implements FileRepository {
    static long countFiles = 0;
    static long countDir = 1; //1 для того, что бы сосчитать саму ROOT

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        File dir = new File(path);

        File[] files = dir.listFiles();
        if (files == null) {
            return 0;
        }
        for (File need : files) {
            if (need.isDirectory()) {
                countFilesInDirectory(need.getPath());
            }
            if (need.isFile()) {
                countFiles++;
            }
        }
        return countFiles;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        File dir = new File(path);

        File[] files = dir.listFiles();
        if (files == null) {
            return 0;
        }
        for (File need : files) {
            if (need.isDirectory()) {
                countDir++;
                countDirsInDirectory(need.getPath());
            }

        }
        return countDir;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    //В тестах вабще забыли про тест этого метода
    @Override
    public void copyTXTFiles(String from, String to) {
        File dir = new File(from + "\\");
        File[] files = dir.listFiles();
        assert files != null;
        for (File need : files) {
            //В задании про подпапки нечего не сказано, да и работют, они в моей реализации не очень, файл папкаОткуда\Подпапка\файл.txt копируется в
            // папкуКуда\файл.txt, подпапка не создается, как сделать правильно, пока ума не хватает
            //поэтому подпапки закоментировал
//            if (need.isDirectory()) {
//                copyTXTFiles(need.getPath(), to);
//            }
            if (need.isFile()) {
                if (need.toPath().toString().substring(need.toPath().toString().length() - 4).equalsIgnoreCase(".txt")) {
                    to = to + "\\";
                    File newFile = new File(to + need.getPath().substring((from + "\\").length()));
                    copyOneFile(need.toPath().toString(), newFile.toString());
                }
            }
        }
    }

    //Не знаю можно или нет, но делаю доп метод
    void copyOneFile(String from, String to) {
        File source = new File(from);
        File dest = new File(to);

        try {
            Files.copy(source.toPath(), dest.toPath());
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
    //У меня файл создался
    @Override
    public boolean createFile(String path, String name) {
        StringBuilder newFile = new StringBuilder();
        newFile.append(path).append(System.getProperty("file.separator")).append(name).append(".txt");
        File file = new File(newFile.toString());
        try {
            return file.createNewFile();
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
        return null;
    }
}
