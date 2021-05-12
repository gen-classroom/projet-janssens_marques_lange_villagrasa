package com.gen.app;


import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;


public class VersionTest {

    public final String EXPECTED_VERSION = "0.0.1-SNAPSHOT";

    @Test
    public void shouldGetCurrentVersion() throws Exception {
        String inputXml = "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "  xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "  <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "  <groupId>com.gen.app</groupId>\n" +
                "  <artifactId>my-app</artifactId>\n" +
                "  <version>0.0.1-SNAPSHOT</version> \n" +
                "</project>";

        InputStream is = new ByteArrayInputStream(inputXml.getBytes(StandardCharsets.UTF_8));
        Version v = new Version(new InputStreamReader(is));
        assertEquals(v.getVersion(), EXPECTED_VERSION);
    }



}
