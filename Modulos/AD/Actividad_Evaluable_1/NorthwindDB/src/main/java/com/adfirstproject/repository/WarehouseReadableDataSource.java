package com.adfirstproject.repository;

import com.adfirstproject.models.ProductInfoContainer;

import java.util.List;

public interface WarehouseReadableDataSource {
    List<ProductInfoContainer> getAllProducts();

}
