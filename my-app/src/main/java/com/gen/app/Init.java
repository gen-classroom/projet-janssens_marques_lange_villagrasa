package com.gen.app;


import org.w3c.dom.ls.LSOutput;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "init", description = "initialisation")

public class Init implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("init");
        return 1;
    }
}
