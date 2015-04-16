package com.files;

import com.DefaultTest;
import vkshell.app.App;
import vkshell.files.manager.interfaces.IFileManagerFactory;
import vkshell.files.manager.interfaces.IFileManagerString;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public abstract class DefaultFileTest extends DefaultTest {
    @Autowired
    protected App app;

    @Autowired
    protected IFileManagerFactory fileManagerFactory;

    protected IFileManagerString fileManager;

    @Before
    public void before() throws IOException {
        fileManager = fileManagerFactory.getFileManagerString();
        fileManager.clean();
    }

    @After
    public void after() {
//        fileManager.clean();
    }

    protected boolean assertFile(String name) {
        return assertFile(name, null);
    }

    protected boolean assertFile(String name, String content) {
        Path file = app.getPath().resolve(name);
        assertTrue(file.toFile().exists());
        try {
            if (content != null) {
                assertEquals(content, Files.readAllLines(file).get(0));
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void assertNoFile(String name) {
        Path file = app.getPath().resolve(name);
        assertFalse(file.toFile().exists());
    }
}
