package com.example.DAO;

import com.example.Database.DatabaseListSP;
import com.example.Model.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    // Sử dụng kết nối từ DatabaseListSP
    public List<Products> getAllProducts() {
        List<Products> productList = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (Connection connection = DatabaseListSP.getConnection(); // Sử dụng phương thức getConnection từ DatabaseListSP
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");

                Products product = new Products(id, name, price, description);
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Products getProductById(int productId) {
        Products product = null;
        String query = "SELECT * FROM products WHERE id = ?";

        try (Connection connection = DatabaseListSP.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");

                product = new Products(productId, name, price, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
}
