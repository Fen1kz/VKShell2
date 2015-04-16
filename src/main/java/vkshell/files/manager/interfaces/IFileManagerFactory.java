package vkshell.files.manager.interfaces;

/**
 * Created on 16.04.2015.
 */
public interface IFileManagerFactory {
    IFileManager getFileManager();

    IFileManagerString getFileManagerString();
}
