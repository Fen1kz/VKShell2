package com.files.manager;

import com.files.DefaultFileTest;
import vkshell.files.manager.interfaces.IFileManagerUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class FileManagerUtilsTest extends DefaultFileTest {
    private static final Logger logger = LoggerFactory.getLogger(FileManagerUtilsTest.class);

    @Autowired
    IFileManagerUtils fileManagerUtils;

    @Test
    public void normalizeFileName() throws IOException {
        nameTest("The Group - The Name (Rmx By &ASDF).tx3",
                "The Group - The Name (Rmx By &ASDF).tx3",
                "The Group — The Name (Rmx By &ASDF).tx3",
                "The_Group_—_The_Name (Rmx By &ASDF).tx3",
                "The_Group___—_The_Name   (Rmx By   &ASDF).tx3",
                "The_Group___—_The_Name   (Rmx By   &!ASDF).tx3",
                "The_Group___—_The_Name   (Rmx By   &[]ASDF).tx3"
        );
        nameTest("Фыв .ft. En & US - (R K.ft.saf8).tx3",
                "Фыв .ft. En & US =- (R K.ft.saf8).tx3"
        );
        nameTest("Название - 123.tx3",
                "Название - 123.tx3",
                "__Название_-_123.tx3"
        );
    }
    @Test
    public void normalizeFileName2() throws IOException {
        nameAntiTest("The Group - The Name (Rmx By &Asdf).tx3",
                "The Group - The Name (rmx by &asdf).tx3"
        );
    }

    private void nameTest(String good, String... others) throws IOException {
        int c = 0;
        for (String other : others) {
            Path path = fileManager.makeFile("file" + ++c + "/" + other, other);
            String name = fileManagerUtils.getNormalizedName(path);
            assertEquals(String.valueOf(c), good, name);
        }
    }

    private void nameAntiTest(String good, String... others) throws IOException {
        int c = 0;
        for (String other : others) {
            Path path = fileManager.makeFile("file" + ++c + "/" + other, other);
            String name = fileManagerUtils.getNormalizedName(path);
            assertNotEquals(String.valueOf(c), good, name);
        }
    }
}
