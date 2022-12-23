package com.example.delivery2;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class startMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logBtn;

    @FXML
    private Button regBtn;

    @FXML
    void initialize() {


    }

    public void goTologin() throws IOException{
        Parent wLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        Stage window = (Stage) logBtn.getScene().getWindow();

        window.setScene(new Scene(wLogin, 1600, 900));
        window.show();
    }

    public void goToReg() throws  IOException{
        Parent wReg = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("reg.fxml")));
        Stage window = (Stage) regBtn.getScene().getWindow();
        window.setScene(new Scene(wReg, 1600, 900));
        window.show();
    }

}
