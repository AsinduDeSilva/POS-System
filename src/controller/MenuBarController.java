package controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import util.ObjectPasser;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarController implements Initializable {


    public Label txtUserFullName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtUserFullName.setText(ObjectPasser.userFullName);
    }
}
