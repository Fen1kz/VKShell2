package vkshell.files.manager.visitors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vkshell.files.manager.FileManagerDefault;
import vkshell.files.manager.interfaces.IFileManager;
import vkshell.files.utils.Utils;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.*;

/**
 * Created on 16.04.2015.
 */
public class FileScanner implements FileVisitor<Path> {
    private static final Logger logger = LoggerFactory.getLogger(FileScanner.class);
    private final IFileManager fileManager;

    public FileScanner(IFileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        try {
            String hash = Utils.md5(file);
            fileManager.addHash(hash, file);
        } catch (IOException x) {
            logger.warn("Unable to scan: %s: %s%n", file, x);
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        logger.warn("Unable to scan: %s: %s%n", file, exc);
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }
}
