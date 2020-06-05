package com.epam.izh.rd.online;

import com.epam.izh.rd.online.repository.SimpleFileRepository;
import com.epam.izh.rd.online.service.SimpleBigNumbersService;

public class Main {

    public static void main(String[] args) {
//        SimpleFileRepository simpleFileRepository = new SimpleFileRepository();
//        System.out.println(simpleFileRepository.countFilesInDirectory("c:/temp/java/java3"));
//        System.out.println(simpleFileRepository.countDirsInDirectory("c:/temp/java/java3/"));
//        simpleFileRepository.copyTXTFiles("c:/temp/java/java3/", "c:/temp/java/java4/");
//        System.out.println(simpleFileRepository.createFile("c:/temp/java/java3/", "someNewFile.txt"));
//        System.out.println(simpleFileRepository.readFileFromResources("readme.txt"));

        SimpleBigNumbersService simpleBigNumbersService = new SimpleBigNumbersService();
        System.out.println(simpleBigNumbersService.getPrecisionNumber(1, 3, 2));
        System.out.println(simpleBigNumbersService.getPrimaryNumber(100));
    }


}
