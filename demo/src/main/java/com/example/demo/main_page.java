package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;


public class main_page {


    @FXML
    private Button cancelButton;

    @FXML
    private Button joinButton;

    @FXML
    protected void onJoinButtonClick(ActionEvent actionEvent) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("message.fxml"));
//            root = FXMLLoader.load(getClass().getResource("ChatBot.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 900, 580));
        stage.setResizable(false);
        stage.show();

        Stage current_stage = (Stage) cancelButton.getScene().getWindow();
        current_stage.close();
    }

    public void cancelButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
}
