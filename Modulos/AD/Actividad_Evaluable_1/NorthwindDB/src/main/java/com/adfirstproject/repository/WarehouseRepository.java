package com.adfirstproject.repository;

import com.adfirstproject.models.EmployeesInfoContainer;
import com.adfirstproject.models.ProductInfoContainer;

import java.util.List;

public class WarehouseRepository {

    /*
        Even if the program requirements are basic, the WarehouseRepository class has been created
        to retrieve data from a slowDataSource (like a remote DB) and, in the future, from a fastDataSource
        (the data stored in the cache).
     */
    private final WarehouseWritableDataSource slowDataSource;

    public WarehouseRepository(WarehouseWritableDataSource slowDataSource){
        this.slowDataSource = slowDataSource;
    }

    public List<ProductInfoContainer> getAllProducts() {
        return slowDataSource.getAllProducts();
    }

    public void populateTableProduct(List<ProductInfoContainer> productInfoContainerList){
        slowDataSource.populateTableProducts(productInfoContainerList);
    }

    public void addNewProduct(ProductInfoContainer product){
        slowDataSource.addNewProduct(product);
    }

    public void addNewEmployee(EmployeesInfoContainer employee){
        slowDataSource.addNewEmployee(employee);
    }
}
