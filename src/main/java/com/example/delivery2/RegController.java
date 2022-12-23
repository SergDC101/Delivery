package com.example.delivery2;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegController {
    @FXML
    private Button backBtn;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Label label_error;
    @FXML
    private TextField emailField;

    @FXML
    private TextField fioField;

    @FXML
    private Button logBtn;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passField;

    @FXML
    private TextField telField;

    @FXML
    void initialize() {
        logBtn.setOnAction(event -> {
            signUpNewUser();
        });

    }


    private void signUpNewUser() {
        DataBaseHandler dbHandler = new DataBaseHandler();

        String phoneRV = "((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
        String emailRV = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}";

        String fio = fioField.getText();
        String login = loginField.getText();
        String password = passField.getText();
        String email = emailField.getText();
        String phone = telField.getText();

        if (!fio.isEmpty() && !login.isEmpty() && !password.isEmpty() && !email.isEmpty() && !phone.isEmpty()){
            if (!(password.length() < 8)){
                if(phone.matches(phoneRV)){
                    if (email.matches(emailRV)){
                        if (checkBox.isSelected()){
                            User user = new User(fio,login,password,phone,email);
                            dbHandler.signUpUser(user);
                            label_error.setText("Регистрация прошла успешно");
                        }
                        else
                            label_error.setText("Требуеться соглашение");
                    }
                    else
                        label_error.setText("Не существующий email");
                }
                else
                    label_error.setText("Номер телефона не корректный");
            }
            else
                label_error.setText("Пароль слишком короткий");
        }else
            label_error.setText("Не все поля заполнены");
    }

    public void goToBack() throws IOException{
        Parent wLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("startMenu.fxml")));
        Stage window = (Stage) backBtn.getScene().getWindow();
        window.setScene(new Scene(wLogin, 1600, 900));
        window.show();
    }

}
