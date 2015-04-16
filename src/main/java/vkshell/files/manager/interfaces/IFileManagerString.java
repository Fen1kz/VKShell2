package vkshell.files.manager.interfaces;

import java.io.IOException;
import java.nio.file.Path;

public interface IFileManagerString extends IFileManager {
    Path makeDirs(String path);

    Path makeFile(String path, String content) throws IOException;

    Path makeFile(String path, String content, Boolean overwrite) throws IOException;

    Path copyFolder(String source, String target) throws IOException;

    Path mergeFolder(String source1, String source2, String target) throws IOException;
}
