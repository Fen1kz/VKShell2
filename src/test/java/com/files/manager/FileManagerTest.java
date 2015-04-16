package com.files.manager;

import com.files.DefaultFileTest;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import vkshell.files.manager.FileManagerUtils;
import vkshell.files.manager.factory.FileManagerFactoryDefault;
import vkshell.files.manager.interfaces.IFileManager;
import vkshell.files.manager.interfaces.IFileManagerString;
import vkshell.files.manager.interfaces.IFileManagerUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class FileManagerTest extends DefaultFileTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    IFileManagerUtils fileManagerUtils;

    @Test
    public void makeFile() throws IOException {
        // Simple
        fileManager.makeFile("a", "a file");
        assertFile("a", "a file");

        // Subdirectory
        fileManager.makeFile("dir1/b", "b file");
        assertFile("dir1/b", "b file");

        // Subdirectory ^ 2
        fileManager.makeFile("dir1/subdir1/c", "c file");
        assertFile("dir1/subdir1/c", "c file");
    }

    @Test
    public void makeFileDupeSilent() throws IOException {

        fileManager.makeFile("d1", "content", true);
        assertFile("d", "content");

        fileManager.makeFile("d1", "duplicate", true);
        assertFile("d", "duplicate");
    }

    @Test
    public void makeFileDupe() throws IOException {
        fileManager.makeFile("d2", "content");
        assertFile("d2", "content");

        thrown.expect(IllegalStateException.class);
        fileManager.makeFile("d2", "duplicate");
    }

    @Test
    public void mergeFolder() throws IOException {
        // Name equal, Hash equal
        fileManager.makeFile("old/a", "a file");
        fileManager.makeFile("new/a", "a file");
        //assertFile("merged/a", "a file");

        // Name equal, Hash not equal
        fileManager.makeFile("old/b", "b file");
        fileManager.makeFile("new/b", "The B file");
        //assertFile("merged/b", "The B file");

        // Name not equal, Hash equal
        fileManager.makeFile("old/c1", "c file");
        fileManager.makeFile("new/c2", "c file");
        //assertNoFile("merged/c1");
        //assertFile("merged/c2", "c file");

        // Name not equal, Hash not equal
        fileManager.makeFile("old/d1", "d1 file");
        fileManager.makeFile("new/d2", "d2 file");
        //assertFile("merged/d1", "d1 file");
        //assertFile("merged/d2", "d2 file");

        fileManager.mergeFolder("old", "new", "merged");

        assertFile("merged/a", "a file");
        assertFile("merged/b", "The B file");
        assertNoFile("merged/c1");
        assertFile("merged/c2", "c file");
        assertFile("merged/d1", "d1 file");
        assertFile("merged/d2", "d2 file");
    }

    @Test
    public void mergeFolderDiffRule() throws IOException {
        fileManager.setRule(IFileManager.Rule.MakeDiff);

        // Name equal, Hash not equal
        fileManager.makeFile("old/b", "b file");
        fileManager.makeFile("new/b", "The B file");
        //assertFile("merged/b", "The B file");

        fileManager.mergeFolder("old", "new", "merged");

        assertFile("merged/b", "The B file");
        assertFile("merged/b_DIFF", "b file");
    }

    @Test
    public void mergeFolderName() throws IOException {
        fileManager.setRule(IFileManager.Rule.StandartName);

        fileManager.makeFile("old/Hop_hej_la - la.tx3", "hop");
        fileManager.makeFile("new/!!Hop_hej_la_-_la.tx3", "hop");

        fileManager.mergeFolder("old", "new", "merged");

        assertFile("merged/" + fileManagerUtils.getNormalizedName("Hop_hej_la - la.tx3"));
    }

    @Test
    public void mergeFolderNameNonStandart() throws IOException {
        fileManager.removeRule(IFileManager.Rule.StandartName);

        fileManager.makeFile("old/Hop_hej_la - la.tx3", "hop");
        fileManager.makeFile("new/!!Hop_hej_la_-_la.tx3", "hop");

        fileManager.mergeFolder("old", "new", "merged");

        assertFile("merged/Hop_hej_la - la.tx3");
        assertFile("merged/!!Hop_hej_la_-_la.tx3");
    }
}
