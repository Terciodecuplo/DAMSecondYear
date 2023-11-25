package com.adfirstproject.dbproject;

import com.adfirstproject.util.JsonURLRetrieve;
import com.adfirstproject.util.ParseJsonToString;

public class Main {
    JsonURLRetrieve jsonURLRetrieve = new JsonURLRetrieve();
    ParseJsonToString parseJsonToString = new ParseJsonToString(jsonURLRetrieve);


    public static void main(String[] args) {
        Main main = new Main();
        main.runProgram();
    }

    private void runProgram() {
        System.out.println(parseJsonToString.getJsonParsed());
    }
}
