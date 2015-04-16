//package com.files.visitors;
//
//import DefaultFileTest;
//import vkshell.files.manager.interfaces.IFileManagerUtils;
//import vkshell.files.manager.visitors.NormalNameCopier;
//import vkshell.files.manager.visitors.SimpleTreeCopier;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.attribute.BasicFileAttributes;
//
//import static org.junit.Assert.*;
//
//public class VisitorsTests extends DefaultFileTest {
//    @Autowired
//    IFileManagerUtils fileManagerUtils;
//
//    @Test
//    public void SimpleTreeCopierTest() throws IOException {
//        Path targetDirectory = app.getPath("target");
//        Path targetFile = fileManager.makeFile("target/a", "");
//
//        Path sourceDirectory = app.getPath("source");
//        Path sourceFile = fileManager.makeFile("source/b", "");
//
//        SimpleTreeCopier visitor = ctx.getBean(SimpleTreeCopier.class, sourceDirectory, targetDirectory);
//        visitor.visitFile(sourceFile, Files.readAttributes(sourceFile, BasicFileAttributes.class));
//
//        assertTrue(app.getPath("target/b").toFile().exists());
//    }
//
//    @Test
//    public void NormalNameCopierTest() throws IOException {
//        Path sourceDirectory = app.getPath("source");
//        Path targetDirectory = app.getPath("target");
//
//        Path sourceFile = fileManager.makeFile("source/awe  aweee  asdfasdf.txt.txt !!!!", "");
//        Path targetFile = fileManager.makeDirs("target");
//
//        NormalNameCopier visitor = ctx.getBean(NormalNameCopier.class, sourceDirectory, targetDirectory);
//        visitor.visitFile(sourceFile, Files.readAttributes(sourceFile, BasicFileAttributes.class));
//
//        String s = fileManagerUtils.getNormalizedName(sourceFile.getFileName());
//        assertTrue(app.getPath("target").resolve(s).toFile().exists());
//    }
//}
