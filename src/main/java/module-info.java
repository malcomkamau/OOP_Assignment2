module com.example.oop_assignment2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oop_assignment2 to javafx.fxml;
    exports com.example.oop_assignment2;
}