package com.example.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/LoginView.fxml")));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setTitle("Đăng nhập");
            primaryStage.setScene(new Scene(root, 300, 200));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading FXML: " + e.getMessage());

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
