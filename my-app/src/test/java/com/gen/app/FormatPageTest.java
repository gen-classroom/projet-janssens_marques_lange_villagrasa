package com.gen.app;

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

    public void testSaveMetada() {
    }

    public void testFormatPage() {
    }
}