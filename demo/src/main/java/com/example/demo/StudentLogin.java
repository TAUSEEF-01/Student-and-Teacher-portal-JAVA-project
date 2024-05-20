package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StudentLogin implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField enterUsernameField;

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private Button LoginScreenSignupButton;

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
    public static class student{
        public static String fname;
        static String lname;
        static String reg;
        static int roll;
        static  String user;
        static String mybook;
        static String todo1;
        static String time1;
        static String todo2;
        static String time2;
        static String todo3;
        static String time3;
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


                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student_mainPage.fxml"));
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
        databaseconnection_2 connectNow = new databaseconnection_2();
        Connection connectDB = connectNow.getConnection();

        // Modify the query to select firstname, lastname, and reg
//        String verifyLogin = "SELECT firstname, lastname, reg, Roll, username, Mybook, Todo, Schedule, Todo2, Schedule2, Todo3, Schedule3, email FROM logintable WHERE username = ? AND password = ?";
        String verifyLogin = "SELECT * FROM logintable WHERE username = ? AND password = ?";

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
                String book1= queryResult.getString("Mybook") ;
                String todo= queryResult.getString("Todo") ;
                String schedule= queryResult.getString("Schedule") ;
                String todo2= queryResult.getString("Todo2") ;
                String schedule2= queryResult.getString("Schedule2");
                String todo3= queryResult.getString("Todo3") ;
                String schedule3= queryResult.getString("Schedule3");
                String email= queryResult.getString("email");


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
                    st.mybook=book1;
                    st.todo1= todo;
                    st.time1=schedule;
                    st.todo2= todo2;
                    st.time2=schedule2;
                    st.todo3= todo3;
                    st.time3=schedule3;

                    // Proceed with your logic here using st.fname, st.lname, and st.reg
                    System.out.println("Student's first name: " + st.fname);
                    System.out.println("Student's last name: " + st.lname);
                    System.out.println("Student's registration number: " + st.reg);

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student_mainPage.fxml"));
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


    public void createAccountForm() {
        try {
            Parent root2 = FXMLLoader.load(getClass().getResource("StudentRegister.fxml"));
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