package vkshell.app;

import vkshell.files.manager.interfaces.IFileManager;
import vkshell.files.manager.interfaces.IFileManagerString;
import vkshell.files.manager.visitors.SimpleTreeCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.nio.file.*;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    @Autowired
    ApplicationContext context;

    private Path folder;
    private IFileManagerString fileManager;

    @Autowired
    public void setFolder(@Value("${App.working_dir:.}") String working_dir, @Value("${App.folder:default}") String folder) {
        this.folder = Paths.get(working_dir).toAbsolutePath().normalize().resolve(folder);
    }

    public Path getPath() {
        return folder;
    }

    public Path getPath(String path) {
        return this.getPath().resolve(path);
    }

    @PostConstruct
    public void init() {
        logger.info("{} started @{}", this, this.folder);
//        this.fileManager = context.getBean(IFileManagerString.class, SimpleTreeCopier.class);
    }

    public IFileManager getFileManager() {
        return fileManager;
    }
}
