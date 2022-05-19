package com.github.ipecter.rtu.filemanageapi;

import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Set;

public class RTUFileManageAPI {
    private RTUFileManageAPI() {
    }

    private static class LazyHolder {
        public static final RTUFileManageAPI INSTANCE = new RTUFileManageAPI();
    }

    public static RTUFileManageAPI getInstance() {
        return LazyHolder.INSTANCE;
    }
    private HashMap<String, StorageFile> fileMap = new HashMap<>();

    /**
     *
     * @param fileName
     * @param enumFile YAML or DB
     */
    public void reloadFile(Plugin plugin, String fileName, EnumFile enumFile) {
        String dir = fileMap.get(fileName).directory;
        fileMap.remove(fileName);
        switch (enumFile){
            case DB:
                registerFile(fileName, new YAMLFile(plugin, dir, fileName));
            case YAML:
                registerFile(fileName, new DBFile(plugin, dir, fileName));
        }
    }

    public StorageFile getFile(String fileName) {
        return fileMap.get(fileName);
    }

    public YAMLFile getYAMLFile(String fileName) {
        return (YAMLFile) getFile(fileName);
    }

    public DBFile getDBFile(String fileName) {
        return (DBFile) getFile(fileName);
    }

    public Set<String> getFileNames() {
        return fileMap.keySet();
    }

    public void registerFile(String fileName, StorageFile file) {
        fileMap.put(fileName, file);
    }

    public void unregisterFile(String fileName) {
        fileMap.remove(fileName);
    }
}
