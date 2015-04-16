package vkshell.files.manager.visitors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import vkshell.files.manager.interfaces.IFileManager;
import vkshell.files.manager.interfaces.IFileManagerUtils;
import vkshell.files.utils.Utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FileMerger implements FileVisitor<Path> {
    private static final Logger logger = LoggerFactory.getLogger(FileMerger.class);
    private final Path source;
    private final Path target;
    private final CopyOption[] options;
    private final IFileManager fileManager;

    @Autowired
    IFileManagerUtils fileManagerUtils;

    public FileMerger(IFileManager fileManager, Path source, Path target) {
        this(fileManager, source, target, new CopyOption[] {COPY_ATTRIBUTES, REPLACE_EXISTING });
    }

    private FileMerger(IFileManager fileManager, Path source, Path target, CopyOption... options) {
        this.fileManager = fileManager;
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
    public FileVisitResult visitFile(Path sourceFile, BasicFileAttributes attrs) {
        String sourceFileHash = null;
        try {
            sourceFileHash = Utils.md5(sourceFile);
        } catch (IOException x) {
            logger.error("Unable to read: %s: %s%n", source, x);
        }

        String targetFileName;
        if (fileManager.hasRule(IFileManager.Rule.StandartName)) {
            targetFileName = fileManagerUtils.getNormalizedName(sourceFile);
        } else {
            targetFileName = fileManagerUtils.getName(sourceFile);
        }

        Path targetFileParent = target.resolve(source.relativize(sourceFile)).getParent();
        Path targetFile = targetFileParent.resolve(targetFileName);


        try {
            if (targetFile.toFile().exists()) {
                if (fileManager.hasRule(IFileManager.Rule.MakeDiff)) {
                    Files.copy(targetFile, targetFile.getParent().resolve(targetFile.getFileName() + "_DIFF"));
                } else {
                    fileManager.removeHash(Utils.md5(targetFile));
                    fileManager.removeFile(targetFile);
                }
            }

            Path hashFile = fileManager.getByHash(sourceFileHash);
            if (hashFile != null && hashFile.toFile().exists()) {
                fileManager.removeHash(Utils.md5(hashFile));
                fileManager.removeFile(hashFile);
            }

            Files.copy(sourceFile, targetFile, options);
            fileManager.addHash(sourceFileHash, targetFile);
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
