package vkshell.files.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import vkshell.app.App;
import vkshell.files.manager.interfaces.IFileManager;
import vkshell.files.manager.interfaces.IFileManagerString;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Path;

@Lazy
@Component
public class StringFileManagerDefault extends FileManagerDefault implements IFileManagerString {
    @Override
    public Path makeDirs(String path) {
        return makeDirs(app.getPath(path));
    }

    @Override
    public Path makeFile(String path, String content) throws IOException {
        Path file = app.getPath(path);
        return makeFile(file, content);
    }

    @Override
    public Path makeFile(String path, String content, Boolean overwrite) throws IOException {
        Path file = app.getPath(path);
        return makeFile(file, content, overwrite);
    }

    @Override
    public Path copyFolder(String source, String target) throws IOException {
        return copyFolder(app.getPath(source), app.getPath(target));
    }

    @Override
    public Path mergeFolder(String source1, String source2, String target) throws IOException {
        return mergeFolder(
                app.getPath().resolve(source1),
                app.getPath().resolve(source2),
                app.getPath().resolve(target)
        );
    }
}
