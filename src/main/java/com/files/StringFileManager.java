//package com.files;
//
//import com.app.App;
//import com.files.manager.BaseFileManager;
//import com.files.manager.interfaces.IFileManagerString;
//import com.files.manager.visitors.SimpleTreeCopier;
//import org.apache.commons.io.FileUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.nio.file.FileVisitor;
//import java.nio.file.Files;
//import java.nio.file.Path;
//
//@Component
//public abstract class StringFileManagerDecorator extends BaseFileManager implements IFileManagerString {
//    private final Logger logger = LoggerFactory.getLogger(StringFileManagerDecorator.class);
//
//    @Autowired
//    private App app;
//
//    @Autowired
//    ApplicationContext ctx;
//
//    @Override
//    public void clean() {
//        try {
//            FileUtils.cleanDirectory(app.getPath().toFile());
//            //dir.toFile().mkdirs();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Path makeDirs(String dir) {
//        return makeDirs(app.getPath(dir));
//    }
//
//    @Override
//    public Path makeDirs(Path dir) {
//        if(!dir.toFile().exists() && !dir.toFile().mkdirs()) {
//            throw new IllegalStateException("Couldn't create dir: " + dir);
//        }
//        return dir;
//    }
//
//    @Override
//    public Path makeFile(String path, String content) throws IOException {
//        Path file = app.getPath().resolve(path);
//
//        if (!Files.exists(file)) {
//            this.makeDirs(file.getParent());
//            Files.write(file, content.getBytes());
//            return file;
//        } else {
//            logger.warn(String.format("File @%s already exists", file.toAbsolutePath()));
//            throw new IllegalStateException(String.format("File @%s already exists", file.toAbsolutePath()));
//        }
//    }
//
//    @Override
//    public Path copyFolder(Path source, Path target) throws IOException {
//        return copyFolder(source, target, new SimpleTreeCopier(source, target));
//    }
//
//    @Override
//    public Path copyFolder(Path source, Path target, FileVisitor visitor) throws IOException {
//        Files.walkFileTree(source, visitor);
//        return target;
//    }
//
//    @Override
//    public Path mergeFolder(String source1, String source2, String target, FileVisitor fileVisitor) throws IOException {
//        return mergeFolder(
//            app.getPath().resolve(source1),
//            app.getPath().resolve(source2),
//            app.getPath().resolve(target),
//            fileVisitor
//        );
//    }
//
//    @Override
//    public Path mergeFolder(Path source1, Path source2, Path target, FileVisitor fileVisitor) throws IOException {
//        copyFolder(source1, target, fileVisitor);
//        copyFolder(source2, target, fileVisitor);
//        return target;
//    }
//
//    /*
//     * PRIVATE
//     */
//}
