package com.adfirstproject.util;

import java.io.IOException;

public class ParseJsonToString {
    private StringBuffer stringBuffer = new StringBuffer();
    private String line = null;
    private final JsonURLRetrieve jsonRetrieved;

    public ParseJsonToString(JsonURLRetrieve jsonRetrieved) {
        this.jsonRetrieved = jsonRetrieved;
    }

    public String getJsonParsed() {
        try {
            while ((line = jsonRetrieved.getReader().readLine()) != null) {
                stringBuffer.append(line);
            }

        } catch (IOException e) {
            System.out.println("I/O error occurred while reading stored json.");
        }
        return stringBuffer.toString();
    }
}
