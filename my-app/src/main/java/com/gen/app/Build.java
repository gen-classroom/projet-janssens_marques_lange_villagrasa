package com.gen.app;


import picocli.CommandLine;
import com.gen.app.FormatPage;

import javax.naming.ldap.PagedResultsControl;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "build", description = "building")

public class Build implements Callable<Integer> {

    private class Page{
        public String content;
        public Object metadata;
    }

    FormatPage fp = new FormatPage();

    @CommandLine.Parameters(index="0", description="Build specified site")
    private String sitePath;

    @Override
    public Integer call() throws Exception {
        buildWebsite();
        return 1;
    }

    /**
     * Builds the website
     */
    private void buildWebsite(){
        // Create the build directory
        try {
            Files.createDirectories(Paths.get(sitePath + "/build"));
        }
        catch (IOException e) {
            System.err.println("Could not create build folder");
            e.printStackTrace();
        }
        System.out.println("Created build folder");
        // Build all the files
        buildPagesInDirectory("");
    }

    /**
     * Builds pages of the website by recursively looking for md files
     * @param path path of the directory to look into
     */
    private void buildPagesInDirectory(String path){
        File dir = new File(sitePath + path);
        File[] files = dir.listFiles();

        // Get the names of the files by using the .getName() method
        for (File file : files){
            // Recursive for directories
            if (file.isDirectory()){
                buildPagesInDirectory(path + "/" + file.getName());
            }
            // Get md files
            else if(file.getName().endsWith(".md")){
                try {
                    Files.createDirectories(Paths.get(sitePath + "/build/" + path));
                    System.out.println("Building " + path + "/" +file.getName());
                    String pageContent = new String(Files.readAllBytes(Paths.get(sitePath + path + "/" +file.getName())));
                    Page page = processPage(pageContent);
                    Files.write(Paths.get(sitePath + "/build" + path + "/" +file.getName().replace(".md", ".html")), page.content.getBytes(StandardCharsets.UTF_8));
                }
                catch (IOException e) {
                    System.err.println("Could not build page " + path + "/" +file.getName());
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Processes a page by building the html and extracting the metadata
     * @param input string of the md file
     * @return Page object
     */
    private Page processPage(String input){
        // Split metadata and content
        String[] splits = input.split("---\n");

        String meta = splits[0];
        String markdown = splits[1];

        Page page = new Page();
        page.content = fp.markdownToHtml(markdown);
        page.metadata = null; // Need to implement the parsing

        return page;
    }

}