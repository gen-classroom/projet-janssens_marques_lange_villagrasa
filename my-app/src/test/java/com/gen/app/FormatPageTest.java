package com.gen.app;


import static org.junit.Assert.*;
import com.google.gson.*;
import org.junit.Test;

public class FormatPageTest {

    public void testMarkdownToHtml() {
    }

    @Test
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