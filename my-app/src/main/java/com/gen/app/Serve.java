package com.gen.app;


import picocli.CommandLine;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "Serve", description = "serving")
public class Serve implements Callable<Integer> {

    @CommandLine.Parameters(index="0", description="serve specified site")
    public String sitePath;

    @Override
    public Integer call() throws Exception {
        // Récupération du chemin travaillable.

        // Vérifie si le fichier build/index.html est présent.
        File index = new File(sitePath  + "build/index.html");

        // Tentative d'ouverture du fichier inddex.html
        try
        {
            if(! index.exists()) // Si le fichier n'existe pas
            {
                throw new RuntimeException("File " + sitePath + "build/index.html unreachable.");
            }
            else // Si le fichier existe
            {
                String uriReady = index.toURI().toString();
                uriReady = uriReady.replace("./", "");
                Desktop.getDesktop().browse(new URI(uriReady));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return 0;
    }
}