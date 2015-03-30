package com.files.manager;

import com.files.DefaultFileTest;
import com.files.manager.interfaces.IFileManagerUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class FileManagerUtilsTest extends DefaultFileTest {
    private static final Logger logger = LoggerFactory.getLogger(FileManagerUtilsTest.class);

    @Autowired
    IFileManagerUtils fileManagerUtils;

    @Test
    public void normalizeFileName() throws IOException {
//        nameTest("The Group - The Name (Rmx By &Asdf).tx3",
//                "the group - The Name (rmx by &asdf).tx3",
//                "the group — The Name (rmx by &asdf).tx3",
//                "the_group_—_The_Name (rmx by &asdf).tx3",
//                "the_group___—_The_Name   (Rmx By   &Asdf).tx3",
//                "the_group___—_The_Name   (Rmx By   &!Asdf).tx3",
//                "the_group___—_The_Name   (Rmx By   &[]Asdf).tx3"
//        );
//        nameTest("Ру .ft. En & US - (R K.ft.saf8).tx3",
//                "ру .ft. en & us =- (r k.ft.saf8 ).tx3"
//        );
//        nameTest("Название - 123.tx3",
//                "название - 123.tx3",
//                "__название_-_123.tx3"
//        );
    }

    private void nameTest(String good, String... others) throws IOException {
        int c = 0;
        for (String other : others) {
            Path path = fileManager.makeFile("file" + ++c + "/" + other, other);
            String name = fileManagerUtils.getNormalizedName(path);
            assertEquals(good, name);
        }
    }

    private void antiNameTest(String good, String... others) throws IOException {
        int c = 0;
        for (String other : others) {
            Path path = fileManager.makeFile("file" + ++c + "/" + other, other);
            String name = fileManagerUtils.getNormalizedName(path);
            assertNotEquals(good, name);
        }
    }
}
