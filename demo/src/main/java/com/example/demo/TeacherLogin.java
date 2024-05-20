package com.example.demo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;

import java.sql.*;
import java.util.ResourceBundle;

public class TeacherLogin implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private Button LoginScreenSignupButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField enterUsernameField;

    @FXML
    private PasswordField enterPasswordField;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void loginButtonOnAction(ActionEvent event) {

        if(!enterUsernameField.getText().isEmpty() && !enterPasswordField.getText().isEmpty()) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter your username and password");
        }
    }



    public void validateLogin(){

        databaseconnection_2 connectNow = new databaseconnection_2();
        Connection connectDB = connectNow.getConnection();

//        String verifyLogin = "SELECT * FROM teacherinfo WHERE teacher_id = '" + enterUsernameField.getText() + "' AND password = '" + enterPasswordField.getText() + "'";
//
//        try {
//            Statement statement = connectDB.createStatement();
//            ResultSet queryResult = statement.executeQuery(verifyLogin);
//
//            while(queryResult.next()) {
//                if(queryResult.getInt(1) == 1) {
//
//                    loginMessageLabel.setText("You have successfully logged in!");
//
//
//                    main_page();
//
//                    Stage stage = (Stage) cancelButton.getScene().getWindow();
//                    stage.close();
//                } else {
//                    loginMessageLabel.setText("Invalid login!");
//                }
//            }
//        } catch(Exception e) {
//            e.printStackTrace();
//            e.getCause();
//        }



        String verifyLogin = "SELECT * FROM teacherinfo WHERE teacher_id = ? AND password = ?";

        try {
            // Use a PreparedStatement to prevent SQL injection
            PreparedStatement statement = connectDB.prepareStatement(verifyLogin);
            statement.setString(1, enterUsernameField.getText());
            statement.setString(2, enterPasswordField.getText());
            ResultSet queryResult = statement.executeQuery();

            if (queryResult.next()) {

                loginMessageLabel.setText("You have successfully logged in!");

                Parent root = null;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/TeacherDashboard.fxml"));
                    root = fxmlLoader.load();
                    TeacherDashBoardController teacherDashBoardController = fxmlLoader.getController();
                    teacherDashBoardController.setTeacherName(enterUsernameField.getText());
//                      root = FXMLLoader.load(getClass().getResource("ChatBot.fxml"));
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


            } else {
                loginMessageLabel.setText("Invalid login. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void main_page() {
        try {
            Parent root2 = FXMLLoader.load(getClass().getResource("first_page.fxml"));
            Stage teacherPanelStage = new Stage();
//            registerStage.initStyle(StageStyle.UNDECORATED);
            teacherPanelStage.setScene(new Scene(root2, 900, 580));
            teacherPanelStage.setResizable(false);
            teacherPanelStage.show();

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void createAccountForm() {
        try {
            Parent root2 = FXMLLoader.load(getClass().getResource("TeacherRegister.fxml"));
            Stage registerStage = new Stage();
//            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root2, 900, 580));
            registerStage.setResizable(false);
            registerStage.show();

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void signUpButtonOnAction(ActionEvent actionEvent) {
        createAccountForm();

        Stage stage = (Stage) LoginScreenSignupButton.getScene().getWindow();
        stage.close();
    }


    public void cancelButtonOnAction(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("first_page.fxml"));
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

}
