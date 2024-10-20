package com.example.Controller;

import com.example.Database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductController {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField descriptionField;

    @FXML
    private Label messageLabel;

    // Hàm thêm sản phẩm
    public void addProduct() {
        String productId = idField.getText();
        String productName = nameField.getText();
        String productPrice = priceField.getText();
        String productDescription = descriptionField.getText();

        if (productId.isBlank() || productName.isBlank() || productPrice.isBlank() || productDescription.isBlank()) {
            messageLabel.setText("Vui lòng nhập tất cả các trường!");
            return;
        }

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String insertProduct = "INSERT INTO products(id, name, price, description) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(insertProduct);
            preparedStatement.setString(1, productId);
            preparedStatement.setString(2, productName);
            preparedStatement.setBigDecimal(3, new BigDecimal(productPrice));
            preparedStatement.setString(4, productDescription);

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
        String productDescription = descriptionField.getText();

        if (productId.isBlank() || productName.isBlank() || productPrice.isBlank() || productDescription.isBlank()) {
            messageLabel.setText("Vui lòng nhập tất cả các trường!");
            return;
        }

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String updateProduct = "UPDATE products SET name = ?, price = ?, description = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(updateProduct);
            preparedStatement.setString(1, productName);
            preparedStatement.setBigDecimal(2, new BigDecimal(productPrice));
            preparedStatement.setString(3, productDescription);
            preparedStatement.setString(4, productId);

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

        String deleteProduct = "DELETE FROM products WHERE id = ?";
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
        descriptionField.clear();
    }
}
