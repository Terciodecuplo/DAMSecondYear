package com.adfirstproject.schema;

public interface SchemaDB {
    String JSON_PRODUCTS_URL = "https://dummyjson.com/products";
    String LOCAL_HOST = "localhost:3306";
    String DB_NAME = "warehouse";
    String DB_USER = "root";
    String DB_PASS = "";
    String DB_TABLE_PRODUCTS = "products";
    String COL_ID = "id";
    String COL_NAME = "name";
    String COL_DESCRIPTION = "description";
    String COL_STOCK = "quantity";
    String COL_PRICE = "price";
    String DB_TABLE_EMPLOYEES = "employees";
    String COL_SURNAME = "surname";
    String COL_EMAIL = "email";
    String DB_TABLE_ORDERS = "orders";
    String COL_ID_PRODUCTS = "id_product";
    String COL_TOTAL_COST= "total_cost";
    String DB_TABLE_FAV_PRODUCTS = "fav_products";



}
