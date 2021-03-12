package com.gen.app;


import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "Serve", description = "serving")

public class Serve implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("serving");
        return 1;
    }
}