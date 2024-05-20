package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Central_Course_MS.Initiate();
        Parent root = FXMLLoader.load(getClass().getResource("first_page.fxml"));
//        root = FXMLLoader.load(getClass().getResource("StudentLogin.fxml"));
//        root = FXMLLoader.load(getClass().getResource("TeacherLogin.fxml"));
//        root = FXMLLoader.load(getClass().getResource("FXML/TeacherDashboard.fxml"));
//        root = FXMLLoader.load(getClass().getResource("student_mainPage.fxml"));
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 900, 580));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}