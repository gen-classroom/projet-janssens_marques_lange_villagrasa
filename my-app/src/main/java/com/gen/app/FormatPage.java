package com.gen.app;

import com.github.rjeschke.txtmark.Processor;

public class FormatPage {
    String markdownToHtml(String markdown) {
        return Processor.process(markdown);
    }

    void saveMetada(String metadata) {

    }

    void formatPage(String page) {

    }
}
