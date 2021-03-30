package com.gen.app;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FormatPage {
    String markdownToHtml(String markdown) {
        return "";
    }

    JsonObject metaDataToJson(String metadata)
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

        JsonObject jsonObject = new JsonParser().parse(str.toString()).getAsJsonObject();
        return jsonObject;
    }


    void formatPage(String page) {

    }
}