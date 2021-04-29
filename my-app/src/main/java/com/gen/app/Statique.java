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
        Callable<Integer> callable = new Statique();
        CommandLine cmd = new CommandLine(callable);

        try
        {
            System.exit(cmd.execute(args));
        }
        catch (CommandLine.ParameterException e)
        {
            cmd.getErr().println("Parameter Error : "+e.getMessage());
            if (!CommandLine.UnmatchedArgumentException.printSuggestions(e, cmd.getErr()))
            {
                e.getCommandLine().usage(cmd.getErr());
            }

            System.exit(cmd.getCommandSpec().exitCodeOnInvalidInput());
        }
        catch (Exception e)
        {
            e.printStackTrace(cmd.getErr());
            System.exit(cmd.getCommandSpec().exitCodeOnExecutionException());
        }

    }

}