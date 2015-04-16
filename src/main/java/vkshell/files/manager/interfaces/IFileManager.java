package vkshell.files.manager.interfaces;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Path;

public interface IFileManager {
    void clean() throws IOException;

    Path makeDirs(Path path);

    Path makeFile(Path path, String content) throws IOException;

    Path makeFile(Path file, String content, Boolean overwrite) throws IOException;

    Path copyFolder(Path source, Path target) throws IOException;

    Path mergeFolder(Path source1, Path source2, Path target) throws IOException;

    /* Rules
     */
    void setRule(Rule rule);

    void removeRule(Rule rule);

    boolean hasRule(Rule rule);

    public enum Rule {StandartName, MakeDiff}

    /* HashData
     */
    void setByHash();

    Path getByHash();
}
