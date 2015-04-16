package vkshell.files.manager.factory;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import vkshell.files.manager.FileManagerDefault;
import vkshell.files.manager.interfaces.IFileManager;
import vkshell.files.manager.interfaces.IFileManagerFactory;
import vkshell.files.manager.interfaces.IFileManagerString;
import vkshell.files.manager.visitors.NormalNameCopier;

@Component
public class FileManagerFactoryDefault implements IFileManagerFactory {
    @Autowired
    ApplicationContext ctx;

    @Override
    public IFileManager getFileManager() {
        IFileManager fileManager = ctx.getBean(IFileManager.class);
        return fileManager;
    }

    @Override
    public IFileManagerString getFileManagerString() {
        IFileManagerString fileManager = ctx.getBean(IFileManagerString.class);
        return fileManager;
    }
}
