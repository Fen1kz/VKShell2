package com.files;

import com.DefaultTest;
import vkshell.app.App;
import vkshell.files.manager.interfaces.IFileManagerString;
import vkshell.files.manager.visitors.SimpleTreeCopier;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public abstract class DefaultFileTest extends DefaultTest {
    @Autowired
    protected App app;

    protected IFileManagerString fileManager;

    @Before
    public void before() throws IOException {
        fileManager = ctx.getBean(IFileManagerString.class, SimpleTreeCopier.class);
        fileManager.clean();
    }

    @After
    public void after() {
//        fileManager.clean();
    }
}
