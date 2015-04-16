package vkshell.files.manager.interfaces;

import java.nio.file.Path;

public interface IFileManagerUtils {
    String getName(Path path);

    public String getNormalizedName(Path path);
    public String getNormalizedName(String string);
}
