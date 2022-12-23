module com.example.delivery2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.delivery2 to javafx.fxml;
    exports com.example.delivery2;
}