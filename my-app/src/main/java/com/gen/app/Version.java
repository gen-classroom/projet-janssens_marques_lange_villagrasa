package com.gen.app;

import picocli.CommandLine;

import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

@CommandLine.Command(name = "-version", description = "giving current version")

public class Version implements Callable<Integer> {

    private Model model;
    private String version;

    public Version(InputStreamReader file) throws Exception{
        MavenXpp3Reader reader = new MavenXpp3Reader();
        model = reader.read(file);
        version = model.getVersion();
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(version);
        return 1;
    }

    /** returns the current version of the app.
     *
     * @return String of current version of the app.
     */
    public String getVersion() {
        return version;
    }
}