package com.example.Controller;

import com.example.DAO.ProductDAO;
import com.example.Model.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    private TableView<Products> productListSP;

    @FXML
    private TableColumn<Products, Integer> colIDList;

    @FXML
    private TableColumn<Products, String> colNameList;

    @FXML
    private TableColumn<Products, Double> colPriceList;

    @FXML
    private TableColumn<Products, String> colDescList;

    @FXML
    private TextField productIdInput;

    @FXML
    private TextField quantityInput;

    @FXML
    private Label totalPriceLabel;

    private ProductDAO productDAO = new ProductDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Cấu hình các cột của TableView
        colIDList.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNameList.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPriceList.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDescList.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Lấy dữ liệu từ ProductDAO và hiển thị lên TableView
        ObservableList<Products> productList = FXCollections.observableArrayList(productDAO.getAllProducts());
        productListSP.setItems(productList);
    }

    @FXML
    public void calculateTotalPrice(ActionEvent event) {
        try {
            // Lấy ID sản phẩm và số lượng từ input
            int productId = Integer.parseInt(productIdInput.getText());
            int quantity = Integer.parseInt(quantityInput.getText());

            // Lấy thông tin sản phẩm từ cơ sở dữ liệu
            Products product = productDAO.getProductById(productId);

            if (product != null) {
                // Tính tổng giá
                double totalPrice = product.getPrice() * quantity;

                // Hiển thị tổng giá
                totalPriceLabel.setText("Tổng giá: " + totalPrice + " VND");
            } else {
                totalPriceLabel.setText("Không tìm thấy sản phẩm!");
            }
        } catch (NumberFormatException e) {
            totalPriceLabel.setText("Vui lòng nhập đúng dữ liệu!");
        }
    }
}