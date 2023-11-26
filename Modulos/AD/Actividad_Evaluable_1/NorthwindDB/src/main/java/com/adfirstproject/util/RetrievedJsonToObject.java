package com.adfirstproject.util;

import com.adfirstproject.models.ProductInfoContainer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RetrievedJsonToObject {
    private StringBuffer stringBuffer = new StringBuffer();
    private String line = null;
    private final JsonURLRetrieve jsonRetrieved;

    public RetrievedJsonToObject(JsonURLRetrieve jsonRetrieved) {
        this.jsonRetrieved = jsonRetrieved;
    }

    public void productListGenerator(String string) {
        JSONObject response = new JSONObject(string);
        JSONArray products = response.getJSONArray("products");
        List<ProductInfoContainer> productList = new ArrayList<>();
        for (int i = 0; i<products.length();i++) {
            JSONObject product = products.getJSONObject(i);
            int productId = product.getInt("id");
            String productName = product.getString("title");
            String productDescription = product.getString("description");
            int productQty = product.getInt("stock");
            double productPrice = product.getDouble("price");
            ProductInfoContainer productInfoContainer = new ProductInfoContainer(
                    productId,
                    productName,
                    productDescription,
                    productQty,
                    productPrice
            );
            productList.add(productInfoContainer);
        }
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
