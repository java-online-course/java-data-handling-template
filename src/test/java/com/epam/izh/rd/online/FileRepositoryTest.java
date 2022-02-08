package com.epam.izh.rd.online;

import com.epam.izh.rd.online.repository.FileRepository;
import com.epam.izh.rd.online.repository.SimpleFileRepository;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileRepositoryTest {

  private static final String TEST_DIR_COUNT_PATH = "testDirCountFiles";
  private static final String TEST_DIR_CREATE_PATH = "testDirCreateFile";
  private static final String TEST_FILE_TO_CREATE = "newFile.txt";
  private static final String SOURCE_FILE = "fileRepository/source/TestFileToCopy.txt";
  private static final String COPY_FILE = "fileRepository/copy/TestFileToCopy.txt";

  private static FileRepository fileRepository;

  @BeforeAll
  static void setup() {
    fileRepository = new SimpleFileRepository();
  }

  @BeforeEach
  void clean() {
    File file = getFile(TEST_DIR_CREATE_PATH + "/" + TEST_FILE_TO_CREATE);
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
  @DisplayName("Тест метода FileRepository.copyTXTFiles(String from, String to)")
  void testCopyTXTFiles() {
    final File emptyFile = getFile(COPY_FILE);
    assertEquals("", emptyFile.getName());
    fileRepository.copyTXTFiles(SOURCE_FILE, COPY_FILE);
    final File sourceFile = new File(SOURCE_FILE);
    final String sourceContent = readLineByLineJava(sourceFile.getAbsolutePath());
    final File copyFile = new File(COPY_FILE);
    final String copyContent = readLineByLineJava(copyFile.getAbsolutePath());

    assertEquals(sourceContent, copyContent);

    if (copyFile.exists()) {
      copyFile.delete();
    }
  }

  @Test
  @DisplayName("Тест метода FileRepository.createFile(String path)")
  void testCreateFile() {
    fileRepository.createFile(TEST_DIR_CREATE_PATH, TEST_FILE_TO_CREATE);
  System.out.println("1213");
    assertTrue(getFile(TEST_DIR_CREATE_PATH + "/" + TEST_FILE_TO_CREATE).exists());
  }

  @Test
  @DisplayName("Тест метода FileRepository.readFileFromResources(String fileName)")
  void testReadFileFromResources() {
    assertEquals("Ya-hoo!", fileRepository.readFileFromResources("readme.txt"));
  }

  private String readLineByLineJava(String filePath) {
    StringBuilder contentBuilder = new StringBuilder();

    try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder.append(s).append("\n"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return contentBuilder.toString();
  }

  private File getFile(String path) {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource(path);
    if (resource != null) {
      return new File(resource.getFile());

    }
    return new File("");
  }
}
