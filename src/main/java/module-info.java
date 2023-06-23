module com.example.controle {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.controle to javafx.fxml;
    exports com.example.controle;
}