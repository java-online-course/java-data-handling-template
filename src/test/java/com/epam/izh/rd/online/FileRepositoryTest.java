package com.epam.izh.rd.online;

import com.epam.izh.rd.online.repository.FileRepository;
import com.epam.izh.rd.online.repository.SimpleFileRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileRepositoryTest {

    //    private static final String TEST_DIR_COUNT_PATH = "testDirCountFiles";
    //    private static final String TEST_DIR_CREATE_PATH = "testDirCreateFile";
    //Изменяю с разрешения
    private static final String TEST_DIR_COUNT_PATH = "src" + System.getProperty("file.separator") + "main"
            + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator")
            + "testDirCountFiles" + System.getProperty("file.separator");
    private static final String TEST_DIR_CREATE_PATH = "src" + System.getProperty("file.separator") + "main"
            + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator")
            + "testDirCreateFile" + System.getProperty("file.separator");

    private static final String TEST_FILE_TO_CREATE = "newFile.txt";

    private static FileRepository fileRepository;

    @BeforeAll
    static void setup() {
        fileRepository = new SimpleFileRepository();
    }

    @BeforeEach
    void clean() {
        //Изменяю с разрешения
        //Original File file = getFile(TEST_DIR_CREATE_PATH + "/" + TEST_FILE_TO_CREATE);
        File file = getFile(TEST_DIR_CREATE_PATH  + TEST_FILE_TO_CREATE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    @DisplayName("Тест метода FileRepository.countDirsInDirectory(String path)")
    void testCountDirsInDirectory() {
        assertEquals(7, fileRepository.countDirsInDirectory(TEST_DIR_COUNT_PATH));
    }

    @Test
    @DisplayName("Тест метода FileRepository.countFilesInDirectory(String path)")
    void testCountFilesInDirectory() {
        assertEquals(10, fileRepository.countFilesInDirectory(TEST_DIR_COUNT_PATH));
    }

    @Test
    @DisplayName("Тест метода FileRepository.createFile(String path)")
    void testCreateFile() {
        fileRepository.createFile(TEST_DIR_CREATE_PATH, TEST_FILE_TO_CREATE);

        //Изменяю с разрешения
        //Original assertTrue(getFile(TEST_DIR_CREATE_PATH + "/" + TEST_FILE_TO_CREATE).exists());
        assertTrue(getFile(TEST_DIR_CREATE_PATH  + TEST_FILE_TO_CREATE).exists());
    }

    @Test
    @DisplayName("Тест метода FileRepository.readFileFromResources(String fileName)")
    void testReadFileFromResources() {
        assertEquals("Ya-hoo!", fileRepository.readFileFromResources("readme.txt"));
    }


    private File getFile(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(path);
        //Тут почему то null хотя файл создается, соответственно
        //1 - файл не удаляется в методе void clean()
        //2 - выходит assert в testCreateFile
        if (resource != null) {
            return new File(resource.getFile());
        }
        return new File("");
    }
}
