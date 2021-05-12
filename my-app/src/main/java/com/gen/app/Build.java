package com.gen.app;


import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import picocli.CommandLine;
import com.gen.app.FormatPage;

import javax.naming.ldap.PagedResultsControl;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CommandLine.Command(name = "build", description = "building")

public class Build implements Callable<Integer> {

    private class Page{
        public String content;
        public JsonObject metadata;
    }

    private class PageContext{
        Map<String, Object> site;
        Map<String, Object> page;
        String content;
    }

    FormatPage fp = new FormatPage();

    @CommandLine.Parameters(index="0", description="Build specified site")
    public String sitePath;
    Map<String, Object> siteConfig;
    public Template template;

    @Override
    public Integer call() throws Exception {
        buildWebsite();
        return 1;
    }

    /**
     * Builds the website
     */
    public void buildWebsite(){
        // Create the build directory
        try {
            Files.createDirectories(Paths.get(sitePath + "/build"));
        }
        catch (IOException e) {
            System.err.println("Could not create build folder");
            e.printStackTrace();
        }
        System.out.println("Created build folder");
        // Load site config

        try{
            String configString = new String(Files.readAllBytes(Paths.get(sitePath + "/config.json")));
            Gson gson = new Gson();

            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            this.siteConfig = gson.fromJson(configString, type);
        } catch (Exception e){
            System.err.println("Could not load website config file");
            e.printStackTrace();
            return;
        }
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // Initialize template
        try{
            Handlebars handlebars = new Handlebars();

            String layout = Files.readString(Paths.get(sitePath + "/template/layout.html"));
            Matcher m = Pattern.compile("\\{\\{ *% include [a-zA-z0-9.-_]* *\\}\\}").matcher(layout);
            StringBuffer sb = new StringBuffer();
            while (m.find())
            {
                // Extract the file name
                Matcher m2 = Pattern.compile("[a-zA-z0-9.-_]* *\\}\\}").matcher(m.group());
                m2.find();

                String fileName = m2.group().replace(" ", "").replace("}}", "");
                String fileContent = Files.readString(Paths.get(sitePath + "/template/" + fileName));
                m.appendReplacement(sb, fileContent);
            }
            m.appendTail(sb);
            this.template = handlebars.compileInline(sb.toString());
        } catch(IOException e){
            System.err.println("Could not load template file");
            e.printStackTrace();
            return;
        }


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

                    // Load metadata
                    Gson gson = new Gson();

                    Type type = new TypeToken<Map<String, Object>>(){}.getType();
                    Map<String, Object> pageConfig = gson.fromJson(page.metadata, type);
                    Map<String, Object> pageContext = new HashMap<>();
                    // Apply template to page
                    pageContext.put("site", this.siteConfig);
                    pageContext.put("page", pageConfig);
                    pageContext.put("content", page.content);

                    String pageHtml = template.apply(pageContext);

                    Files.write(Paths.get(sitePath + "/build" + path + "/" +file.getName().replace(".md", ".html")), pageHtml.getBytes(StandardCharsets.UTF_8));
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
        page.metadata = new JsonParser().parse(meta).getAsJsonObject();

        return page;
    }

}