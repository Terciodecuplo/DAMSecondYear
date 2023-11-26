package com.adfirstproject.repository;

import com.adfirstproject.models.EmployeesInfoContainer;
import com.adfirstproject.models.ProductInfoContainer;
import com.adfirstproject.schema.SchemaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseInfoContainerDBDataSource implements WarehouseWritableDataSource {

    private static Connection connection;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public WarehouseInfoContainerDBDataSource() {
        getConnection();
    }

    public static void setConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = String.format("jdbc:mysql://%s/%s",
                    SchemaDB.LOCAL_HOST,
                    SchemaDB.DB_NAME
            );
            connection = DriverManager.getConnection(url,
                    SchemaDB.DB_USER,
                    SchemaDB.DB_PASS
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            setConnection();
        }
        return connection;
    }

    @Override
    public List<ProductInfoContainer> getAllProducts() {
        List<ProductInfoContainer> productList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " +
                    SchemaDB.DB_TABLE_PRODUCTS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(SchemaDB.COL_ID);
                String name = resultSet.getString(SchemaDB.COL_NAME);
                String description = resultSet.getString(SchemaDB.COL_DESCRIPTION);
                int stock = resultSet.getInt(SchemaDB.COL_STOCK);
                double price = resultSet.getDouble(SchemaDB.COL_PRICE);
                ProductInfoContainer product = new ProductInfoContainer(id,
                        name,
                        description,
                        stock,
                        price
                );
                 productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public void saveEmployeeInDataSource(EmployeesInfoContainer employee) {

    }
}
