module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires mysql.connector.j;



    opens com.example to javafx.fxml;
    exports com.example.View;
    opens com.example.View to javafx.fxml;
    opens com.example.Controller to javafx.fxml;
    opens com.example.Model to javafx.base;

}