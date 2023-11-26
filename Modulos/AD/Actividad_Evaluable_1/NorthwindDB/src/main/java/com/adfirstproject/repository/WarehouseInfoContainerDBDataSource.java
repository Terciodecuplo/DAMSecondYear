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
    public void addNewEmployee(EmployeesInfoContainer employee) {
        try {
            preparedStatement = connection.prepareStatement(String.format("INSERT INTO %s (%s,%s,%s)" +
                            "VALUE (?,?,?)", SchemaDB.DB_TABLE_EMPLOYEES,
                    SchemaDB.COL_NAME,
                    SchemaDB.COL_SURNAME,
                    SchemaDB.COL_EMAIL));
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void populateTableProducts(List<ProductInfoContainer> productList) {
        try {
            preparedStatement = connection.prepareStatement(String.format("INSERT INTO %s (%s,%s,%s,%s)" +
                            "VALUE (?,?,?,?)", SchemaDB.DB_TABLE_PRODUCTS,
                    SchemaDB.COL_NAME,
                    SchemaDB.COL_DESCRIPTION,
                    SchemaDB.COL_STOCK,
                    SchemaDB.COL_PRICE));
            for (ProductInfoContainer product : productList) {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setInt(3, product.getQuantity());
                preparedStatement.setDouble(4, product.getPrice());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addNewProduct(ProductInfoContainer product) {
        try {
            preparedStatement = connection.prepareStatement(String.format("INSERT INTO %s (%s,%s,%s,%s)" +
                            "VALUE (?,?,?,?)", SchemaDB.DB_TABLE_PRODUCTS,
                    SchemaDB.COL_NAME,
                    SchemaDB.COL_DESCRIPTION,
                    SchemaDB.COL_STOCK,
                    SchemaDB.COL_PRICE));
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
