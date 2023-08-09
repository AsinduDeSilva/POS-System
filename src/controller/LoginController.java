package controller;

import bo.BOFactory;
import bo.custom.LoginBO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.ObjectPasser;

import java.io.IOException;


public class LoginController {

    public TextField txtUsername;
    public TextField txtPassword;

    LoginBO loginBO = BOFactory.getInstance(BOFactory.BoTypes.LOGIN);

    public void btnLoginOnClick(ActionEvent actionEvent) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();



    }
}