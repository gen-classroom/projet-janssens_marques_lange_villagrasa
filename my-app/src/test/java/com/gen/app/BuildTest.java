package com.gen.app;

import com.gen.app.Build;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;


public class BuildTest{

    String sitePath = "test";

    @Test
    public void canBuild(){
        initSite();
        Build builder = new Build();
        builder.sitePath = sitePath;
        builder.buildWebsite();
        try {
            String index = new String(Files.readAllBytes(Paths.get( sitePath + "/build/index.html")));
            String page = new String(Files.readAllBytes(Paths.get( sitePath + "/build/content/page.html")));

            assertEquals("<h1>Index</h1>\n", index);
            assertEquals("<h1>Page</h1>\n", page);
        }
        catch (IOException e) {
            System.err.println("Could not create build folder");
            e.printStackTrace();
            assert false;
        }

    }

    /**
     * Initialise some test files
     */
    private void initSite(){
        try {
            Init init = new Init();
            init.createWebsiteFolder(sitePath);
        }
        catch (IOException e) {
            System.err.println("Could not create folder");
            e.printStackTrace();
        }
        catch (Exception e){
            System.err.println("Could not create folder");
            e.printStackTrace();
        }
    }
}