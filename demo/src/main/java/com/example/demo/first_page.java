package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class first_page{

    @FXML
    private Button studentButton;

    @FXML
    private Button cancelButton;

    @FXML
    protected void studentButtonOnAction(ActionEvent actionEvent) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("StudentLogin.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 900, 580));
        stage.setResizable(false);
        stage.show();

        Stage current_stage = (Stage) studentButton.getScene().getWindow();
        current_stage.close();
    }


    @FXML
    protected void teacherButtonOnAction(ActionEvent actionEvent) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("TeacherLogin.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 900, 580));
        stage.setResizable(false);
        stage.show();

        Stage current_stage = (Stage) studentButton.getScene().getWindow();
        current_stage.close();
    }

    public void cancelButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
}