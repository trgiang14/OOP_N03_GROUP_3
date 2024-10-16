package com.example.Controller;

import com.example.Database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginViewController {

    @FXML
    private Label thongBaoDangNhap;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    public void loginButtonOnAction(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() && !passwordField.getText().isBlank()) {
            validateLogin();
        } else {
            thongBaoDangNhap.setText("Vui lòng nhập tài khoản và mật khẩu");
        }
    }

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection(); // Lấy kết nối từ DatabaseConnection

        String verifyLogin = "SELECT count(1) FROM userAccounts WHERE username = ? AND password = ?";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
            preparedStatement.setString(1, usernameTextField.getText());
            preparedStatement.setString(2, passwordField.getText());

            ResultSet queryResult = preparedStatement.executeQuery();

            if (queryResult.next() && queryResult.getInt(1) == 1) {
                thongBaoDangNhap.setText("Đăng nhập thành công!");
            } else {
                thongBaoDangNhap.setText("Đăng nhập không hợp lệ. Hãy đăng nhập lại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}



