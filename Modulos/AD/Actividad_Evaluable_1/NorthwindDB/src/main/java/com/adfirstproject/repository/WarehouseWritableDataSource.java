package com.adfirstproject.repository;

import com.adfirstproject.models.EmployeesInfoContainer;
import com.adfirstproject.models.ProductInfoContainer;

import java.util.List;

public interface WarehouseWritableDataSource extends WarehouseReadableDataSource {
    void addNewEmployee(EmployeesInfoContainer employee);
    void populateTableProducts(List<ProductInfoContainer> productList);
    void addNewProduct(ProductInfoContainer product);
}
