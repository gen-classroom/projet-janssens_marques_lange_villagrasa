package com.gen.app;

import com.google.gson.*;
import org.junit.Test;
import static org.junit.Assert.*;


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
        String inputString = "titre: Mon premier article\n" +
                "auteur: Bertil Chapuis\n" +
                "date: 2021-03-10\n";
        String expectedOutput = "{\n" +
                "\ttitre: \"Mon premier article\",\n" +
                "\tauteur: \"Bertil Chapuis\",\n" +
                "\tdate: \"2021-03-10\"\n" +
                "}";
        String outputString;

        FormatPage format = new FormatPage();

        outputString = format.metaDataToJson(inputString);
        assertEquals(expectedOutput, outputString);
    }

    @Test
    public void testIsPageFormatted() {
        String page = "titre: Mon premier article\n" +
                "auteur: Bertil Chapuis\n" +
                "date: 2021-03-10\n" +
                "---\n" +
                "# Mon premier article\n" +
                "## Mon sous-titre\n" +
                "Le contenu de mon article.\n" +
                "\n" +
                "![Une image](./image.png)";
        String expectedConfig = "{\n" +
                "\ttitre: \"Mon premier article\",\n" +
                "\tauteur: \"Bertil Chapuis\",\n" +
                "\tdate: \"2021-03-10\"\n" +
                "}";
        String expectedHtml = "<h1>Mon premier article</h1>\n" +
                "<h2>Mon sous-titre</h2>\n" +
                "<p>Le contenu de mon article.</p>\n" +
                "<p><img src=\"./image.png\" alt=\"Une image\" /></p>\n";
        FormatPage pageFormatter = new FormatPage();
        String[] data = pageFormatter.formatPage(page);
        assertEquals(expectedConfig, data[0]);
        assertEquals(expectedHtml, data[1]);
    }
}