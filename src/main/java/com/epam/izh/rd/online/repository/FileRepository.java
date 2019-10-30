package com.epam.izh.rd.online.repository;

public interface FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    long countFilesInDirectory(String path);

    /**
     * Метод рекурсивно подсчитывает количество папок в директории
     * @param path путь до директории
     * @return число папок, считая корень
     */
    long countDirsInDirectory(String path);

    /**
     * Метод копирует все файлы с расширением .txt
     * @param from путь откуда
     * @param to путь куда
     */
    void copyTXTFiles(String from, String to);

    /**
     * Метод создает файл на диске
     * @param path путь до нового файла
     * @param body содержимое нового файла
     * @return был ли создан файл
     */
    boolean createFile(String path, String body);

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     * @param fileName имя файла
     * @return контент
     */
    String readFileFromResources(String fileName);

}
