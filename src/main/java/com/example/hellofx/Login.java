package com.example.hellofx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import javafx.scene.Node;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private Button cancelButton;

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
            validateLogin(event);
        } else {
            loginMessageLabel.setText("Please enter your username and password");
        }
    }
    static class student{
        static String fname;
        static String lname;
        static String reg;
        static int roll;
        static  String user;
    }


    /*public void validateLogin(ActionEvent event){

        databaseconnection connectNow = new databaseconnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM project.logintable WHERE username = '" + enterUsernameField.getText() + "' AND password = '" + enterPasswordField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if(queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("You have successfully logged in!");
                    student st = new student();
                    st.fname=;


                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                    Parent root = fxmlLoader.load();

                    // Get the scene from the current event source
                    Scene currentScene = ((Node) event.getSource()).getScene();

                    // Set the new root in the current scene
                    currentScene.setRoot(root);



                } else {
                    loginMessageLabel.setText("Invalid login!");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }*/

    public void validateLogin(ActionEvent event) {
        databaseconnection connectNow = new databaseconnection();
        Connection connectDB = connectNow.getConnection();

        // Modify the query to select firstname, lastname, and reg
        String verifyLogin = "SELECT firstname, lastname, reg, Roll, username FROM project.logintable WHERE username = ? AND password = ?";

        try {
            // Use a PreparedStatement to prevent SQL injection
            PreparedStatement statement = connectDB.prepareStatement(verifyLogin);
            statement.setString(1, enterUsernameField.getText());
            statement.setString(2, enterPasswordField.getText());
            ResultSet queryResult = statement.executeQuery();

            if (queryResult.next()) {
                // Retrieve the firstname, lastname, and reg from the result set
                String firstName = queryResult.getString("firstname");
                String lastName = queryResult.getString("lastname");
                String reg = queryResult.getString("reg");
                int roll = queryResult.getInt("Roll");
                String user= queryResult.getString("username") ;

                // Check if the required fields are not null or empty
                if (firstName != null && !firstName.isEmpty() &&
                        lastName != null && !lastName.isEmpty() &&
                        reg != null && !reg.isEmpty()) {
                    loginMessageLabel.setText("You have successfully logged in!");

                    // Set the retrieved values in the student object
                    student st = new student();
                    st.fname = firstName;
                    st.lname = lastName;
                    st.reg = reg;
                    st.roll=roll;
                    st.user= user;

                    // Proceed with your logic here using st.fname, st.lname, and st.reg
                    System.out.println("Student's first name: " + st.fname);
                    System.out.println("Student's last name: " + st.lname);
                    System.out.println("Student's registration number: " + st.reg);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                    Parent root = fxmlLoader.load();

                    // Get the scene from the current event source
                    Scene currentScene = ((Node) event.getSource()).getScene();

                    // Set the new root in the current scene
                    currentScene.setRoot(root);
                } else {
                    loginMessageLabel.setText("Required information not found for the user.");
                }
            } else {
                loginMessageLabel.setText("Invalid login. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void cancelButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}