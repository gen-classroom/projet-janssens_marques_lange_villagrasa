package com.gen.app;


import junit.framework.TestCase;
import com.google.gson.*;


import java.io.*;
public class FormatPageTest extends TestCase {

    public void testMarkdownToHtml() {
    }

    public void testSaveMetada() {
        String inputString = "titre: Mon premier Article\nauteur: Bertil Chapuis\ndate: 2021-03-10";
        JsonObject outputString;

        FormatPage format = new FormatPage();

        outputString = format.metaDataToJson(inputString);

        assertTrue(outputString.isJsonObject());
        assertTrue(outputString.get("titre").getAsString().equals("Mon premier Article"));
        assertTrue(outputString.get("auteur").getAsString().equals("Bertil Chapuis"));
        assertTrue(outputString.get("date").getAsString().equals("2021-03-10"));
    }

    public void testFormatPage() {
    }
}