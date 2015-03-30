package com.files.manager.interfaces;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Path;

public interface IFileManager {
    public void clean() throws IOException;

    public Path makeDirs(Path path);

    public Path makeFile(Path path, String content) throws IOException;

    public Path copyFolder(Path source, Path target) throws IOException;

    public Path mergeFolder(Path source1, Path source2, Path target) throws IOException;
}
