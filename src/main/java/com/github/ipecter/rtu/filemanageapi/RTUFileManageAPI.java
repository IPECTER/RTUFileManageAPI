package com.github.ipecter.rtu.filemanageapi;

import org.bukkit.plugin.Plugin;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**
     * config.yml *
     */
    private static final int CONFIG_FILE_VERSION = 4;
    private static final boolean CONFIG_RESET_FILE = true;
    private static final List<String> CONFIG_IGNORED_NODES = new ArrayList<String>() {
        {
            add("config-version");
        }
    };
    private static final List<String> CONFIG_REPLACE_NODES = new ArrayList<String>() {
        {
            add("disabled-worlds");
        }
    };

    /**
     * messages.yml *
     */
    private static final int MESSAGES_FILE_VERSION = 1;
    private static final boolean MESSAGES_RESET_FILE = false;
    private static final List<String> MESSAGES_IGNORED_NODES = new ArrayList<String>() {
        {
            add("config-version");
        }
    };
    private static final List<String> MESSAGES_REPLACE_NODES = new ArrayList<>();

    /**
     * biomes.yml *
     */
    private static final int BIOMES_FILE_VERSION = 0; //Unused for now
    private static final boolean BIOMES_RESET_FILE = false;
    private static final List<String> BIOMES_IGNORED_NODES = new ArrayList<String>() {
        {
            add("config-version");
        }
    };
    private static final List<String> BIOMES_REPLACE_NODES = new ArrayList<>();

    /**
     * database.db *
     */
    private static final int DATABASE_FILE_VERSION = 0; //Unused for now
    private static final boolean DATABASE_RESET_FILE = false;

    private HashMap<String, StorageFile> fileMap = new HashMap<>();

    public void reloadFiles(Plugin plugin) {

        INSTANCE.fileMap.clear();
        INSTANCE.registerFile("config.yml", new YAMLFile("config.yml"));
        INSTANCE.registerFile("messages.yml", new YAMLFile("messages.yml"));
        INSTANCE.registerFile("biomes.yml", new YAMLFile("biomes.yml"));
        INSTANCE.registerFile("database.db", new DBFile("database.db"));
    }

    /**
     *
     * @param fileName
     * @param type YAML or DB
     */
    public void reloadFile(Plugin plugin, String fileName, String type) {
        String dir = INSTANCE.fileMap.get(fileName).directory;
        INSTANCE.fileMap.remove(fileName);
        if (type.equalsIgnoreCase("YAML")){
            INSTANCE.registerFile(fileName, new YAMLFile(plugin, dir, fileName));
        }
        else if(type.equalsIgnoreCase("DB")){
            INSTANCE.registerFile(fileName, new YAMLFile(plugin, dir, fileName));
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
