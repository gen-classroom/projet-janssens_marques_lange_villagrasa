package com.gen.app;

import junit.framework.TestCase;
import com.google.gson.*;
import org.junit.Test;
import java.io.*;
import com.gen.app.FormatPage;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class FormatPageTest{

    @Test
    public void canConvertMarkdownToHtml(){
        FormatPage fp = new FormatPage();
        String input = "#Hello\n[test link](https://example.com)\n![test image](https://example.com/image.jpg)";
        String output = fp.markdownToHtml(input);
        assertEquals("<h1>Hello</h1>\n<p><a href=\"https://example.com\">test link</a>\n<img src=\"https://example.com/image.jpg\" alt=\"test image\" /></p>\n", output);
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