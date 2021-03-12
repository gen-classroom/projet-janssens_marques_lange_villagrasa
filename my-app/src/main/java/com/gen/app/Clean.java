package com.gen.app;


import picocli.CommandLine;


import java.util.concurrent.Callable;

@CommandLine.Command(name = "Clean", description = "cleaning")

public class Clean implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("cleaning");
        return 1;
    }
}