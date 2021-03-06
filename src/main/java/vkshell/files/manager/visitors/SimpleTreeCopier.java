package vkshell.files.manager.visitors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.*;
import static java.nio.file.StandardCopyOption.*;


/**
 * A {@code FileVisitor} that copies a file-tree ("cp -r")
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SimpleTreeCopier implements FileVisitor<Path> {
    private final Path source;
    private final Path target;
    private final CopyOption[] options;
    private static final Logger logger = LoggerFactory.getLogger(SimpleTreeCopier.class);

    private SimpleTreeCopier(Path source, Path target) {
        this(source, target, new CopyOption[] {COPY_ATTRIBUTES, REPLACE_EXISTING });
    }

    private SimpleTreeCopier(Path source, Path target, CopyOption... options) {
        this.source = source;
        this.target = target;
        this.options = options;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        // before visiting entries in a directory we copy the directory
        // (okay if directory already exists).
        Path newdir = target.resolve(source.relativize(dir));
        try {
            if (!newdir.toFile().exists()) {
                Files.copy(dir, newdir, options);
            }
        } catch (IOException x) {
            logger.error("Unable to create: %s: %s%n", newdir, x);
            return SKIP_SUBTREE;
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        Path targetFile = target.resolve(source.relativize(file));
        try {
            Files.copy(file, targetFile, options);
        } catch (IOException x) {
            logger.error("Unable to copy: %s: %s%n", source, x);
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        if (exc instanceof FileSystemLoopException) {
            logger.warn("cycle detected: " + file);
        } else {
            logger.error("Unable to copy: %s: %s%n", file, exc);
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path path, IOException e) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}