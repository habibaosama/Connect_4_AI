module com.example.connect_4_ai {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.connect_4_ai to javafx.fxml;
    exports com.example.connect_4_ai;
    exports com.example.connect_4_ai.utilities;
    opens com.example.connect_4_ai.utilities to javafx.fxml;
    exports com.example.connect_4_ai.Tree;
    opens com.example.connect_4_ai.Tree to javafx.fxml;
}