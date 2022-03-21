package com.github.ipecter.rtu.filemanageapi;

import org.bukkit.plugin.Plugin;

import java.io.File;

public abstract class StorageFile {

    Plugin plugin;
    public String directory;
    public String fileName;
    public Integer fileVersion;
    public boolean resetFile;
    public File file;


    public StorageFile(Plugin plugin, String directory, String fileName) {
        this(plugin, directory, fileName, null, false);
    }

    public StorageFile(Plugin plugin, String directory, String fileName, Integer fileVersion, boolean resetFile) {
        this.plugin = plugin;
        this.directory = directory;
        this.fileName = fileName;
        this.fileVersion = fileVersion;
        this.resetFile = resetFile;
        this.file = new File(directory, fileName);
    }
}