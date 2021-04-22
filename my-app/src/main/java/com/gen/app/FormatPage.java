package com.gen.app;

import com.github.rjeschke.txtmark.Processor;

public class FormatPage {
    String markdownToHtml(String markdown) {
        return Processor.process(markdown);
    }

    String metaDataToJson(String metadata)
    {

        String[] lines = metadata.split("\n");
        StringBuilder str = new StringBuilder();
        str.append("{\n");

        int linectr = 0;
        for(String line: lines)
        {
            linectr++;
            String[] proprety = line.split(": ");
            str.append("\t").append(proprety[0]).append(": ").append("\"").append(proprety[1]).append("\"");
            if(linectr < lines.length)
                str.append(",");
            str.append("\n");
        }
        str.append("}");

        return str.toString();
    }

    /**
     * split the content of a page into config and html
     * the page must have the following syntax:
     * ```
     * metada1: value of metada 1
     * metadata2: value of metada 2
     * ---
     * # My mardown page
     * And bellow the split expression `---`
     * will be the text of the page
     * @param page a page respecting the right format
     * @return array of strings containing:
     *  id[0] config in json format
     *  id[1] html
     */
    String[] formatPage(String page) {
        String[] data = page.split("---");
        String config = metaDataToJson(data[0]);
        String html = markdownToHtml(data[1]);

        return new String[] {config, html};
    }
}
