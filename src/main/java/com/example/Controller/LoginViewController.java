package com.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LoginViewController {

    @FXML
    private Label thongBaoDangNhap;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    public void loginButtonOnAction(ActionEvent event) {
        if (usernameTextField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {
            thongBaoDangNhap.setText("Đăng nhập sai tài khoản hoặc mật khẩu");
        }
        else {
            thongBaoDangNhap.setText("Đăng nhập tài khoản hoặc mật khẩu");
        }
    }
}



