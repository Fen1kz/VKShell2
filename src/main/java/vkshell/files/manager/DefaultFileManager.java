package vkshell.files.manager;

import vkshell.app.App;
import vkshell.files.manager.interfaces.IFileManager;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;

@Lazy
@Component
public class DefaultFileManager implements IFileManager {
    private final Logger logger = LoggerFactory.getLogger(DefaultFileManager.class);

    @Autowired
    protected App app;

    @Autowired
    protected ApplicationContext ctx;

    protected final Class<FileVisitor<Path>> fileVisitorClass;

    protected DefaultFileManager(Class<FileVisitor<Path>> fileVisitorClass) {
        this.fileVisitorClass = fileVisitorClass;
    }

    protected FileVisitor<Path> getFileVisitor(Path source, Path target) {
        return ctx.getBean(fileVisitorClass, source, target);
    }

    @Override
    public void clean() throws IOException {
        FileUtils.cleanDirectory(app.getPath().toFile());
    }

    @Override
    public Path makeDirs(Path dir) {
        if(!dir.toFile().exists() && !dir.toFile().mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + dir);
        }
        return dir;
    }

    @Override
    public Path makeFile(Path file, String content) throws IOException {
        if (!Files.exists(file)) {
            this.makeDirs(file.getParent());
            Files.write(file, content.getBytes());
            return file;
        } else {
            logger.warn(String.format("File @%s already exists", file.toAbsolutePath()));
            throw new IllegalStateException(String.format("File @%s already exists", file.toAbsolutePath()));
        }
    }

    @Override
    public Path copyFolder(Path source, Path target) throws IOException {
        Files.walkFileTree(source, getFileVisitor(source, target));
        return target;
    }

    @Override
    public Path mergeFolder(Path source1, Path source2, Path target) throws IOException {
        copyFolder(source1, target);
        copyFolder(source2, target);
        return target;
    }
}
