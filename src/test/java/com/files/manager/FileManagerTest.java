package com.files.manager;

import com.files.DefaultFileTest;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileManagerTest extends DefaultFileTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void makeFile() throws IOException {
        fileManager.makeFile("a", "a file");
        Path file = app.getPath().resolve("a");
        assertTrue(file.toFile().exists());
        assertEquals("a file", Files.readAllLines(file).get(0));

        fileManager.makeFile("dir1/b", "b file");
        assertTrue(file.toFile().exists());

        fileManager.makeFile("dir1/subdir1/c", "c file");
        assertTrue(file.toFile().exists());

        fileManager.makeFile("d", "content");
        thrown.expect(IllegalStateException.class);
        fileManager.makeFile("d", "duplicate");
    }

    @Test
    public void mergeDirs() throws IOException {
        fileManager.makeFile("old/a", "a file");
        fileManager.makeFile("old/b", "b file");
        fileManager.makeFile("old/c", "c file");
        fileManager.makeFile("new/b", "The B File");
        fileManager.makeFile("new/c", "The C File");
        fileManager.makeFile("new/d", "The D File");
        fileManager.mergeFolder("old", "new", "merged");

        Path file;

        file = app.getPath().resolve("merged/a");
        assertTrue(file.toFile().exists());
        assertEquals("a file", Files.readAllLines(file).get(0));

        file = app.getPath().resolve("merged/b");
        assertTrue(file.toFile().exists());
        assertEquals("The B File", Files.readAllLines(file).get(0));

        file = app.getPath().resolve("merged/c");
        assertTrue(file.toFile().exists());
        assertEquals("The C File", Files.readAllLines(file).get(0));

        file = app.getPath().resolve("merged/d");
        assertTrue(file.toFile().exists());
        assertEquals("The D File", Files.readAllLines(file).get(0));
    }


//    @Test
//    public void mergeDirs2() throws IOException {
//    fileManager.makeFile("old/the a file", "old");
//    fileManager.makeFile("new/the_a_file", "new");
//        fileManager.makeFile("old/the a file", "a file");
//        fileManager.makeFile("old/the", "b file");
//        fileManager.makeFile("old/c", "c file");
//        fileManager.makeFile("new/the_a_file", "The B File");
//        fileManager.makeFile("new/the_a_file_9)(", "The C File");
//        fileManager.makeFile("new/d", "The D File");
//        fileManager.mergeFolder("old", "new", "merged");
//
//        Path file;
//
//        file = app.getPath().resolve("merged/a");
//        assertTrue(file.toFile().exists());
//        assertEquals("a file", Files.readAllLines(file).get(0));
//
//        file = app.getPath().resolve("merged/b");
//        assertTrue(file.toFile().exists());
//        assertEquals("The B File", Files.readAllLines(file).get(0));
//
//        file = app.getPath().resolve("merged/c");
//        assertTrue(file.toFile().exists());
//        assertEquals("The C File", Files.readAllLines(file).get(0));
//
//        file = app.getPath().resolve("merged/d");
//        assertTrue(file.toFile().exists());
//        assertEquals("The D File", Files.readAllLines(file).get(0));
//    }

//    @Test
//    public void makeFolders () {
//        fileManager.makeStructure
//    }
}
