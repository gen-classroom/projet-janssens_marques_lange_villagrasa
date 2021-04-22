package com.gen.app;

import junit.framework.TestCase;
import org.junit.Test;

public class FormatPageTest extends TestCase {

    public void testMarkdownToHtml() {
    }

    public void testSaveMetada() {
    }

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