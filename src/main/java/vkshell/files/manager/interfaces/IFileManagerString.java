package vkshell.files.manager.interfaces;

import java.io.IOException;
import java.nio.file.Path;

public interface IFileManagerString extends IFileManager {
    public Path makeFile(String path, String content) throws IOException;

    public Path makeDirs(String path);

    public Path copyFolder(String source, String target) throws IOException;

    public Path mergeFolder(String source1, String source2, String target) throws IOException;
}
