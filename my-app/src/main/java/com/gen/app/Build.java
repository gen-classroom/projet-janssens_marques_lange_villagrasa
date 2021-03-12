package com.gen.app;


import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "Build", description = "building")

public class Build implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("building");
        return 1;
    }
}