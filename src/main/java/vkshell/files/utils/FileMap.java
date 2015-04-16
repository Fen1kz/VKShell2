package vkshell.files.utils;

import vkshell.files.FileEntity;

import java.util.*;

public class FileMap implements MultiKeyMap<FileEntity> {
    private final Map<String, Set<FileEntity>> names = new HashMap<>();
    private final Map<String, FileEntity> md5s = new HashMap<>();

    @Override
    public FileEntity put(String md5, String name, FileEntity value) {
        Set namesSet = this.getByName(name);
        namesSet.add(value);
        return md5s.put(md5, value);
    }

    @Override
    public FileEntity getByMD5(String md5) {
        return md5s.get(md5);
    }

    @Override
    public Set<FileEntity> getByName(String name) {
        Set<FileEntity> namesSet = names.get(name);
        if (namesSet == null) {
            namesSet = new HashSet<>();
            names.put(name, namesSet);
        }
        return namesSet;
    }

    @Override
    public FileEntity remove(String md5) {
        FileEntity fe = this.getByMD5(md5);
        if (fe != null) {

        }
        return null;
    }
}