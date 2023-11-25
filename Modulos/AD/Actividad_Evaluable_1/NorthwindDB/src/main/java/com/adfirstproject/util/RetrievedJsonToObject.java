package com.adfirstproject.util;

import com.adfirstproject.models.Products;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class RetrievedJsonToObject {
    private StringBuffer stringBuffer = new StringBuffer();
    private String line = null;
    private final JsonURLRetrieve jsonRetrieved;

    public RetrievedJsonToObject(JsonURLRetrieve jsonRetrieved) {
        this.jsonRetrieved = jsonRetrieved;
    }

    public void productListContainer(String string) {
        JSONObject response = new JSONObject(string);
        JSONArray products = response.getJSONArray("products");
        JSONObject product = products.getJSONObject(0);
        String productName = product.getString("title");
        System.out.println(productName);
    }

    public String parseJsonToStringBuffer() {
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
