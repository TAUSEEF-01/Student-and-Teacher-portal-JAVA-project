package com.example.demo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.net.URL;

public class LoginController implements Initializable {

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

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM new_table WHERE registration_no = '" + enterUsernameField.getText() + "' AND password = '" + enterPasswordField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if(queryResult.getInt(1) == 1) {
                    
                    loginMessageLabel.setText("You have successfully logged in!");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    main_page();
//
////                    Stage stage = (Stage) LoginScreenSignupButton.getScene().getWindow();
////                    stage.close();
//
                    Stage stage = (Stage) cancelButton.getScene().getWindow();
                    stage.close();
                } else {
                    loginMessageLabel.setText("Invalid login!");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void main_page() {
        try {
            Parent root2 = FXMLLoader.load(getClass().getResource("main_page.fxml"));
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
            Parent root2 = FXMLLoader.load(getClass().getResource("register.fxml"));
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
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

}
