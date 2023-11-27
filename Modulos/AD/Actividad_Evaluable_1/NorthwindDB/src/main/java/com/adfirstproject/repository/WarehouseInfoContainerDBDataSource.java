package com.adfirstproject.repository;

import com.adfirstproject.models.EmployeesInfoContainer;
import com.adfirstproject.models.OrderInfoContainer;
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
    public List<ProductInfoContainer> getAllProducts(double maxProductPrice) {
        List<ProductInfoContainer> productList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s " +
                    "WHERE %s < ?",
                    SchemaDB.DB_TABLE_PRODUCTS,
                    SchemaDB.COL_PRICE));
            preparedStatement.setDouble(1,maxProductPrice);
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
    public List<EmployeesInfoContainer> getAllEmployees() {
        List<EmployeesInfoContainer> employeesList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " +
                    SchemaDB.DB_TABLE_EMPLOYEES);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(SchemaDB.COL_ID);
                String name = resultSet.getString(SchemaDB.COL_NAME);
                String surname = resultSet.getString(SchemaDB.COL_SURNAME);
                String email = resultSet.getString(SchemaDB.COL_EMAIL);

                EmployeesInfoContainer employee = new EmployeesInfoContainer(id,
                        name,
                        surname,
                        email
                );
                employeesList.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeesList;
    }

    @Override
    public List<OrderInfoContainer> getAllOrders() {
        List<OrderInfoContainer> orderList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s"
                    , SchemaDB.DB_TABLE_ORDERS));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(SchemaDB.COL_ID);
                int id_product = resultSet.getInt(SchemaDB.COL_ID_PRODUCTS);
                String description = resultSet.getString(SchemaDB.COL_DESCRIPTION);
                double totalCost = resultSet.getDouble(SchemaDB.COL_TOTAL_COST);
                OrderInfoContainer order = new OrderInfoContainer(
                        id,
                        id_product,
                        description,
                        totalCost
                );
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderList;
    }

    @Override
    public ProductInfoContainer getSingleProduct(int retrieveProductById) {
        int id = 0;
        String name = "";
        String description = "";
        int stock = 0;
        double price = 0.0;
        try {
            preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s " +
                            "WHERE %s = ?", SchemaDB.DB_TABLE_PRODUCTS,
                    SchemaDB.COL_ID));
            preparedStatement.setInt(1, retrieveProductById);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(SchemaDB.COL_ID);
                name = resultSet.getString(SchemaDB.COL_NAME);
                description = resultSet.getString(SchemaDB.COL_DESCRIPTION);
                stock = resultSet.getInt(SchemaDB.COL_STOCK);
                price = resultSet.getDouble(SchemaDB.COL_PRICE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ProductInfoContainer product = new ProductInfoContainer(id,
                name,
                description,
                stock,
                price
        );
        return product;
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

    @Override
    public void addNewOrder(OrderInfoContainer order) {
        try {
            preparedStatement = connection.prepareStatement(String.format("INSERT INTO %s (%s, %s, %s)" +
                            "VALUES (?, ?, ?)",
                    SchemaDB.DB_TABLE_ORDERS,
                    SchemaDB.COL_ID_PRODUCTS,
                    SchemaDB.COL_DESCRIPTION,
                    SchemaDB.COL_TOTAL_COST));
            preparedStatement.setInt(1, order.getId_product());
            preparedStatement.setString(2, order.getDescription());
            preparedStatement.setDouble(3, order.getTotalCost());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addNewProductToFavList(double minProductPrice) {
        try {
            preparedStatement = connection.prepareStatement(String.format("INSERT INTO %s (%s) " +
                            "SELECT %s FROM %s WHERE %s > ?",
                    SchemaDB.DB_TABLE_FAV_PRODUCTS,
                    SchemaDB.COL_ID_PRODUCTS,
                    SchemaDB.COL_ID,
                    SchemaDB.DB_TABLE_PRODUCTS,
                    SchemaDB.COL_PRICE));
            preparedStatement.setDouble(1, minProductPrice);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
