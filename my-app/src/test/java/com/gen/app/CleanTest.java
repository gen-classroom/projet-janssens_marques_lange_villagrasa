package com.gen.app;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CleanTest {

    @Test
    public void shouldCleanBuildFolder()
    {

        Build builder = new Build();
        builder.sitePath = "./test/mon/site";

        Clean clean = new Clean();
        clean.sitePath = "./test/mon/site";
        try{
            builder.buildWebsite();

            //make sure the site has been build
            assertTrue(Files.exists(Paths.get(clean.sitePath+"/build")));


            clean.call();
        }catch(Exception e)
        {
            System.out.println("Could not clean build folder");

            e.printStackTrace();
        }
        assertFalse(Files.exists(Paths.get(clean.sitePath+"/build")));
    }
}
