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

    String sitePath = "./websites/test";

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
            Files.createDirectories(Paths.get(sitePath));
            Files.write(Paths.get( sitePath +"/index.md"), "{}\n---\n#Index".getBytes(StandardCharsets.UTF_8));

            Files.createDirectories(Paths.get(sitePath + "/content"));
            Files.write(Paths.get(sitePath + "/content/page.md"), "{}\n---\n#Page".getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException e) {
            System.err.println("Could not create build folder");
            e.printStackTrace();
        }
    }
}