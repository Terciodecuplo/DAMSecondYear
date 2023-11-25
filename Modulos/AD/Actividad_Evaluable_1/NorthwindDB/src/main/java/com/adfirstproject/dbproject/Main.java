package com.adfirstproject.dbproject;

import com.adfirstproject.util.JsonURLRetrieve;
import com.adfirstproject.util.RetrievedJsonToObject;

public class Main {
    JsonURLRetrieve jsonURLRetrieve = new JsonURLRetrieve();
    RetrievedJsonToObject retrievedJsonToObject = new RetrievedJsonToObject(jsonURLRetrieve);


    public static void main(String[] args) {
        Main main = new Main();
        main.runProgram();
    }

    private void runProgram() {
        retrievedJsonToObject.productListContainer(retrievedJsonToObject.parseJsonToStringBuffer());
    }


}
