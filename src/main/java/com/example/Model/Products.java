package com.example.Model;

import java.util.Date;

public class Products {
    private String idProduct;
    private String name;
    private double price;
    private int quantity;
    private Date expiryDate;
    private String description;
    private float weight;

    public Products(int idProduct, String name, double price, String description) {
        this.idProduct = String.valueOf(idProduct);
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdProduct() {
        return idProduct;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

