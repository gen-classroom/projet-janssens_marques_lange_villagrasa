package com.gen.app;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;


@Command(name = "statique", description = "Static site generator",
         subcommands = {Init.class, Clean.class, Build.class, Serve.class})

class Statique implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        CommandLine.usage(this, System.out);
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new Statique()).execute(args);
        System.exit(exitCode);
    }

}