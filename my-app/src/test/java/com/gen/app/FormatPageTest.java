package com.gen.app;


import junit.framework.TestCase;
import com.google.gson.*;


import java.io.*;
public class FormatPageTest extends TestCase {

    public void testMarkdownToHtml() {
    }

    public void testSaveMetada() {
        String inputString = "titre: Mon premier article\nauteur: Bertil Chapuis\n date: 2021-03-10";
        String outputString;
        String expected = "{\n\ttitre: \"Mon premier Article\",\n\tauteur: \"Bertil Chapuis\",\n\tdate: \"2021-03-10\"\n}";

        FormatPage format = new FormatPage();
        StringBuilder builder = new StringBuilder();

        outputString = format.metaDataToJson(inputString);

        assertEquals(outputString,expected);
    }

    public void testFormatPage() {
    }
}