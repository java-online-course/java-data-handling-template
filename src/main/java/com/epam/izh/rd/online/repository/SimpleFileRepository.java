package com.epam.izh.rd.online.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleFileRepository implements FileRepository {
    static long countFiles = 0;
    static long countDir = 1; //1 для того, что бы сосчитать саму ROOT
    final static String SEPARATOR = System.getProperty("file.separator");
    final static String TYPE_TXT = ".txt";

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        if (path == null || path.equals("")) {
            return 0;
        } //хотя может возвращать -1, что бы сразу бросалось в глаза??

        File dir = new File(path + SEPARATOR);
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
        if (path == null || path.equals("")) {
            return 0;
        } //хотя может возвращать -1, что бы сразу бросалось в глаза??

        File dir = new File(path + SEPARATOR);

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
    //В тестах совсем забыли про тест этого метода
    @Override
    public void copyTXTFiles(String from, String to) {
        if (from == null || to == null || from.equals("") || to.equals("")) {
            return;
        }

        String dirFrom = "";
        String dirTo = "";

        if (from.endsWith(SEPARATOR)) {
            dirFrom = from;
        } else {
            dirFrom = from + SEPARATOR;
        }

        if (to.endsWith(SEPARATOR)) {
            dirTo = to;
        } else {
            dirTo = to + SEPARATOR;
        }

        File dir = new File(dirFrom);
        File[] files = dir.listFiles();

        assert files != null;

        for (File filePathTXT : files) {
            //В задании про подпапки нечего не сказано, да и работют, они в моей реализации не очень, файл папкаОткуда\Подпапка\файл.txt копируется в
            // папкуКуда\файл.txt, подпапка не создается, как сделать правильно, пока ума не хватает, поэтому подпапки не делал

            if (filePathTXT.isFile()) {
                if (filePathTXT.toPath().toString().substring(filePathTXT.toPath().toString().length() - 4).equalsIgnoreCase(TYPE_TXT)) {
                    File newFile = new File(dirTo + filePathTXT.getPath().substring((dirFrom).length()));
                    copyOneFile(filePathTXT.toPath().toString(), newFile.toString());
                }
            }
        }
    }

    //Не знаю можно или нет, но делаю доп метод
    void copyOneFile(String from, String to) {
        if (from == null || to == null || from.equals("") || to.equals("")) {
            return;
        }

        File fileFrom = new File(from);
        File fileTo = new File(to);

        if (!fileFrom.exists()) {
            return;
        }

        if (fileTo.exists()) {
            return;
        }

        try {
            Files.copy(fileFrom.toPath(), fileTo.toPath());
        } catch (Exception e) {
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
    //У меня файл создался, но тест не пройден
    @Override
    public boolean createFile(String path, String name) {
        if (name == null || name.equals("") || path == null || path.equals("")) {
            return false;
        }

        String pathWithSeparator = "";

        if (path.endsWith(SEPARATOR)) {
            pathWithSeparator = path;
        } else {
            pathWithSeparator = path + SEPARATOR;
        }

        String filePathIS = "";

        if (!name.endsWith(TYPE_TXT)) {
            filePathIS = pathWithSeparator + name + TYPE_TXT;
        } else {
            filePathIS = path + name;
        }

        File file = new File(filePathIS);

        if (file.exists()) {
            return false;
        } //не знаю, правильно или нет, но если файл существует, возвращаю false, т.к. СОЗДАНИЯ файла не было

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
    //Странно, но тест прошел, в своей SimpleFileRepositoryTest, не делаю
    @Override
    public String readFileFromResources(String fileName) {
        if (fileName == null || fileName.equals("")) {
            return null;
        }

        String filePathIS = "";
        //Дописал жесткую связку в src\main\resources + добявляю .txt, если не указали
        if (!fileName.endsWith(TYPE_TXT)) {
            filePathIS = "src" + SEPARATOR + "main" + SEPARATOR + "resources" + SEPARATOR + fileName + TYPE_TXT;
        } else {
            filePathIS = "src" + SEPARATOR + "main" + SEPARATOR + "resources" + SEPARATOR + fileName;
        }

        try {
            return new String(Files.readAllBytes(Paths.get(filePathIS)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
