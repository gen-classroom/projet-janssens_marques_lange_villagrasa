package com.gen.app;

import com.gen.app.Init;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;


public class InitTest{
    String testWebsite = "testWebsite";


    @Test
    public void canCreateDirecory(){

        try {
            Init init = new Init();
            init.createWebsiteFolder(testWebsite);
            File dir = new File(testWebsite);
            assert(dir.isDirectory());
            init.deleteDirectory(dir);
        }
        catch (Exception e){
            e.printStackTrace();
            assert(false);
        }

    }

    @Test
    public void canWriteConfig() {
        try {
            Init init = new Init();
            init.createWebsiteFolder(testWebsite);
            File dir = new File(testWebsite);
            init.addConfig(testWebsite);
            assert(Files.exists(Paths.get(testWebsite + "/config.json")));
            init.deleteDirectory(dir);
        }
        catch (Exception e){
            e.printStackTrace();
            assert(false);
        }
    }
}