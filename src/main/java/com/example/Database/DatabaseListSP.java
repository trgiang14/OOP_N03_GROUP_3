package com.example.Database;

import java.sql.*;

public class DatabaseListSP {

    // Thông tin kết nối tới cơ sở dữ liệu
    private static final String URL = "jdbc:mysql://localhost:3306/listSanPham";
    private static final String USER = "root"; // Username của database
    private static final String PASSWORD = "1234"; // Mật khẩu của database

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Kết nối tới cơ sở dữ liệu
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Tạo một statement để thực hiện truy vấn SQL
            statement = connection.createStatement();

            // Truy vấn dữ liệu từ bảng "products"
            String query = "SELECT * FROM products";
            resultSet = statement.executeQuery(query);

            // Xử lý dữ liệu lấy về
            while (resultSet.next()) {
                // Lấy dữ liệu từ các cột của bảng
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");

                // In dữ liệu ra màn hình
                System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price + ", Description: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và các tài nguyên
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        // Tạo và trả về kết nối với cơ sở dữ liệu
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}