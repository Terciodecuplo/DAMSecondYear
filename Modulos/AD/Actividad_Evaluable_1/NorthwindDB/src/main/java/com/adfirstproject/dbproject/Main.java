package com.adfirstproject.dbproject;

import com.adfirstproject.models.ProductInfoContainer;
import com.adfirstproject.repository.WarehouseInfoContainerDBDataSource;
import com.adfirstproject.repository.WarehouseRepository;
import com.adfirstproject.util.JsonURLRetrieve;
import com.adfirstproject.util.RetrievedJsonToObject;

import java.util.List;

public class Main {
    JsonURLRetrieve jsonURLRetrieve = new JsonURLRetrieve();
    RetrievedJsonToObject retrievedJsonToObject = new RetrievedJsonToObject(jsonURLRetrieve);
    WarehouseInfoContainerDBDataSource slowDataSource = new WarehouseInfoContainerDBDataSource();
    WarehouseRepository repository = new WarehouseRepository(slowDataSource);

    public static void main(String[] args) {
        Main main = new Main();
        main.runProgram();
    }

    private void runProgram() {
        retrievedJsonToObject.productListGenerator(retrievedJsonToObject.parseJsonToStringBuffer());
        List< ProductInfoContainer> productList = repository.getAllProducts();
        for(ProductInfoContainer product : productList){
            System.out.println(String.format("%d.- %s \n\t\tDescription: %s\n\t\tStock: %d\n\t\tPrice: %f",
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getQuantity(),
                    product.getPrice()));
        }
    }




}
