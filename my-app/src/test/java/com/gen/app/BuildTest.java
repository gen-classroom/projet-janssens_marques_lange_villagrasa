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
    String indexContent =
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Mon site | Index</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <ul>\n" +
            "    <li><a href=\"/index.html\">home</a></li>\n" +
            "    <li><a href=\"/content/page.html\">page</a></li>\n" +
            "</ul>\n" +
            "    <h1>Index</h1>\n\n" +
            "</body>\n" +
            "</html>";

    String pageContent =
            "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Mon site | Mon Premier Site</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <ul>\n" +
                    "    <li><a href=\"/index.html\">home</a></li>\n" +
                    "    <li><a href=\"/content/page.html\">page</a></li>\n" +
                    "</ul>\n" +
                    "    <h1>Mon titre</h1>\n" +
                    "<h2>Mon sous-titre</h2>\n" +
                    "<p>Le contenu de mon article.</p>\n\n"+
                    "</body>\n" +
                    "</html>";

    @Test
    public void canBuild(){
        initSite();
        Build builder = new Build();
        builder.sitePath = sitePath;
        builder.buildWebsite();
        try {
            String index = new String(Files.readAllBytes(Paths.get( sitePath + "/build/index.html")));
            String page = new String(Files.readAllBytes(Paths.get( sitePath + "/build/content/page.html")));

            assertEquals(indexContent, index);
            assertEquals(pageContent, page);
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