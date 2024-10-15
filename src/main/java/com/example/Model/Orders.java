package com.example.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Orders {
    private String idOrder;
    private String customerID;
    private double price;
    private LocalDate orderDate;
    private String notes;
    private List<Products> products;
    private List<Orders> orders;
    private int quantityOrders;

    public Orders(String idOrder, String customerID, double price, LocalDate orderDate, String notes) {
        this.idOrder = idOrder;
        this.customerID = customerID;
        this.price = price;
        this.orderDate = orderDate;
        this.notes = notes;
        this.products = new ArrayList<Products>();
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public int getQuantityOrders() {
        return quantityOrders;
    }

    public void setQuantityOrders(int quantityOrders) {
        this.quantityOrders = quantityOrders;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    //Cập nhật đơn hàng
    private void updateOrders() {
        this.quantityOrders = 0;
        this.price = 0.0;

        for (Products sp : products) {
            this.quantityOrders += sp.getQuantity();
            this.price += sp.getPrice() * sp.getQuantity();
        }
    }

    //Phương thức thêm sản phẩm
    public void updateOrder (String idOrder, List<Products> newProducts, String newNotes, int quantityOrders) {
        for (Orders orders : orders) {
            if (orders.getIdOrder().equals(idOrder)) {

                if (newProducts != null && !newProducts.isEmpty()) {
                    orders.setProducts(newProducts);
                }

                if (newNotes != null && !newNotes.isEmpty()) {
                    orders.setNotes(newNotes);
                }

                if (quantityOrders != 0) {
                    orders.setQuantityOrders(quantityOrders);
                }
            }
        }
    }
}

