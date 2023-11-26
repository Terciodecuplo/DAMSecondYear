package com.adfirstproject.repository;

import com.adfirstproject.models.EmployeesInfoContainer;

public interface WarehouseWritableDataSource extends WarehouseReadableDataSource {
    void saveEmployeeInDataSource(EmployeesInfoContainer employee);
}
