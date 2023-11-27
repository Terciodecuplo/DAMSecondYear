package com.adfirstproject.repository;

import com.adfirstproject.models.EmployeesInfoContainer;
import com.adfirstproject.models.OrderInfoContainer;
import com.adfirstproject.models.ProductInfoContainer;

import java.util.List;

public interface WarehouseReadableDataSource {
    List<ProductInfoContainer> getAllProducts();
    List<ProductInfoContainer> getAllProducts(double maxProductPrice);
    List<EmployeesInfoContainer> getAllEmployees();
    List<OrderInfoContainer> getAllOrders();
    ProductInfoContainer getSingleProduct(int retrieveProductById);

}
