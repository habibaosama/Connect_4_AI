module com.example.connect_4_ai {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.connect_4_ai to javafx.fxml;
    exports com.example.connect_4_ai;
}