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

        // Truy vấn lấy vai trò của người dùng thay vì chỉ kiểm tra sự tồn tại
        String verifyLogin = "SELECT role FROM userAccounts WHERE username = ? AND password = ?";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
            preparedStatement.setString(1, usernameTextField.getText());
            preparedStatement.setString(2, passwordField.getText());

            ResultSet queryResult = preparedStatement.executeQuery();

            if (queryResult.next()) {
                // Lấy vai trò của người dùng
                String role = queryResult.getString("role");

                thongBaoDangNhap.setText("Đăng nhập thành công!");

                // Điều hướng đến cửa sổ dựa trên vai trò
                switch (role) {
                    case "Customer":
                        switchTodatHang();
                        break;
                    case "Manager":
                        openProductManagement();
                        break;
                    default:
                        thongBaoDangNhap.setText("Vai trò không hợp lệ!");
                        break;
                }
            } else {
                thongBaoDangNhap.setText("Đăng nhập không hợp lệ. Hãy đăng nhập lại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            thongBaoDangNhap.setText("Có lỗi xảy ra.");
        }
    }

    // Mở cửa sổ dành cho Manager
    private void openProductManagement() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ProductsManagement.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Manager Dashboard");
            stage.setScene(new Scene(root));
            stage.show();

            closeCurrentWindow();
        } catch (Exception e) {
            e.printStackTrace();
            thongBaoDangNhap.setText(e.getMessage());
        }
    }

    //Mở cửa sổ đặt hàng
    private void switchTodatHang() {
        try {
            // Tải file FXML cho danh sách sản phẩm
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/DatHang.fxml"));
            Parent root = loader.load();

            // Lấy Stage hiện tại (cửa sổ hiện tại)
            Stage stage = (Stage) thongBaoDangNhap.getScene().getWindow();

            // Tạo Scene mới từ FXML và thiết lập nó
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Đặt hàng");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Đóng cửa sổ hiện tại
    private void closeCurrentWindow() {
        Stage currentStage = (Stage) usernameTextField.getScene().getWindow();
        currentStage.close();
    }
}
