package com.example.delivery2;


import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button backBtn;

    @FXML
    private Button logBtn;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passField;

    @FXML
    private Label label_error;



    private int num = 0;
    @FXML
    void initialize() {
        logBtn.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPassword = passField.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals("")) {
                try {
                    loginUser(loginText, loginPassword);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                label_error.setText("");
                num++;
                if (num == 2){
                    label_error.setText("У вас осталась 1 попытка");
                }
                if (num > 2){
                    System.exit(0);
                }
            }
            else
                label_error.setText("Поля не заполнены");
        });

    }
    public static String NameFooter = "";
    private void loginUser(String loginText, String loginPassword) throws SQLException {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(loginPassword);

        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        while (true) {
            try {
                if (result.next()) {
                    NameFooter = result.getString(2);
                }else
                    break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }
        if (counter >= 1) {
            logBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("mainScreen.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        }

    }
    public void goToBack() throws IOException{
        Parent wLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("startMenu.fxml")));
        Stage window = (Stage) backBtn.getScene().getWindow();
        window.setScene(new Scene(wLogin, 1600, 900));
        window.show();
    }

}
