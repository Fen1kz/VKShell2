package vkshell.files.manager;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import vkshell.app.App;
import vkshell.files.manager.interfaces.IFileManager;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import vkshell.files.manager.visitors.FileVisitorRule;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Lazy
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FileManagerDefault implements IFileManager {
    private final Logger logger = LoggerFactory.getLogger(FileManagerDefault.class);

    @Autowired
    protected App app;

    @Autowired
    protected ApplicationContext ctx;

    protected Collection<Rule> rules;
    protected Map<String, String> files;

    protected FileManagerDefault() {
        rules = new HashSet<>();
        rules.add(Rule.StandartName);

        files = new HashMap<>();
    }

    protected FileVisitor<Path> getFileVisitor(Path source, Path target) {
        FileVisitor<Path> fileVisitor = new FileVisitorRule(this, source, target);

        ctx.getAutowireCapableBeanFactory().autowireBean(fileVisitor);
        return fileVisitor;
    }

    @Override
    public void clean() throws IOException {
        FileUtils.cleanDirectory(app.getPath().toFile());
    }

    @Override
    public Path makeDirs(Path dir) {
        if(!dir.toFile().exists() && !dir.toFile().mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + dir);
        }
        return dir;
    }

    @Override
    public Path makeFile(Path file, String content) throws IOException {
        return makeFile(file, content, false);
    }

    @Override
    public Path makeFile(Path file, String content, Boolean overwrite) throws IOException {
        if (!Files.exists(file) || overwrite) {
            this.makeDirs(file.getParent());
            Files.write(file, content.getBytes());
            return file;
        } else {
            logger.warn(String.format("File @%s already exists", file.toAbsolutePath()));
            throw new IllegalStateException(String.format("File @%s already exists", file.toAbsolutePath()));
        }
    }

    @Override
    public Path copyFolder(Path source, Path target) throws IOException {
        Files.walkFileTree(source, getFileVisitor(source, target));
        return target;
    }

    @Override
    public Path mergeFolder(Path source1, Path source2, Path target) throws IOException {
        copyFolder(source1, target);
        copyFolder(source2, target);
        return target;
    }

    @Override
    public void setRule(Rule rule) {
        rules.add(rule);
    }

    @Override
    public void removeRule(Rule rule) {
        rules.remove(rule);
    }

    @Override
    public boolean hasRule(Rule rule) {
        return rules.contains(rule);
    }

    @Override
    public void setByHash() {

    }

    @Override
    public Path getByHash() {
        return null;
    }
}
