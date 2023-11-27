package com.adfirstproject.models;

public class OrderInfoContainer {
    private int id;
    private int id_product;
    private String description;
    private double totalCost;

    public OrderInfoContainer(int id, int id_product, String description, double totalCost) {
        this.id = id;
        this.id_product = id_product;
        this.description = description;
        this.totalCost = totalCost;
    }

    public OrderInfoContainer(int id_product, String description, double totalCost) {
        this.id_product = id_product;
        this.description = description;
        this.totalCost = totalCost;
    }

    public int getId() {
        return id;
    }

    public int getId_product() {
        return id_product;
    }

    public String getDescription() {
        return description;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
