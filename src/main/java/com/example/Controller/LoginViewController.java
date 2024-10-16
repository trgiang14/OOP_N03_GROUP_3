package com.example.Controller;

import com.example.Database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginViewController {
    @FXML
    public Button loginButton;
    @FXML
    private Label thongBaoDangNhap;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    public void loginButtonOnAction(ActionEvent event) {
        validateLogin();
    }

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM userAccounts WHERE username = ? AND password = ?";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
            preparedStatement.setString(1, usernameTextField.getText());
            preparedStatement.setString(2, passwordField.getText());

            ResultSet queryResult = preparedStatement.executeQuery();

            if (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    thongBaoDangNhap.setText("Đăng nhập thành công!");
                    openProductManagement();
                } else {
                    thongBaoDangNhap.setText("Đăng nhập không hợp lệ. Hãy đăng nhập lại.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            thongBaoDangNhap.setText("Có lỗi xảy ra.");
        }
    }

    private void openProductManagement() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/com/example/ProductsManagement.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Quản Lý Sản Phẩm");
            stage.setScene(new Scene(root));
            stage.show();


            Stage currentStage = (Stage) usernameTextField.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
            thongBaoDangNhap.setText(e.getMessage());
        }

    }
}
