package com.gen.app;

import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
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
        addConfig(sitePath);
        addMetaData(sitePath);
        return 1;
    }

    /**
     * Create the folder for the website
     * @param sitePath Path for the website
     * @throws Exception
     */
    void createWebsiteFolder(String sitePath) throws Exception{
        Files.createDirectories(Paths.get(sitePath));
    }

    /**
     * Adds the config file to the website folder
     * @param sitePath Path of the website
     */
    void addConfig(String sitePath){
        try{
            Files.write(Paths.get(sitePath + "/config.json"), defaultConfig.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e){
            System.err.println("Could not create config file");
            e.printStackTrace();
        }
        System.out.println("Created config file");
    }

    /**
     * Adds the config file to the website folder
     * @param sitePath Path of the website
     */
    void addMetaData(String sitePath){
        try{
            Files.write(Paths.get(sitePath + "/meta.json"), defaultMetadata.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e){
            System.err.println("Could not create config file");
            e.printStackTrace();
        }
        System.out.println("Created config file");
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
