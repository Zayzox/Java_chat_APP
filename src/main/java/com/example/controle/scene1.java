package com.example.controle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class scene1 {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML

    private Button button;

   @FXML
    void login () throws IOException {
       if (username.getText().equals("mehdi") && password.getText().equals("mehdi")){
           Stage a = (Stage) button.getScene().getWindow();
           FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scene2.fxml"));
           Scene scene = new Scene(fxmlLoader.load());
           a.setScene(scene);
       }
       else {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Authentification Error");
           alert.setHeaderText("Username or password are not correct ");
           alert.setContentText("try again ! ");

       }
   }
}