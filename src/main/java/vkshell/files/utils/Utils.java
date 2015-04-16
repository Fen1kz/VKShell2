package vkshell.files.utils;

import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Utils {
    public static String md5 (String encode) {
        return DigestUtils.md5DigestAsHex(encode.getBytes());
    }
    public static String md5 (Path file) throws IOException {
        return DigestUtils.md5DigestAsHex(Files.readAllBytes(file));
    }
}
