module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example to javafx.fxml;
    exports com.example.View;
    opens com.example.View to javafx.fxml;
    opens com.example.Controller to javafx.fxml;
}