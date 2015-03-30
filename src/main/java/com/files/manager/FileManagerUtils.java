package com.files.manager;

import com.files.manager.interfaces.IFileManagerUtils;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class FileManagerUtils implements IFileManagerUtils {

    @Override
    public String getNormalizedName(Path path) {
        return getNormalizedName(path.getFileName().toString());
    }

    @Override
    public String getNormalizedName(String pathFileName) {
        pathFileName = pathFileName.toLowerCase();
        pathFileName = pathFileName.replaceAll("—", "-");
        pathFileName = pathFileName.replaceAll("[\\s_]+", " ");
        pathFileName = pathFileName.replaceAll("[^\\p{L}\\d\\s\\.\\-&()]", "");

//        StringBuffer sb = new StringBuffer();
//        for (String str : pathFileName.split(" ")) {
//            char[] stringArray = str.trim().toCharArray();
//            stringArray[0] = Character.toUpperCase(stringArray[0]);
//            sb.append(stringArray).append(" ");
//        }
//        pathFileName = sb.toString().trim();

        char[] cb = pathFileName.toCharArray();
        boolean found = false;
        for (int i = 0; i < cb.length; i++) {
            if (!found && (Character.isLetter(cb[i])
                    || cb[i] == '.'
            )) {
                cb[i] = Character.toUpperCase(cb[i]);
                found = true;
            } else if (Character.isWhitespace(cb[i])
                //    || cb[i]=='('
                    ) {
                found = false;
            }
        }
        pathFileName = String.valueOf(cb).trim();

        return pathFileName;
    }
}
