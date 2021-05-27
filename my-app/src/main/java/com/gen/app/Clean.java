package com.gen.app;


import picocli.CommandLine;


import java.io.File;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "Clean", description = "cleaning")

public class Clean implements Callable<Integer> {

    @CommandLine.Parameters(index="0", description="Build specified site")
    public String sitePath;

    @Override
    public Integer call() throws Exception {
        System.out.println("cleaning");
        if(!deleteDirectory(new File(sitePath+"/build/"))){
            throw new Exception("Could not delete build");
        }
        return 1;
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}