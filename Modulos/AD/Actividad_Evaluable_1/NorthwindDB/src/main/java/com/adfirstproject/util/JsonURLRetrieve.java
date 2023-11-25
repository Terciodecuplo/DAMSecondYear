package com.adfirstproject.util;

import com.adfirstproject.schema.SchemaDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonURLRetrieve {
    // https://dummyjson.com/products
    // Process of develop: URL > HTTPConnection > BufferedReader > String

    private URL url;
    private HttpURLConnection connection;
    private BufferedReader reader;

    public JsonURLRetrieve() {
        try {
            this.url = new URL(SchemaDB.JSON_PRODUCTS_URL);
            connection = (HttpURLConnection) url.openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            System.out.println("I/O connection error.");
        }
    }

    public BufferedReader getReader(){
        return reader;
    }
}
