package com.example.Controller;

import com.example.Database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class ProductController {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField quantityField;
    @FXML
    private DatePicker expiryDateField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField weightField;
    @FXML
    private Label messageLabel;

    // Hàm thêm sản phẩm
    public void addProduct() {
        String productId = idField.getText();
        String productName = nameField.getText();
        String productPrice = priceField.getText();
        String productQuantity = quantityField.getText();
        Date expiryDate = Date.valueOf(expiryDateField.getValue());
        String productDescription = descriptionField.getText();
        String productWeight = weightField.getText();

        if (productId.isBlank() || productName.isBlank() || productPrice.isBlank() ||
                productQuantity.isBlank() || expiryDateField.getValue() == null ||
                productDescription.isBlank() || productWeight.isBlank()) {
            messageLabel.setText("Vui lòng nhập tất cả các trường!");
            return;
        }

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String insertProduct = "INSERT INTO products(product_id, product_name, product_price, product_quantity, expiry_date, description, weight) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(insertProduct);
            preparedStatement.setString(1, productId);
            preparedStatement.setString(2, productName);
            preparedStatement.setBigDecimal(3, new BigDecimal(productPrice));
            preparedStatement.setInt(4, Integer.parseInt(productQuantity));
            preparedStatement.setDate(5, expiryDate);
            preparedStatement.setString(6, productDescription);
            preparedStatement.setFloat(7, Float.parseFloat(productWeight));

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                messageLabel.setText("Thêm sản phẩm thành công!");
                clearFields();
            } else {
                messageLabel.setText("Không thể thêm sản phẩm.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Có lỗi xảy ra: " + e.getMessage());
        }
    }

    // Hàm sửa sản phẩm
    public void updateProduct() {
        String productId = idField.getText();
        String productName = nameField.getText();
        String productPrice = priceField.getText();
        String productQuantity = quantityField.getText();
        Date expiryDate = Date.valueOf(expiryDateField.getValue());
        String productDescription = descriptionField.getText();
        String productWeight = weightField.getText();

        if (productId.isBlank() || productName.isBlank() || productPrice.isBlank() ||
                productQuantity.isBlank() || expiryDateField.getValue() == null ||
                productDescription.isBlank() || productWeight.isBlank()) {
            messageLabel.setText("Vui lòng nhập tất cả các trường!");
            return;
        }

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String updateProduct = "UPDATE products SET product_name = ?, product_price = ?, product_quantity = ?, expiry_date = ?, description = ?, weight = ? WHERE product_id = ?";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(updateProduct);
            preparedStatement.setString(1, productName);
            preparedStatement.setBigDecimal(2, new BigDecimal(productPrice));
            preparedStatement.setInt(3, Integer.parseInt(productQuantity));
            preparedStatement.setDate(4, expiryDate);
            preparedStatement.setString(5, productDescription);
            preparedStatement.setFloat(6, Float.parseFloat(productWeight));
            preparedStatement.setString(7, productId);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                messageLabel.setText("Sửa sản phẩm thành công!");
                clearFields();
            } else {
                messageLabel.setText("Không thể sửa sản phẩm.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Có lỗi xảy ra: " + e.getMessage());
        }
    }

    // Hàm xóa sản phẩm
    public void deleteProduct() {
        String productId = idField.getText();

        if (productId.isBlank()) {
            messageLabel.setText("Vui lòng nhập ID sản phẩm!");
            return;
        }

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String deleteProduct = "DELETE FROM products WHERE product_id = ?";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(deleteProduct);
            preparedStatement.setString(1, productId);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                messageLabel.setText("Xóa sản phẩm thành công!");
                clearFields();
            } else {
                messageLabel.setText("Không thể xóa sản phẩm.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Có lỗi xảy ra: " + e.getMessage());
        }
    }

    // Hàm làm sạch các trường nhập
    private void clearFields() {
        idField.clear();
        nameField.clear();
        priceField.clear();
        quantityField.clear();
        expiryDateField.setValue(null);
        descriptionField.clear();
        weightField.clear();
    }
}
