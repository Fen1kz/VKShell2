package vkshell.files.utils;

import org.springframework.util.DigestUtils;

public class Utils {
    public static String md5 (String encode) {
        return DigestUtils.md5DigestAsHex(encode.getBytes());
    }
}
