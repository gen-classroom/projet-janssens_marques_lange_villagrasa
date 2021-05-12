package com.gen.app;

import org.apache.commons.io.FileUtils;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "init", description = "initialisation")

public class Init implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "Path for the new website.")
    private String sitePath;

    String defaultConfig = "{\n\tname: \"\"\n}";
    String defaultMetadata = "{}";

    @Override
    public Integer call() throws Exception {
        createWebsiteFolder(sitePath);
        System.out.print("Created site directory: " + sitePath);
        return 1;
    }

    /**
     * Create the folder for the website
     * @param sitePath Path for the website
     * @throws Exception
     */
    void createWebsiteFolder(String sitePath) throws Exception{
        // Get the default website folder in resources
        ClassLoader classLoader = getClass().getClassLoader();
        URL baseSite = classLoader.getResource("baseSite");
        File baseSiteFile = new File(baseSite.getPath());
        File siteFile = new File(sitePath);
        FileUtils.copyDirectory(baseSiteFile, siteFile);
    }

    /**
     * Deletes a directory and all it's content
     * @param dir Directory to delete
     */
    void deleteDirectory(File dir){
        File[] content = dir.listFiles();
        if(content != null){
            for (File file : content){
                deleteDirectory(file);
            }
        }
        dir.delete();
    }
}
